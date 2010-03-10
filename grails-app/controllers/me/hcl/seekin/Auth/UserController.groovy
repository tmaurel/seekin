package me.hcl.seekin.Auth

import me.hcl.seekin.Auth.Role.*
import me.hcl.seekin.Formation.*
import me.hcl.seekin.Util.Address
import me.hcl.seekin.Util.Settings
import me.hcl.seekin.Company
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
import org.hibernate.LockMode

import nl.captcha.Captcha
import nl.captcha.backgrounds.*
import nl.captcha.servlet.CaptchaServletUtil
import grails.converters.JSON


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

			// Get the user instance logged in
			def userInstance = authenticateService.userDomain()
			sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)



			// If the user is an admin
			if(authenticateService.ifAllGranted("ROLE_ADMIN")) {

				// Get the admin instance logged in
				def admin = Admin.findByUser(userInstance)

				// Get the users waiting for validation
				def usersWaitingForValidation = User.findAllByEnabledAndValidated(false,false)

				render(
					view: "index",
					model: [
						usersWaitingForValidation: usersWaitingForValidation,
						totalUsersWaitingForValidation: usersWaitingForValidation.size(),
					]
				)
			}
			
			// If the user is a student
			if(authenticateService.ifAllGranted("ROLE_STUDENT")) {

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

				render(
					view: "index",
					model: [
						lastOffers: lastOffers,
						totalLastOffers: lastOffers.size(),
						currentInternshipAcademicTutor: currentInternshipAcademicTutor,
						studentInternships: studentInternships,
						lastInternshipReports: lastInternshipReports,
						totalStudentInternships: studentInternships.size(),
						totalLastInternshipReports: lastInternshipReports.size(),
						totalInternshipReports: Report.findAllByIsPrivate(false).size(),
						totalLinks: Link.count()
					]
				)
			}

			// If the user is a formation manager
			if(authenticateService.ifAllGranted("ROLE_FORMATIONMANAGER")) {
				
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
						if(!currentInternship.getStatus() == "internship.waitForValidation") {
							internshipsWaitingForValidation << currentInternship
						}
					}
				}

				render(
					view: "index",
					model: [
						studentsWithoutInternship: studentsWithoutInternship,
						studentsWithoutAcademicTutor: studentsWithoutAcademicTutor,
						internshipsWaitingForValidation: internshipsWaitingForValidation,
						offersWaitingForValidation: offersWaitingForValidation,
						studentsWaitingForValidation: studentsWaitingForValidation,
						totalStudentsWaitingForValidation: studentsWaitingForValidation.size(),
						totalStudentsWithoutInternship: studentsWithoutInternship.size(),
						totalStudentsWithoutAcademicTutor: studentsWithoutAcademicTutor.size(),
						totalInternshipsWaitingForValidation: internshipsWaitingForValidation.size(),
						totalOffersWaitingForValidation: offersWaitingForValidation.size(),
					]
				)
			}
			
			// If the user is a staff
			if(authenticateService.ifAllGranted("ROLE_STAFF")) {

				// Get the staff instance logged in
				def staff = Staff.findByUser(userInstance)

				// Get the students whose staff is academic tutor for the current year
				def internships = Internship.findAllByAcademicTutorAndMillesime(staff, Millesime.getCurrent())
				def staffStudents = internships?.student
				
				// Get the convocations for this internships
				def staffConvocations = internships?.convocation

				render(
					view: "index",
					model: [
						staffStudents: staffStudents,
						staffConvocations: staffConvocations,
						totalStaffStudents: staffStudents.size(),
						totalStaffConvocations: staffConvocations.size()
					]
				)
			}
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
	def list = {

	}

 	/**
	 * Validate registered Users
	 */
	def validate = {
            def user
            params.each {
                if(it.key.contains("validate_") && it.value == "on")
                {
                    user = User.get(it.key.split("_")[1].toInteger())
                    user.enabled = true
                    user.validated = true
                    user.save(flush: true)
                }
            }

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

	def create = {


		[userInstance: new User(password: generatePwd(8)), roles: buildParamsRolesList(), formations:formations]
	}

	/**
	 * Person save action.
	 */
	def save = {
		def userInstance = new User()
                userInstance.address = new Address()
		userInstance.properties = params

                def formations = getFormations()

		if (userInstance.validate()) {
                        userInstance.password = authenticateService.encodePassword(params.password)
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

                    for (role in userInstance.authorities)
                    {
                       if(role.getRoleName() == it)
                       {
                            catchedRole = role
                            break
                       }
                    }
                    if(value == "on" && catchedRole != null)
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
                    else if(value == "on" && catchedRole == null)
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
						if(role && Promotion.get(params.promotion)) {
							role.addToPromotions(Promotion.get(params.promotion))
						}

						userInstance.addToAuthorities(role)
                        ret = [userInstance: userInstance, usertype: params.usertype, formations:formations]
                    } // If User selected "Staff"
                    else if(params.usertype == "2")
                    {
                        role = new Staff()
                        userInstance.addToAuthorities(role)
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
                        userInstance.addToAuthorities(role)
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

                        //Construction of the lineBar included in the email with the good size
                        String lineBar = ""
                        message(code:"user.email.content.text2").size().times{lineBar += "-"}


                        // If everything went well
                        if (!userInstance.hasErrors())
                        {
                            
                            // If we use confirmation mail
                            if (config.security.useMail)
                            {
                                emailerService.buildRegistrationMail(userInstance)
                            }
                            if(company != null)
                                company.save()
                            userInstance.save(flush: true)

//                          def auth = new AuthToken(userInstance.email, params.password)
//                          def authtoken = daoAuthenticationProvider.authenticate(auth)
//                          SCH.context.authentication = authtoken

							redirect uri: '/user/registerSuccess'
                        }
                        else
                        {
                                userInstance.password = ''
                                return ret
                        }
                    }

               }
	}

	def registerSuccess = { }

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
				promotion?.students?.each {
					if(!it?.user?.enabled && !it?.user?.validated) {
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
				auth += it.getRoleName() + "<br />"
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
				totalRecords: User.count(),
				results: ret
		]

		render data as JSON
	}
}
