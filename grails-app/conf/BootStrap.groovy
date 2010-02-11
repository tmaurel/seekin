import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

class BootStrap {

	 def fixtureLoader
	
	 def navigationService
	
     def init = { servletContext ->

		// Main Menu
		navigationService.registerItem('menu', [controller:'user', action:'index', order:'1', title:'home', id:'home'])
		navigationService.registerItem('menu', [controller:'student', action:'index', order:'2', title:'student'])
		navigationService.registerItem('menu', [controller:'staff', action:'index', order:'3', title:'staff'])
		navigationService.registerItem('menu', [controller:'external', action:'index', order:'4', title:'external'])
		navigationService.registerItem('menu', [controller:'internship', action:'index', order:'5', title:'internship'])
		navigationService.registerItem('menu', [controller:'link', action:'index', order:'6', title:'link'])
		navigationService.registerItem('menu', [controller:'settings', action:'index', order:'7', title:'settings'])
		navigationService.registerItem('menu', [controller:'user', action:'logout', order:'8', title:'logout'])


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
