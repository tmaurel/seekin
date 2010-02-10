import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

class BootStrap {

	 def fixtureLoader
	
	 def navigationService
	
     def init = { servletContext ->



		// Main Menu
		navigationService.registerItem('menu', [controller:'user', action:'index', title:'home', id:'home'])

		// Bottom Bar
		navigationService.registerItem('bar_left', [controller:'test', action:'index', title:'profil', id:'profil'])
		navigationService.registerItem('bar_right', [controller:'test', action:'index', title:'contact', id:'contact'])

		// Load Fixtures
		if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) {
			fixtureLoader.load("students","externals","staffs","users","companies","reports","convocations","internships")
		}
		
     }
     def destroy = {
     }
} 
