import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.codehaus.groovy.grails.commons.GrailsApplication
import grails.util.GrailsUtil
import org.springframework.context.i18n.LocaleContextHolder as LCH
import java.text.SimpleDateFormat
import java.util.Locale

class BootStrap {

     def fixtureLoader
	
     def init = { servletContext ->

		// Load Fixtures
		if (GrailsUtil.environment == GrailsApplication.ENV_DEVELOPMENT) {
			fixtureLoader.load("settings","students","externals","staffs","users","companies","reports","convocations","internships","links", "formations")

            Date.metaClass.formatDate = { ->

                def locale = LCH.getLocale()
                def pattern
                if(locale.toString() == "fr") {
                    pattern = "dd/MM/yyyy"
                }
                else {
                    pattern = "MM/dd/yyyy"
                }
                def formatter = new SimpleDateFormat(pattern, locale);
                println delegate
                return formatter.format(delegate)
            }
		}
		
     }
     def destroy = {
     }
} 
