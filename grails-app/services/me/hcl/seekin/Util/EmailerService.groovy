package me.hcl.seekin.Util

import javax.mail.MessagingException
import org.codehaus.groovy.grails.commons.ConfigurationHolder
import org.springframework.context.i18n.LocaleContextHolder as LCH
import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import me.hcl.seekin.Auth.User
import org.apache.commons.codec.binary.Base64
import java.text.DateFormat
import me.hcl.seekin.Util.Settings


/**
 * Simple service for sending emails.
 *
 * Work is planned in the Grails roadmap to implement first-class email
 * support, so there's no point in making this code any more sophisticated.
 *
 * @author Haotian Sun
 */
class EmailerService {

	boolean transactional = false

        def messageSource

	def mailSender
	def mailMessage // a "prototype" email instance

	/**
	 * Send a list of emails.
	 *
	 * @param mails a list of maps
	 */
	def sendEmails(mails) {

		// Build the mail messages
		def messages = []
		for (mail in mails) {
			// create a copy of the default message
			def message = new SimpleMailMessage(mailMessage)
			message.to = mail.to
			message.text = mail.text
			message.subject = mail.subject
			messages << message
		}

		// Send them all together
		try {
			mailSender.send(messages as SimpleMailMessage[])
		}
		catch (MailException e) {
			log.error "Failed to send emails: $e.message", e
		}
		catch (MessagingException e) {
			log.error "Failed to send emails: $e.message", e
		}
	}

        /**
	 * Build the code for the lost password form
	 *
	 * @param user the user who lost his password
	 */
        def buildContactMail(message) {

            def email = [
                    to: [ConfigurationHolder.config.security.mailFrom], // 'to' expects a List, NOT a single email address
                    subject: message.subject,
                    text: """ From : ${message.fullName} <${message.email}>
Content : ${message.body} """ // 'text' is the email body
                    ]

            sendEmails([email])
       }


	/**
	 * Build the code for the lost password form
	 *
	 * @param user the user who lost his password
	 */
        def buildLostPwdURL(user) {

            /* Get information from user for the hash */
            def pwd = user.password
            def email =  user.email

            /* Build the today's date */
            def date = DateFormat.getInstance().format(new Date())

            /* Build the string with all the information */
            def tmp = date.toString()+pwd+email

            /* Encode the string */
            def encode = Base64.encodeBase64(tmp.getBytes())

            /* Return the encoded string in a String format */
            return new String(encode)

        }
        

    	/**
	 * Build the registration confirmation mail
	 *
	 * @param user instance of the newly registered user
	 */
       def buildRegistrationMail(user) {

            def locale = LCH.getLocale();
            Object[] args = {}

            //Construction of the lineBar included in the email with the good size
            String lineBar = ""
            messageSource.resolveCode("user.email.content.text2", locale).format(args).size().times{lineBar += "-"}

            def msg = """
${messageSource.resolveCode("user.email.content.text1", locale).format(args)}
${ConfigurationHolder.config.grails.serverURL}

${messageSource.resolveCode("user.email.content.text2", locale).format(args)}
${lineBar}
${messageSource.resolveCode("user.email", locale).format(args)}: ${user.email}"""

            def email = [
                    to: [user.email], // 'to' expects a List, NOT a single email address
                    subject: "[${Settings.get(1)?.applicationName}] "+ messageSource.resolveCode("user.email.subject", locale).format(args),
                    text: msg // 'text' is the email body
                    ]
                    
            sendEmails([email])
       }


    	/**
	 * Build the email with the url containing the code for the lost password form
	 *
	 * @param user the user who lost his password
	 */
        def buildLostPwdEmail(user) {

            /* Build the code */
            def encodedUrl = buildLostPwdURL(user)

            /* Get the serveur URL from the Config file */
            def baseUrl = ConfigurationHolder.config.grails.serverURL

            log.error baseUrl + "/user/checkCode/" + encodedUrl

            def email = [
                    to: [user.email], // 'to' expects a List, NOT a single email address
                    subject: "[${Settings.get(1)?.applicationName}] "+ messageSource.resolveCode("user.lostPassword", locale).format(args),
                    text: baseUrl + "/user/checkCode/" + encodedUrl // 'text' is the email body
                    ]

            sendEmails([email])
        }

    	/**
	 * Check if the given code is valid and
         * return the user
	 *
	 * @param encodedUrl the code provided by the user
	 */
        def getUserFromLostPwdCode(encodedUrl) {

            /* Current Date */
            def today = new Date()

            /* Number of milliseconds per day */
            long mills_per_day = 1000 * 60 * 60 * 24;

            println "Encoded Url: " + encodedUrl

            /* Decode the base64 code */
            def decodedUrl = new String(Base64.decodeBase64(encodedUrl.getBytes()))

            println "Date: " + decodedUrl.substring(0, 14)
            println "Password MD5: " + decodedUrl.substring(14, 54)
            println "Email: " + decodedUrl.substring(54, decodedUrl.length())


            /* Split the code to get the date, the password, and the email */
            def date = DateFormat.getInstance().parse(decodedUrl.substring(0, 14))
            def pwd = decodedUrl.substring(14, 54)
            def email = decodedUrl.substring(54, decodedUrl.length())

            /* Check if the user exists */
            def userFound = User.findByEmailAndPassword(email, pwd)

            /* Make sur the code's date is less than 3 days ago */
            def value = (today.getTime() - date.getTime())/mills_per_day

            /* If the user exists, and the date is ok, then return true */
            if(userFound != null && value < 3)
                return userFound
            else
                return null
        }
}
