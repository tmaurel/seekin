package me.hcl.seekin.Auth

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Auth.Role
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
	
	def index = {
		if (!isLoggedIn()) {
			redirect action: auth, params: params
		}
		else
		{
			render "olol"
			// TODO
		}
	}

	def list = {
		params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
		[personList: User.list(params), personTotal: User.count()]
	}

	def show = {
		def person = User.get(params.id)
		if (!person) {
			flash.message = "user.not.found"
			flash.args = [params.id]
			flash.defaultMessage = "User not found with id ${params.id}"
			redirect(action: "list")
			return
		}
		List roleNames = []
		for (role in person.authorities) {
			roleNames << role.authority
		}
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
                            flash.defaultMessage = "User ${params.id} could not be deleted"
                            redirect(action: "show", id: params.id)
			}
			else {
				//first, delete this person from People_Authorities table.
				Role.findAll().each { it.removeFromPeople(person) }
				person.delete()
				flash.message = "user.deleted"
				flash.args = [params.id]
				flash.defaultMessage = "User ${params.id} deleted"
				redirect(action: "list")
			}
		}
		else {
			flash.message = "user.not.deleted"
			flash.args = [params.id]
			flash.defaultMessage = "User ${params.id} could not be deleted"
			redirect(action: "show", id: params.id)
		}
	}

	def edit = {

		def person = User.get(params.id)
		if (!person) {
			flash.message = "user.not.found"
			flash.args = [params.id]
			flash.defaultMessage = "User not found with id ${params.id}"
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
			flash.defaultMessage = "User not found with id ${params.id}"
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
			flash.defaultMessage = "User ${params.id} updated"
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
			flash.defaultMessage = "User ${person.id} created"
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
	 * Login page for users with a remember-me cookie but accessing a IS_AUTHENTICATED_FULLY page.
	 */
	def full = {
		render view: 'auth', params: params,
		model: [hasCookie: authenticationTrustResolver.isRememberMe(SCH.context?.authentication)]
	}
	
	/**
	 * login failed
	 */
	def authFail = {
		
		def username = session[AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY]
		def msg = ''
		def exception = session[AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY]
		if (exception) {
			if (exception instanceof DisabledException) {
				msg = message(code:"user.login.validation.disabled", args:["${username}"])
			}
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
	
	/** cache controls */
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
            def captcha = new Captcha.Builder(200, 50)
                .addText()
                .gimp()
                .addNoise()
                .addBorder()
                .build()
            session.captcha = captcha
            CaptchaServletUtil.writeImage(response, captcha.image)
        }


	/**
	 * User Registration Top page.
	 */
	def register = {
            
		
		if(request.method == 'GET' || params.fromHome == "1") { 		

			// skip if already logged in
			if (authenticateService.isLoggedIn()) {
				redirect uri: '/'
				return
			}
			
			if (session.id) {
				def person = new User()
				person.properties = params
				return [person: person]
			}
		}
		else if(request.method == 'POST') {
			
			// skip if already logged in
			if (authenticateService.isLoggedIn()) {
				redirect uri: '/'
				return
			}
			
			def person = new User()
			person.properties = params
			
			def config = authenticateService.securityConfig
			def defaultRole = config.security.defaultRole
			
			def role = Role.findByAuthority(defaultRole)
			if (!role) {
				person.password = ''
				flash.message = "user.default.role.not.found"
				flash.args = [person.id]
				flash.defaultMessage = "Default Role not found"
				render view: 'register', model: [person: person]
				return
			}

                        if (!session?.captcha?.isCorrect(params.captcha))
			{
				person.password = ''
				flash.message = message(code:"user.code.dismatch")
				flash.defaultMessage = "Access code did not match"
				render view: 'register', model: [person: person]
				return
			}
                     
			if (params.password != params.repassword) {
				person.password = ''
				flash.message = message(code:"user.password.dismatch")
				flash.defaultMessage = "The passwords you entered do not match"
				render view: 'register', model: [person: person]
				return
			}
			
			def pass = authenticateService.encodePassword(params.password)
			person.password = pass
			person.enabled = false
			person.showEmail = false
			if (person.save()) {
				role.addToPeople(person)
				if (config.security.useMail) {
					String emailContent = """You have signed up for an account at:
	
	 ${request.scheme}://${request.serverName}:${request.serverPort}${request.contextPath}
	
	 Here are the details of your account:
	 -------------------------------------
	 Email: ${person.email}
	 Full Name: ${person.userRealName}
	 Password: ${params.password}
	"""
					
					def email = [
							to: [person.email], // 'to' expects a List, NOT a single email address
							subject: "[${request.contextPath}] Account Signed Up",
							text: emailContent // 'text' is the email body
							]
					emailerService.sendEmails([email])
				}
				
				person.save(flush: true)
				
//				def auth = new AuthToken(person.email, params.password)
//				def authtoken = daoAuthenticationProvider.authenticate(auth)
//				SCH.context.authentication = authtoken
				redirect uri: '/'
			}
			else {
				person.password = ''
				render view: 'register', model: [person: person]
			}
		}
	}
}
