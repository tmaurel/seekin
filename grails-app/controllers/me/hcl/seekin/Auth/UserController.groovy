package me.hcl.seekin.Auth

import me.hcl.seekin.Auth.Role.*
import me.hcl.seekin.Formation
import me.hcl.seekin.Util.Address
import me.hcl.seekin.Util.Settings

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
import grails.converters.JSON


/**
 * User controller.
 */
class UserController {

	def authenticateService

        def roleService

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
			if(!Settings.get(1))
			{
				redirect(controller: "settings", action: "install")
			}
			else
			{
				redirect action: auth, params: params
			}

		}
		else
		{
			// TODO : Accueil Logged User
                        render "Logged-in Home"
		}
	}

	/**
	 * List all Users
	 */
	def list = {

	}

	/**
	 * Show user details
	 */
	def show = {
                // get the user using the url id
		def userInstance = User.get(params.id)
                // if the user doesnt exit, show error message
		if (!userInstance) {
			flash.message = "user.not.found"
			flash.args = [params.id]
                        // redirect to the list of users
			redirect(action: "list")
			return
		}

                // get all roles associated to the user
		def roleNames = ""
		for (role in userInstance.authorities) {
			roleNames += role.getRoleName() + " "
		}

                // build a string from the address
                def userAddress = ""
                userAddress += userInstance?.address?.street + " "
                userAddress += userInstance?.address?.town + " "
                userAddress += userInstance?.address?.zipCode
          
		[userInstance: userInstance, address: userAddress, roleNames: roleNames]
	}

	/**
	 * Person delete action. Before removing an existing userInstance,
	 * he should be removed from those authorities which he is involved.
	 */
	def delete = {

		def userInstance = User.get(params.id)
		if (userInstance) {
			def authPrincipal = authenticateService.principal()
			//avoid self-delete if the logged-in user is an admin
			if (!(authPrincipal instanceof String) && authPrincipal.email == userInstance.email) {
                            flash.message = "user.not.deleted"
                            flash.args = [params.id]
                            redirect(action: "show", id: params.id)
			}
			else {
				userInstance.delete()
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
                
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = "user.not.found"
			flash.args = [params.id]
			redirect(action: "list")
			return
		}

		[userInstance: userInstance, roles: buildRolesList(userInstance)]
	}

	/**
	 * Person update action.
	 */
	def update = {

		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = "user.not.found"
			flash.args = [params.id]
			redirect(action: "edit", id: params.id)
			return
		}

		long version = params.version.toLong()
		if (userInstance.version > version) {
			userInstance.errors.rejectValue 'version', "userInstance.optimistic.locking.failure",
				"Another user has updated this User while you were editing."
				render view: 'edit', model: [userInstance: userInstance, roles: buildRolesList(userInstance)]
			return
		}

		def oldPassword = userInstance.password
		userInstance.properties = params
		if (!params.password.equals(oldPassword)) {
			userInstance.password = authenticateService.encodePassword(params.password)
		}
		if (userInstance.save()) {
			flash.message = "user.updated"
			flash.args = [params.id]
			addRoles(userInstance)
			redirect action: show, id: userInstance.id
		}
		else {
                    
			render view: 'edit', model: [userInstance: userInstance, roles: buildParamsRolesList()]
		}
	}

	def create = {

		[userInstance: new User(password: generatePwd(8)), roles: buildParamsRolesList()]
	}

	/**
	 * Person save action.
	 */
	def save = {
		def userInstance = new User()
                userInstance.address = new Address()
		userInstance.properties = params

		if (userInstance.validate()) {
                        userInstance.password = authenticateService.encodePassword(params.password)
                        userInstance.save()
			flash.message = "user.created"
			flash.args = [userInstance.id]
			addRoles(userInstance)
			redirect action: show, id: userInstance.id
		}
		else {
			render view: 'create', model: [authorityList: Role.list(), userInstance: userInstance]
		}
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
	 * Lost password form
	 */
        def lostPassword = {

                if(request.method == 'POST')
                {
                    def user = User.findByEmail(params.email)
                    if (user)
                    {
                            emailerService.buildLostPwdEmail(user)
                            flash.message = message(code:"user.lostpassword.email.sent", args:["${params.email}"])
                    }
                    else
                    {
                            flash.message = message(code:"user.not.found")
                    }
                }
        }

 	/**
	 * Form to check the Url Code and enter a new password
	 */
        def checkCode = {

                def code = params.id
                def user

                if(code && (user = emailerService.getUserFromLostPwdCode(code)) != null)
                {

                        if(request.method == 'POST')
                        {
                            if (params.password != params.repassword)
                            {
                                    flash.message = message(code:"user.password.dismatch")
                            }
                            else
                            {
                                    // encode the password for the insertion in database
                                    def pass = authenticateService.encodePassword(params.password)
                                    user.password = pass
                                    if(user.save())
                                        flash.message = message(code:"user.password.changed")

                            }
                        }
                }
                else
                {
                    flash.message = message(code:"user.lostpassword.code.invalid")
                }
        }

	
	/**
	 * Check if logged in.
	 */
	private boolean isLoggedIn()
        {
		return authenticateService.isLoggedIn()
	}
	
	/**
         * Make sure the is no cache for the response
         */
	private void noCache(response)
        {
		response.setHeader('Cache-Control', 'no-cache') // HTTP 1.1
		response.addDateHeader('Expires', 0)
		response.setDateHeader('max-age', 0)
		response.setIntHeader ('Expires', -1) //prevents caching at the proxy server
		response.addHeader('cache-Control', 'private') //IE5.x only
	}

        private ArrayList buildParamsRolesList()
        {
                def roles = roleService.getRoleNames()
                def authorities = []
                def role
                
                roles.each
                {
                     role = "ROLE_$it".toUpperCase()
                     authorities << [name:it, value:params."$role"]
                }

                return authorities
        }

        private ArrayList buildRolesList(userInstance)
        {
                def roles = roleService.getRoleNames()
                def authorities = []
                def found
                
                roles.each
                {
                   found = false
                   for (role in userInstance.authorities)
                   {
                       if(role.getRoleName() == it)
                       {
                            found = true
                            break
                       }
                   }
                   authorities << [name:it, value:found]
                }

                return authorities
        }

        private void addRoles(userInstance)
        {
                def roles = roleService.getRoleNames()
                def name
                def value
                def found
                def catchedRole
                def roleClass
  
                roles.each
                {
                    catchedRole = null
                    name = "ROLE_$it".toUpperCase()
                    value = params."$name"

                    for (role in userInstance.authorities)
                    {
                       if(role.getRoleName() == it)
                       {
                            catchedRole = role
                            break
                       }
                    }
            
                    if(value == "on" && catchedRole == null)
                    {
                        Thread t = Thread.currentThread()
                        ClassLoader cl = t.getContextClassLoader()
                        roleClass = cl.loadClass("me.hcl.seekin.Auth.Role." + it)
                        userInstance.addToAuthorities(roleClass.newInstance())
                    }
                    else if(value == null && catchedRole != null)
                    {
                        userInstance.removeFromAuthorities(catchedRole)
                        catchedRole.delete()
                    }
                }
        }

        private static String generatePwd(length) {
            def charset = "!0123456789abcdefghijklmnopqrstuvwxyz"       
            def rand = new Random(System.currentTimeMillis())
            def ret = (1..length).collect {
                charset.charAt(rand.nextInt(charset.length()))
            }
            return ret.join()
        }
	
	
	
	
	/* Based on RegisterController */

    	/**
	 * Captcha generation
	 */
        def generateCaptcha = {
            // Generate a captcha of 150px X 50px with gimp, noise and border
            // And add the associated text to the current user session
            def captcha = new Captcha.Builder(150, 50)
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

                def userInstance = new User()
                userInstance.address = new Address()
                def ret

                // skip if already logged in
                if (authenticateService.isLoggedIn()) {
                        redirect uri: '/'
                        return
                }

                if (session.id)
                {
                    userInstance.properties = params
 
                    // If User selected "Student"
                    if(params.usertype == "1")
                    {
                        userInstance.addToAuthorities(new Student())
                        ret = [userInstance: userInstance, usertype: params.usertype, formations:Formation.list()]
                    } // If User selected "Staff"
                    else if(params.usertype == "2")
                    {
                        userInstance.addToAuthorities(new Staff())
                        ret = [userInstance: userInstance, usertype: params.usertype]
                    } // If User selected "Other"
                    else if(params.usertype == "3")
                    {
                        userInstance.addToAuthorities(new External(formerStudent: params.formerStudent))
                        ret = [userInstance: userInstance, usertype: params.usertype, company: params.company]
                    }
                    else
                    {
                        redirect action: auth, params: params
                    }

                    
                    // If u got to this form by a link or from homepage form
                    if(params.fromHome == "1")
                    {
                        return ret
                    }
                    else if(request.method == 'POST')
                    {
                        def config = authenticateService.securityConfig

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
                        userInstance.password = pass
                        userInstance.enabled = false

                        //Construction of the lineBar included in the email with the good size
                        String lineBar = ""
                        message(code:"user.email.content.text2").size().times{lineBar += "-"}

                        // If everything went well
                        if (userInstance.validate() && flash.message == null)
                        {
                            // Check if the company as been specified
                            if(params.company != null)
                            {
                                // If the company already exists in database
                                def comp = Company.findByName(params.company)
                                if(comp.size() == 1)
                                {

                                    println comp
                                    // TODO : Link to Company or Create it
                                }

                            }

                            
                            // If we use confirmation mail
                            if (config.security.useMail)
                            {
                                emailerService.buildRegistrationMail(userInstance)
                            }
                            userInstance.save(flush: true)


//                          def auth = new AuthToken(userInstance.email, params.password)
//                          def authtoken = daoAuthenticationProvider.authenticate(auth)
//                          SCH.context.authentication = authtoken
                            redirect uri: '/'
                        }
                        else
                        {
                                userInstance.password = ''
                                return ret
                        }
                    }

               }
	}

    
    def dataTableDataAsJSON = {
        def list = User.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {

            def auth = ""
            it.authorities.each {
                auth += it.getRoleName() + "<br />"
            }

            ret << [
                id:it.id,
                email:it.email,
                firstName: it.firstName,
                lastName: it.lastName,
                showEmail:it.showEmail,
                roles:auth,
                enabled:it.enabled,
                urlID:it.id
            ]
        }

        def data = [
                totalRecords: User.count(),
                results: ret
        ]

        render data as JSON
    }
}
