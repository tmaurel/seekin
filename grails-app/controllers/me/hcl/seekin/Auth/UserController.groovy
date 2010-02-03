package me.hcl.seekin.Auth

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Auth.Role
import me.hcl.seekin.Profile.*
import me.hcl.seekin.Formation
import me.hcl.seekin.Util.Address

import org.springframework.security.providers.UsernamePasswordAuthenticationToken as AuthToken

import org.codehaus.groovy.grails.plugins.springsecurity.RedirectUtils
import org.grails.plugins.springsecurity.service.AuthenticateService

import org.springframework.security.AuthenticationTrustResolverImpl
import org.springframework.security.DisabledException
import org.springframework.security.context.SecurityContextHolder as SCH
import org.springframework.security.ui.AbstractProcessingFilter
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter

import java.awt.image.BufferedImage

import nl.captcha.Captcha
import nl.captcha.backgrounds.*
import nl.captcha.servlet.CaptchaServletUtil

/**
 * User controller.
 */
class UserController {

	def authenticateService

	// the delete, save and update actions only accept POST requests
	static Map allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

	private final authenticationTrustResolver = new AuthenticationTrustResolverImpl()

	def daoAuthenticationProvider
	def emailerService
	
	def jcaptchaService


	/**
	 * Default closure
	 */
	def index = {

                // If the user isnt logged in, redirect to auth closure
		if (!isLoggedIn()) {
			redirect action: auth, params: params
		}
		else
		{
			// TODO : Accueil Logged User
		}
	}

	/**
	 * List all Users
	 */
	def list = {
		params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
		[personList: User.list(params), personTotal: User.count()]
	}

	/**
	 * Show user details
	 */
	def show = {
                // get the user using the url id
		def person = User.get(params.id)
                // if the user doesnt exit, show error message
		if (!person) {
			flash.message = "user.not.found"
			flash.args = [params.id]
                        // redirect to the list of users
			redirect(action: "list")
			return
		}
                // get all roles associated to the user
		List roleNames = []
		for (role in person.authorities) {
			roleNames << role.authority
		}
                // sort user roles
		roleNames.sort { n1, n2 ->
			n1 <=> n2
		}
		[person: person, roleNames: roleNames]
	}

	/**
	 * Person delete action. Before removing an existing person,
	 * he should be removed from those authorities which he is involved.
	 */
	def delete = {

		def person = User.get(params.id)
		if (person) {
			def authPrincipal = authenticateService.principal()
			//avoid self-delete if the logged-in user is an admin
			if (!(authPrincipal instanceof String) && authPrincipal.email == person.email) {
                            flash.message = "user.not.deleted"
                            flash.args = [params.id]
                            redirect(action: "show", id: params.id)
			}
			else {
				//first, delete this person from People_Authorities table.
				Role.findAll().each { it.removeFromPeople(person) }
				person.delete()
				flash.message = "user.deleted"
				flash.args = [params.id]
				redirect(action: "list")
			}
		}
		else {
			flash.message = "user.not.deleted"
			flash.args = [params.id]
			redirect(action: "show", id: params.id)
		}
	}

	def edit = {

		def person = User.get(params.id)
		if (!person) {
			flash.message = "user.not.found"
			flash.args = [params.id]
			redirect(action: "list")
			return
		}

		return buildPersonModel(person)
	}

	/**
	 * Person update action.
	 */
	def update = {

		def person = User.get(params.id)
		if (!person) {
			flash.message = "user.not.found"
			flash.args = [params.id]
			redirect(action: "edit", id: params.id)
			return
		}

		long version = params.version.toLong()
		if (person.version > version) {
			person.errors.rejectValue 'version', "person.optimistic.locking.failure",
				"Another user has updated this User while you were editing."
				render view: 'edit', model: buildPersonModel(person)
			return
		}

		def oldPassword = person.password
		person.properties = params
		if (!params.password.equals(oldPassword)) {
			person.password = authenticateService.encodePassword(params.password)
		}
		if (person.save()) {
			flash.message = "user.updated"
			flash.args = [params.id]
			Role.findAll().each { it.removeFromPeople(person) }
			addRoles(person)
			redirect action: show, id: person.id
		}
		else {
			render view: 'edit', model: buildPersonModel(person)
		}
	}

