import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil

class BootStrap {

     def fixtureLoader
	
     def init = { servletContext ->

		// Load Fixtures
		if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) {
			fixtureLoader.load("settings","students","externals","staffs","users","companies","reports","convocations","internships","links", "formations")
		}
		
     }
     def destroy = {
     }
} 
