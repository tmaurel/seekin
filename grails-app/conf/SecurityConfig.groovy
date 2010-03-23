security {

	// see DefaultSecurityConfig.groovy for all settable/overridable properties

	active = true

	loginUserDomainClass = "me.hcl.seekin.Auth.User"
	authorityDomainClass = "me.hcl.seekin.Auth.Role.Role"
	requestMapClass = "me.hcl.seekin.Auth.RequestMap"
	
	useRequestMapDomainClass = false
	useControllerAnnotations = true 
	
	defaultRole = "ROLE_ROLE"
	
	authenticationFailureUrl = "/user/authFail?login_error=1"
	loginFormUrl = "/user/auth" 
	errorPage = "/user/accessDenied"
	userName = "email"
	password = "password"


	// Mail conf
	useMail = false
	/*
	mailHost = 'smtp.free.fr'
	mailUsername = 'seekin.hcl@free.fr'
	mailPassword = 'hcljunior'
	mailProtocol = 'smtp'
	mailFrom = 'webmaster@hcl.me'
	mailPort = 25
	*/


       controllerAnnotationStaticRules = [
          '/':['IS_AUTHENTICATED_REMEMBERED'],
          '/**/js/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
          '/**/css/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
          '/**/images/**': ['IS_AUTHENTICATED_ANONYMOUSLY'],
          '/**': ['IS_AUTHENTICATED_REMEMBERED'],
       ]

	
}