	def create = {
		[person: new User(params), authorityList: Role.list()]
	}

	/**
	 * Person save action.
	 */
	def save = {

		def person = new User()
		person.properties = params
		person.password = authenticateService.encodePassword(params.password)
		if (person.save()) {
			flash.message = "user.created"
			flash.args = [person.id]
			addRoles(person)
			redirect action: show, id: person.id
		}
		else {
			render view: 'create', model: [authorityList: Role.list(), person: person]
		}
	}

	private void addRoles(person) {
		for (String key in params.keySet()) {
			if (key.contains('ROLE') && 'on' == params.get(key)) {
				Role.findByAuthority(key).addToPeople(person)
			}
		}
	}

	private Map buildPersonModel(person) {

		List roles = Role.list()
		roles.sort { r1, r2 ->
			r1.authority <=> r2.authority
		}
		Set userRoleNames = []
		for (role in person.authorities) {
			userRoleNames << role.authority
		}
		LinkedHashMap<Role, Boolean> roleMap = [:]
		for (role in roles) {
			roleMap[(role)] = userRoleNames.contains(role.authority)
		}

		return [person: person, roleMap: roleMap]
	}
	
	
	
	
	/* Based on LogoutController */ 
	
	/**
	 * Index action. Redirects to the Spring security logout uri.
	 */
	def logout = {
		// TODO  put any pre-logout code here
		redirect(uri: '/j_spring_security_logout')
	}
	
	
	
	
	/* Based on LoginController */
	
	/**
	 * Show the login page.
	 */
	def auth = {
		
		noCache response
		def config = authenticateService.securityConfig.security
		if (isLoggedIn()) {
			redirect uri: '/'
			return
		}
		
		render view: 'auth', model: [postUrl: "${request.contextPath}${config.filterProcessesUrl}"]
	}	
	
	/**
	 * Show denied page.
	 */
	def accessDenied = {
		if (isLoggedIn() && authenticationTrustResolver.isRememberMe(SCH.context?.authentication)) {
			// have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
			redirect action: full, params: params
		}
	}
	
	
	/**
	 * Login failed
	 */
	def authFail = {

                // Get the username
		def username = session[AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY]
		def msg = ''
                // Get the reason why auth failed
		def exception = session[AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY]
		if (exception) {
                        // If the user has been disabled
			if (exception instanceof DisabledException) {
				msg = message(code:"user.login.validation.disabled", args:["${username}"])
			} // If the login or password is wrong
			else {
				msg = message(code:"user.login.validation.invalid", args:["${username}"])
			}
		}
		
		flash.message = msg
		redirect action: auth, params: params
	}
	
	/**
	 * Check if logged in.
	 */
	private boolean isLoggedIn() {
		return authenticateService.isLoggedIn()
	}
	
	/**
         * Make sure the is no cache for the response
         */
	private void noCache(response) {
		response.setHeader('Cache-Control', 'no-cache') // HTTP 1.1
		response.addDateHeader('Expires', 0)
		response.setDateHeader('max-age', 0)
		response.setIntHeader ('Expires', -1) //prevents caching at the proxy server
		response.addHeader('cache-Control', 'private') //IE5.x only
	}
	
	
	
	
	/* Based on RegisterController */

    	/**
	 * Captcha generation
	 */
        def generateCaptcha = {
            // Generate a captcha of 200px X 50px with gimp, noise and border
            // And add the associated text to the current user session
            def captcha = new Captcha.Builder(200, 50)
                .addText()
                .gimp()
                .addNoise()
                .addBorder()
                .build()
            session.captcha = captcha
            // Write the captcha
            CaptchaServletUtil.writeImage(response, captcha.image)
        }


