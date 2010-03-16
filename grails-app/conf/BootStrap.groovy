import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import org.springframework.context.i18n.LocaleContextHolder as LCH
import java.util.Locale

class BootStrap {

     def fixtureLoader
	
     def init = { servletContext ->

		// Load Fixtures
		if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) {
			fixtureLoader.load("settings","students","externals","staffs", "admins","managers","users","companies","reports","convocations","internships","links", "formations", "offers")
		}

		Date.metaClass.formatDate = { ->

			def locale = LCH.getLocale()
			def pattern
			if(locale.toString() == "fr") {
				pattern = "dd/MM/yyyy"
			}
			else {
				pattern = "MM/dd/yyyy"
			}

			return delegate.format(pattern)
		}

		Date.metaClass.formatDateHour = { ->

			def locale = LCH.getLocale()
			def pattern
			if(locale.toString() == "fr") {
				pattern = "dd/MM/yyyy HH:mm"
			}
			else {
				pattern = "MM/dd/yyyy HH:mm"
			}

			return delegate.format(pattern)
		}
		
     }
     def destroy = {
     }
} 
