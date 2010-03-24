package me.hcl.seekin.Auth

import me.hcl.seekin.Auth.Role.*
import me.hcl.seekin.Formation.*
import me.hcl.seekin.Util.Address
import me.hcl.seekin.Util.Settings
import me.hcl.seekin.Internship.Company
import me.hcl.seekin.Internship.*
import me.hcl.seekin.Ressource.*

import org.springframework.security.providers.UsernamePasswordAuthenticationToken as AuthToken

import org.codehaus.groovy.grails.plugins.springsecurity.RedirectUtils
import org.grails.plugins.springsecurity.service.AuthenticateService

import org.springframework.security.AuthenticationTrustResolverImpl
import org.springframework.security.DisabledException
import org.springframework.security.context.SecurityContextHolder as SCH
import org.springframework.security.ui.AbstractProcessingFilter
import org.springframework.security.ui.webapp.AuthenticationProcessingFilter
import org.springframework.security.AccountExpiredException
import org.hibernate.LockMode

import nl.captcha.Captcha
import nl.captcha.backgrounds.*
import nl.captcha.servlet.CaptchaServletUtil
import grails.converters.JSON
import org.codehaus.groovy.grails.plugins.springsecurity.Secured


/**
 * User controller.
 */
class UserController {


        static navigation = [
		group:'menu',
		order:1,
		title:'home',
		action:'index'
	]

	def authenticateService

        def roleService

	// the delete, save and update actions only accept POST requests
	static Map allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

	private final authenticationTrustResolver = new AuthenticationTrustResolverImpl()

	def daoAuthenticationProvider
	def emailerService
	def jcaptchaService
        def sessionFactory

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
			def model = [:]
			