	/**
	 * User Registration Top page.
	 */
	def register = {

                def person = new User()
                def ret


                // skip if already logged in
                if (authenticateService.isLoggedIn()) {
                        redirect uri: '/'
                        return
                }

                if (session.id)
                {
                    person.email = params.email
                    person.password = params.password

                    // If User selected "Student"
                    if(params.usertype == "1")
                    {
                        person.profile = new Student()
                        ret = [person: person, profile: person.profile, usertype: params.usertype, formations:Formation.list()]
                    } // If User selected "Staff"
                    else if(params.usertype == "2")
                    {
                        person.profile = new Staff()
                        ret = [person: person, profile: person.profile, usertype: params.usertype]
                    } // If User selected "Other"
                    else if(params.usertype == "3")
                    {
                        person.profile = new External()
                        person.profile.formerStudent = params.formerStudent
                        ret = [person: person, profile: person.profile, usertype: params.usertype, company: params.company]
                    }
                    else return;

                    person.profile.firstName = params.firstName
                    person.profile.lastName = params.lastName
                
                    // If u got to this form by a link or from homepage form
                    if(request.method == 'GET' || params.fromHome == "1")
                    {
                        return ret
                    }
                    else if(request.method == 'POST')
                    {

                        def config = authenticateService.securityConfig
                        def defaultRole = config.security.defaultRole

                        // Add all form data to the user profile
                        person.profile.firstName = params.firstName
                        person.profile.lastName = params.lastName
                        person.profile.address = new Address()
                        person.profile.address.street = params.street
                        person.profile.address.town = params.town
                        person.profile.address.zipCode = params.zipCode
                        person.profile.phone = params.phone
                        person.profile.visible = params.visible

                        // retrieve default role
                        def role = Role.findByAuthority(defaultRole)
                        if (!role)
                        {
                                flash.message = "user.default.role.not.found"
                        }
                        // check if the entered code matches the captcha image
                        if (!session?.captcha?.isCorrect(params.captcha))
                        {
                                flash.message = message(code:"user.code.dismatch")
                        }
                        // check if the password matches the repassword
                        if (params.password != params.repassword)
                        {
                                flash.message = message(code:"user.password.dismatch")
                        }
                        // encode the password for the insertion in database
                        def pass = authenticateService.encodePassword(params.password)
                        person.password = pass
                        person.enabled = false
                        person.showEmail = false

                        //Construction of the lineBar included in the email with the good size
                        String lineBar = ""
                        message(code:"user.email.content.text2").size().times{lineBar += "-"}

                        // If everything went well
                        if (person.validate() && flash.message == null)
                        {
                            // Check if the company as been specified
                            if(params.company != null)
                            {
                                // If the company already exists in database
                                def comp = Company.findByName(params.company)
                                if(comp.size() == 1)
                                {
                                    // TODO : Link to Company or Create it
                                }

                            }

                            // Add default role to the user
                            role.addToPeople(person)

                            // If we use confirmation mail
                            if (config.security.useMail)
                            {
                                flash.message = message(code:"user.email.content.text1") + """

                                ${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}

                                ${message(code:"user.email.content.text2")}
                                ${lineBar}
                                ${message(code:"user.email")}: ${person.email}"""

                                def email = [
                                        to: [person.email], // 'to' expects a List, NOT a single email address
                                        subject: "[${request.contextPath}] "+ message(code:"user.email.subject"),
                                        text: flash.message // 'text' is the email body
                                        ]
                                emailerService.sendEmails([email])
                            }
                            person.save(flush: true)


//                          def auth = new AuthToken(person.email, params.password)
//                          def authtoken = daoAuthenticationProvider.authenticate(auth)
//                          SCH.context.authentication = authtoken
                            redirect uri: '/'
                        }
                        else
                        {
                                person.password = ''
                                return ret
                        }
                    }
               }
	}
}
