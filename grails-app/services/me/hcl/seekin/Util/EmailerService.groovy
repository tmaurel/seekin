package me.hcl.seekin.Util

import javax.mail.MessagingException

import org.springframework.mail.MailException
import org.springframework.mail.SimpleMailMessage
import me.hcl.seekin.Auth.User
import org.apache.commons.codec.binary.Base64
import java.text.DateFormat


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

    def buildURL(user) {

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
}
