import me.hcl.seekin.Auth.User
import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.springframework.context.ApplicationContext
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes

def appContext = ServletContextHolder.getServletContext().getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT) 
def authenticateService = appContext.getBean("authenticateService")


include "roles"

fixture {
	nadir(User, email : "nadir.boukeffa@gmail.com" , password : authenticateService.encodePassword("nadir"), enabled : "true", authorities : [adminRole, studentRole, externalRole])
	alexis(User, email : "alexis.plantin@gmail.com" , password : authenticateService.encodePassword("alexis"), enabled : "true", authorities : [adminRole, studentRole])
	thomas(User, email : "neoseifer@gmail.com" , password : authenticateService.encodePassword("thomas"), enabled : "true", authorities : [adminRole, studentRole])
}