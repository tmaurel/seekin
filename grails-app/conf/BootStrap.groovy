import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

class BootStrap {

	 def fixtureLoader
	
	 def navigationService
	
     def init = { servletContext ->

		// Main Menu
		navigationService.registerItem('menu', [controller:'user', action:'index', title:'home', id:'home'])
		navigationService.registerItem('menu', [controller:'student', action:'index', title:'student'])
		navigationService.registerItem('menu', [controller:'staff', action:'index', title:'staff'])
		navigationService.registerItem('menu', [controller:'external', action:'index', title:'external'])
		navigationService.registerItem('menu', [controller:'internship', action:'index', title:'internship'])
		navigationService.registerItem('menu', [controller:'link', action:'index', title:'link'])
		navigationService.registerItem('menu', [controller:'settings', action:'index', title:'settings'])
		navigationService.registerItem('menu', [controller:'user', action:'logout', title:'logout'])


		// Bottom Bar
		navigationService.registerItem('bar_left', [controller:'test', action:'index', title:'profil', id:'profil'])
		navigationService.registerItem('bar_right', [controller:'test', action:'index', title:'contact', id:'contact'])

		// Load Fixtures
		if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) {
			fixtureLoader.load("settings","students","externals","staffs","users","companies","reports","convocations","internships","links")
		}
		
     }
     def destroy = {
     }
} 