			// Get the user instance logged in
			def userInstance = authenticateService.userDomain()
			sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)

			// If the user is an admin
			if(authenticateService.ifAnyGranted("ROLE_ADMIN")) {

				// Get the admin instance logged in
				def admin = Admin.findByUser(userInstance)

				// Get the users waiting for validation
				def usersWaitingForValidation = User.findAllByEnabledAndValidated(false,false)

				model["usersWaitingForValidation"] = usersWaitingForValidation
				model["totalUsersWaitingForValidation"] = usersWaitingForValidation.size()
			}
			
			// If the user is a student
			if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {

				// Get the student instance logged in
				def student = Student.findByUser(userInstance)
				
				// Get the current promotion for the manager's formation
				def promotion = Promotion.getCurrentForStudent(student)

				def lastOffers = []
				def lastInternshipReports = []
				def i = 0

				// Get academic tutor for the current student's internship if it exist
				def currentInternshipAcademicTutor = Internship.getCurrentForStudent(student)?.academicTutor


				// Get all the student's internships
				def studentInternships = Internship.findAllByStudent(student, [sort: "beginAt", order: "desc"])

				// Get the five last offers for the student's promotion
				Offer.findAll(sort: "id", order: "desc").each {
					if(it?.promotions?.contains(promotion) && i < 5 && it?.getStatus() == "offer.validated") {
						lastOffers << it
						++i
					}
				}
				
				// Get the five last reports for the student's promotion
				Report.findAllByIsPrivate(false,[sort: "id", order: "desc", max: 5]).each {
					lastInternshipReports << it
				}

				model["lastOffers"] = lastOffers
				model["totalLastOffers"] = lastOffers.size()
				model["currentInternshipAcademicTutor"] = currentInternshipAcademicTutor
				model["studentInternships"] = studentInternships
				model["lastInternshipReports"] = lastInternshipReports
				model["totalStudentInternships"] = studentInternships.size()
				model["totalLastInternshipReports"] = lastInternshipReports.size()
				model["totalInternshipReports"] = Report.findAllByIsPrivate(false).size()
				model["totalLinks"] = Link.count()
			}

			// If the user is a formation manager
			if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER")) {
				
				// Get the formation manager instance logged in
				def formationManager = FormationManager.findByUser(userInstance)

				// Get the current promotion for the manager's formation
				def promotion = Promotion.getCurrentForFormation(formationManager.formation)

				// Get the offers awaiting validation for the manager's formation
				def offersWaitingForValidation = Offer.getOffersWaitingForValidationForPromotion(promotion)

				// Get students awaiting for validation who belongs to the same formation as manager
				def studentsWaitingForValidation = []

				def studentsWithoutInternship = []
				def studentsWithoutAcademicTutor = []
				def internshipsWaitingForValidation = []

				promotion?.students?.each {

					// TODO ignore internship refused
					def currentInternship = Internship.getCurrentForStudent(it)

					if(!it?.user?.enabled && !it?.user?.validated) {
						studentsWaitingForValidation << it
					}

					// Get students without internship
					if(!currentInternship) {
						studentsWithoutInternship << it
					}
					else {
						// Get students with an internship who don't have yet an academic tutor
						if(!currentInternship.academicTutor) {
							studentsWithoutAcademicTutor << it
						}

						// Get internships awayting validation
						if(currentInternship.getStatus() == "internship.waitForValidation") {
							internshipsWaitingForValidation << currentInternship
						}
					}
				}

				model["studentsWithoutInternship"] = studentsWithoutInternship
				model["studentsWithoutAcademicTutor"] = studentsWithoutAcademicTutor
				model["internshipsWaitingForValidation"] = internshipsWaitingForValidation
				model["offersWaitingForValidation"] = offersWaitingForValidation
				model["studentsWaitingForValidation"] = studentsWaitingForValidation
				model["totalStudentsWaitingForValidation"] = studentsWaitingForValidation.size()
				model["totalStudentsWithoutInternship"] = studentsWithoutInternship.size()
				model["totalStudentsWithoutAcademicTutor"] = studentsWithoutAcademicTutor.size()
				model["totalInternshipsWaitingForValidation"] = internshipsWaitingForValidation.size()
				model["totalOffersWaitingForValidation"] = offersWaitingForValidation.size()
			}
			
			// If the user is a staff
			if(authenticateService.ifAnyGranted("ROLE_STAFF")) {

				// Get the staff instance logged in
				def staff = Staff.findByUser(userInstance)

				// Get the students whose staff is academic tutor for the current year
				def internships = Internship.findAllByAcademicTutorAndMillesime(staff, Millesime.getCurrent())
				def staffStudents = internships?.student
				
				// Get the convocations for this internships
				def staffConvocations = internships?.convocation

				model["staffStudents"] = staffStudents
				model["staffConvocations"] = staffConvocations
				model["totalStaffStudents"] = staffStudents.size()
				model["totalStaffConvocations"] = staffConvocations.size()
			}
			render(view: "index", model: model)
		}
	}

        /**
         * Edit Profil Closure
         */
        def profile = {

            if(authenticateService.isLoggedIn())
            {
                def userInstance = authenticateService.userDomain()
                sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
                def ret = [userInstance:userInstance]
                if (session.id)
                {
                    def role
                    def pass = userInstance.password
                    def promotion

                    if(authenticateService.ifAllGranted("ROLE_STUDENT"))
                    {
                        def formations = getFormations()

                        profile = Student.findByUser(userInstance)
                        promotion = Promotion.getCurrentForStudent(profile)

                        ret.formations = formations
                        ret.profile = profile
                        ret.promotion = promotion?.id
                    }

                    // If User selected "Staff"
                    if(authenticateService.ifAllGranted("ROLE_EXTERNAL"))
                    {
                        profile = External.findByUser(userInstance)

                        if(profile?.company != null)
                        {
                            ret.company = profile.company.name
                        }

                        ret.profile = profile
                    }

                    if(request.method == 'POST')
                    {
                        userInstance.properties = params

                        if(params.password == "" && params.repassword == "")
                        {
                             userInstance.password = pass
                        }
                        else
                        {
                             // encode the password for the insertion in database
                             userInstance.password = authenticateService.encodePassword(params.password)
                        }

                        userInstance.validate()

                        // check if the password matches the repassword
                        if (params.password != params.repassword)
                        {
                                userInstance.errors.rejectValue(
                                    'password',
                                    'user.password.dismatch'
                                )
                        }

                        // If everything went well
                        if (!userInstance.hasErrors())
                        {
                            userInstance.showEmail = (params.showEmail)?true:false
                            // If User selected "Student"
                            if(authenticateService.ifAllGranted("ROLE_STUDENT"))
                            {
                                profile.visible = (params.visible)?true:false

                                if(params.promotion != null)
                                {
                                    if(params.promotion.toInteger() != promotion?.id && Promotion.get(params.promotion)?.millesime?.current)
                                    {
                                        if(promotion?.id)
                                            profile.removeFromPromotions(promotion)
                                        profile.addToPromotions(Promotion.get(params.promotion))
                                    }
                                }
                                
                            } // If User selected "Staff"
                            if(authenticateService.ifAllGranted("ROLE_EXTERNAL"))
                            {
                                profile.formerStudent = (params.formerStudent)?true:false

                                if(params.company != "")
                                {
                                    if(Company.countByName(params.company) == 0)
                                    {
                                        def company = new Company(phone: "")
                                        company.name = params.company
                                        profile.company = company
                                        company.save()
                                    }
                                    else profile.company = Company.findByName(params.company)
                                }
                                else profile.company = null
                            }
                            userInstance.save(flush: true)
                            redirect action:'index'
                        }
                        else
                        {
                            userInstance.password = pass
                            userInstance.discard()
                            if(authenticateService.ifAllGranted("ROLE_STUDENT"))
                            {
                                ret.promotion = params.promotion
                                ret.profile.visible = params.visible
                            }
                            if(authenticateService.ifAllGranted("ROLE_EXTERNAL"))
                            {
                                ret.company = params.company
                                ret.profile.formerStudent = params.formerStudent
                            }
                        }
                    }
                }
                return ret
            }
        }


	/**
	 * List all Users
	 */
        @Secured(['ROLE_ADMIN'])
	def list = {

	}

 	/**
	 * Validate registered Users
	 */
        @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER'])
	def validate = {
            def user
            def config = authenticateService.securityConfig
            params.each {
                if(it.key.contains("validate_") && it.value == "on")
                {
                    user = User.get(it.key.split("_")[1].toInteger())
                    user.enabled = true
                    user.validated = true
                    user.save(flush: true)
                    // If we use confirmation mail
                    if (config.security.useMail)
                    {
                        emailerService.buildRegistrationMail(user)
                    }
                }
            }
	}


	/**
	 * Show user details
	 */
	def show = {
            def userInstance = User.get(params.id)
            if(authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_FORMATIONMANAGER"))
            {
                    // get the user using the url id
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

                        if(role.authority == "ROLE_STUDENT")
                        {
                                def prom = Promotion.getCurrentForStudent(role)
                                roleNames += role.getRoleName()
                                if(prom)
                                        roleNames += " : " + prom
                        }
                        else if(role.authority == "ROLE_FORMATIONMANAGER")
                        {
                                roleNames += role.getRoleName() + " : " + role.formation + "<br />"
                        }
                        else
                        {
                                roleNames += role.getRoleName() + "<br />"
                        }

                    }

                    // build a string from the address
                    def userAddress = ""
                    userAddress += userInstance?.address?.street + " "
                    userAddress += userInstance?.address?.town + " "
                    userAddress += userInstance?.address?.zipCode

                    [userInstance: userInstance, address: userAddress, roleNames: roleNames]
            }
            else
            {
                    userInstance?.authorities?.each {
                            if(it instanceof Student) {
                                    redirect controller: "student", action: "show", id: userInstance?.id
                            }
                            else if(it instanceof Staff) {
                                    redirect controller: "staff", action: "show", id: userInstance?.id
                            }
                            else if(it instanceof External) {
                                    redirect controller: "external", action: "show", id: userInstance?.id
                            }
                    }
            }
	}

	/**
	 * Person delete action. Before removing an existing userInstance,
	 * he should be removed from those authorities which he is involved.
	 */
        @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER'])
	def delete = {

                def userInstance = User.get(params.id)
                def ok = true

                if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
                {
                    ok = false
                    def user = authenticateService.userDomain()
                    def formation = FormationManager.findByUser(user)?.formation
                    def student = Student.findByUser(userInstance)
                    if(student && Promotion.getCurrentForStudent(student)?.formation == formation)
                    {
                        ok = true
                    }
                }

		if (userInstance && ok) {
			def authPrincipal = authenticateService.principal()
			//avoid self-delete if the logged-in user is an admin
			if (!(authPrincipal instanceof String) && authPrincipal?.domainClass?.email == userInstance.email) {
                            flash.message = "user.not.deleted"
                            flash.args = [params.id]
                            redirect(action: "show", id: params.id)
			}
			else {
				userInstance.delete()
				flash.message = "user.deleted"
				flash.args = [params.id]

                                if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
                                    redirect(action: "validate")
                                else
                                    redirect(action: "list")
			}
		}
		else {
			flash.message = "user.not.deleted"
			flash.args = [params.id]
			redirect(action: "show", id: params.id)
		}
	}

        @Secured(['ROLE_ADMIN'])
	def edit = {

                def formations = getFormations()
		def userInstance = User.get(params.id)
		if (!userInstance) {
			flash.message = "user.not.found"
			flash.args = [params.id]
			redirect(action: "list")
			return
		}

		[userInstance: userInstance, roles: buildRolesList(userInstance), formations: formations]
	}

	/**
	 * Person update action.
	 */
        @Secured(['ROLE_ADMIN'])
	def update = {

                def formations = getFormations()

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
				render view: 'edit', model: [userInstance: userInstance, roles: buildRolesList(userInstance), formations: formations]
			return
		}
                
                userInstance.properties = params
		if (userInstance.save()) {
			flash.message = "user.updated"
			flash.args = [params.id]
			addRoles(userInstance)
			redirect action: show, id: userInstance.id
		}
		else {
                    
			render view: 'edit', model: [userInstance: userInstance, roles: buildParamsRolesList(), formations: formations]
		}
	}

        @Secured(['ROLE_ADMIN'])
	def create = {


		[userInstance: new User(password: generatePwd(8)), roles: buildParamsRolesList(), formations:formations]
	}

	/**
	 * Person save action.
	 */
        @Secured(['ROLE_ADMIN'])
	def save = {
		def userInstance = new User()
                userInstance.address = new Address()
		userInstance.properties = params

                def formations = getFormations()

		if (userInstance.validate()) {
                        userInstance.password = authenticateService.encodePassword(params.password)
                        userInstance.validated = true
                        userInstance.save()
			flash.message = "user.created"
			flash.args = [userInstance.id]
			addRoles(userInstance)
			redirect action: show, id: userInstance.id
		}
		else {
			render view: 'create', model: [userInstance: userInstance, roles: buildParamsRolesList(), formations:formations]
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
        @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
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
        @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def accessDenied = {
		if (isLoggedIn() && authenticationTrustResolver.isRememberMe(SCH.context?.authentication)) {
			// have cookie but the page is guarded with IS_AUTHENTICATED_FULLY
			redirect action: full, params: params
		}
	}
	
	
	/**
	 * Login failed
	 */
        @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def authFail = {

                // Get the username
		def username = session[AuthenticationProcessingFilter.SPRING_SECURITY_LAST_USERNAME_KEY]
		def msg = ''
                // Get the reason why auth failed
		def exception = session[AbstractProcessingFilter.SPRING_SECURITY_LAST_EXCEPTION_KEY]

                // If the user account has expired
                if(exception && exception instanceof AccountExpiredException) {
                        redirect action:renewal, params: [username: username]

                }
                else
                {
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
	}

	/**
	 * Renew expired account
	 */
        @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
        def renewal = {
            def formations = getFormations()

            if(request.method == 'POST')
            {
                def username = params.username
                def password = authenticateService.encodePassword(params.password)
                def promotion = Promotion.get(params.promotion)

                def user = User.findByEmailAndPassword(username, password)

                if(user)
                {
                    def student = Student.findByUser(user)
                    if(student)
                    {
                        def current = Promotion.getCurrentForStudent(student)
                        if(!current && promotion)
                        {
                            student.addToPromotions(promotion)
                            user.enabled = false
                            user.validated = false
                            redirect action: 'registerSuccess'
                        }
                        else
                        {
                            flash.message = message(code:"user.not.expired")
                        }
                    }
                    else
                    {
                        flash.message = message(code:"user.not.student")
                    }
                }
                else
                {
                    flash.message = message(code:"user.not.found")
                }
            }


            return [formations: formations, username: params.username]
        }


	/**
	 * Lost password form
	 */
        @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
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
        @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
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
                def formation
                
                roles.each
                {
                   formation = null
                   found = false
                   for (role in userInstance.authorities)
                   {
                       if(role.getRoleName() == it)
                       {
                            found = true
                            if(role.getRoleName() == "Student")
                            {
                                formation = Promotion.getCurrentForStudent(role)?.id
                            }
                            else if(role.getRoleName() == "FormationManager")
                            {
                                formation = Promotion.getCurrentForFormation(role.formation)?.id
                            }

                            break
                       }
                   }
                   authorities << [name:it, value:found, formation:formation]
                }
                return authorities
        }

        private ArrayList getFormations()
        {
            def millesime = Millesime.getCurrent()
			if(millesime) {
				return Promotion.findAllByMillesime(millesime).collect {
					[
						id: it.id,
						value: it?.formation?.label
					]
				}
			}
        }

        private void addRoles(userInstance)
        {
                def roles = roleService.getRoleNames()
                def name
                def value
                def found
                def catchedRole
                def roleClass
                def instance
                def promotion
  
                roles.each
                {
                    catchedRole = null
                    name = "ROLE_$it".toUpperCase()
                    value = params."$name"
                    def test = value == "on" || value == "true"

                    for (role in userInstance.authorities)
                    {
                       if(role.getRoleName() == it)
                       {
                            catchedRole = role
                            break
                       }
                    }
                    if(test && catchedRole != null)
                    {
                        if(it == "Student" && params.FORMATION_Student)
                        {
                            promotion = Promotion.getCurrentForStudent(catchedRole)
                            if(params.FORMATION_Student.toInteger() != promotion?.id && Promotion.get(params.FORMATION_Student)?.millesime?.current)
                            {
                                if(promotion?.id)
                                    catchedRole.removeFromPromotions(promotion)
                                catchedRole.addToPromotions(Promotion.get(params.FORMATION_Student))
                            }
                        }
                        else if(it == "FormationManager" && params.FORMATION_FormationManager)
                        {
                            promotion = Promotion.get(params.FORMATION_FormationManager)
                            if(promotion?.formation?.manager != catchedRole)
                            {
                                if(catchedRole.formation != null)
                                {
                                    catchedRole?.formation?.manager = null
                                }
                                if(promotion?.formation?.manager != null)
                                {
                                    promotion?.formation?.manager.delete()
                                }
                                promotion?.formation?.manager = catchedRole
                            }
                        }
                    }
                    else if(test && catchedRole == null)
                    {
                        Thread t = Thread.currentThread()
                        ClassLoader cl = t.getContextClassLoader()
                        roleClass = cl.loadClass("me.hcl.seekin.Auth.Role." + it)
                        instance = roleClass.newInstance()

                        if(it == "Student" && params.FORMATION_Student)
                        {
                            promotion = Promotion.get(params.FORMATION_Student)
                            instance.addToPromotions(promotion)
                        }
                        else if(it == "FormationManager" && params.FORMATION_FormationManager)
                        {
                            promotion = Promotion.get(params.FORMATION_FormationManager)
                            if(promotion?.formation?.manager != null)
                            {
                                promotion?.formation?.manager.delete()
                            }
                            promotion?.formation?.manager = instance
                        }
                        userInstance.addToAuthorities(instance)
                    }
                    else if(value == null && catchedRole != null)
                    {
                        if(it == "Student")
                        {
                            catchedRole.promotions.each {
                                it.removeFromStudents(catchedRole)
                            }
                        }
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
        @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
        def generateCaptcha = {
            // Generate a captcha of 150px X 50px with gimp, noise and border
            // And add the associated text to the current user session
            def captcha = new Captcha.Builder(150, 50)
                .addText()
                .gimp()
                .addNoise()
                .build()
            session.captcha = captcha
            // Write the captcha
            CaptchaServletUtil.writeImage(response, captcha.image)
        }


	/**
	 * User Registration Top page.
	 */
        @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def register = {
                def userInstance = new User()
                userInstance.address = new Address()
                def ret
                def company

                // skip if already logged in
                if (authenticateService.isLoggedIn()) {
                        redirect uri: '/'
                        return
                }

                if (session.id)
                {
                    userInstance.properties = params
                    def role
                    // If User selected "Student"
                    if(params.usertype == "1")
                    {

                        def formations = getFormations()

                        role = new Student(visible: (params.visible != null)?:false)
                        ret = [userInstance: userInstance, usertype: params.usertype, formations:formations]
                    } // If User selected "Staff"
                    else if(params.usertype == "2")
                    {
                        role = new Staff()
                        ret = [userInstance: userInstance, usertype: params.usertype]
                    } // If User selected "Other"
                    else if(params.usertype == "3")
                    {

                        // Check if the company as been specified
                        if(params.company != null)
                        {
                            if(Company.countByName(params.company) == 0)
                            {
                                company = new Company(phone: "")
                                company.name = params.company
                            }
                            else
                            {
                                company = Company.findByName(params.company)
                            }
                        }
                        role = new External(company: company, formerStudent: (params.formerStudent != null)?:false)
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
                        userInstance.validate()

                        // check if the password matches the repassword
                        if (params.password != params.repassword)
                        {
                                userInstance.errors.rejectValue(
                                    'password',
                                    'user.password.dismatch'
                                )
                        }
         
                        if (!session?.captcha?.isCorrect(params.captcha))
                        {
                                userInstance.errors.reject(
                                   'user.code.dismatch'
                                )
                        }

                        // encode the password for the insertion in database
                        def pass = authenticateService.encodePassword(params.password)
                        userInstance.password = pass
                        userInstance.enabled = false
                        userInstance.validated = false

                        // If everything went well
                        if (!userInstance.hasErrors())
                        {
                            if(company != null)
                                company.save()
                            if(userInstance.save(flush: true)) {
								if(role && Promotion.get(params.promotion))
								{
									role.addToPromotions(Promotion.get(params.promotion))
									userInstance.addToAuthorities(role)
								}
							}

//                          def auth = new AuthToken(userInstance.email, params.password)
//                          def authtoken = daoAuthenticationProvider.authenticate(auth)
//                          SCH.context.authentication = authtoken

                            redirect action: 'registerSuccess'
                        }
                        else
                        {
                                userInstance.password = ''
                                return ret
                        }
                    }

               }
	}

        @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
	def registerSuccess = { }

        @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER'])
	def dataTableDataAsJSON = {
		def list = []

		if(params.enabled != null && params.validated != null)
		{
			if(authenticateService.ifAnyGranted("ROLE_ADMIN")) {
				list = User.createCriteria().list(params) {
					eq('enabled', Boolean.valueOf(params.enabled))
					eq('validated', Boolean.valueOf(params.validated))
				}
			}
			else if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
			{
				def userInstance = authenticateService.userDomain()
				sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
				def formationManager = FormationManager.findByUser(userInstance)
				def promotion = Promotion.getCurrentForFormation(formationManager.formation)
                println promotion
				promotion?.students?.each {
                    println it?.user
                    println it?.user?.enabled
                    println it?.user?.validated
					if(!it?.user?.enabled && !it?.user?.validated) {
                        println "blabla"
						list << it?.user
					}
				}
			}
		}
		else
		{
			list = User.list(params)
		}

		def ret = []
		response.setHeader("Cache-Control", "no-store")

		list.each {

			def auth = ""
			it.authorities.each {
                            
                                if(it.authority == "ROLE_STUDENT")
                                {
                                    def prom = Promotion.getCurrentForStudent(it)
                                    auth += it.getRoleName()
                                    if(prom)
                                        auth += " : " + prom
                                }
                                else if(it.authority == "ROLE_FORMATIONMANAGER")
                                {
                                    auth += it.getRoleName() + " : " + it.formation + "<br />"
                                }
                                else
                                {
                                    auth += it.getRoleName() + "<br />"
                                }
			}

			ret << [
				id:it.id,
				email:it.email,
				firstName: it.firstName,
				lastName: it.lastName,
				showEmail:it.showEmail,
				roles:auth,
				enabled:it.enabled?message(code:'user.enabled'):it.validated?message(code:'user.blocked'):message(code:'user.pending'),
				urlID:it.id
			]
		}

		def data = [
				totalRecords: list.size(),
				results: ret
		]

		render data as JSON
	}
}
