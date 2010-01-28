import me.hcl.seekin.Auth.User
import org.codehaus.groovy.grails.web.context.ServletContextHolder
import org.springframework.context.ApplicationContext
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes

def appContext = ServletContextHolder.getServletContext().getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT) 
def authenticateService = appContext.getBean("authenticateService")


include "roles"

fixture {
	nadir(User, username : "nadir", userRealName : "Nadir Boukeffa", passwd : authenticateService.encodePassword("nadir"), enabled : "true", email : "nadir.boukeffa@gmail.com" , authorities : [adminRole, studentRole, externalRole])
	alexis(User, username : "alexis", userRealName : "Alexis Plantin", passwd : authenticateService.encodePassword("alexis"), enabled : "true", email : "alexis.plantin@gmail.com" , authorities : [adminRole, studentRole])
	thomas(User, username : "thomas", userRealName : "Thomas Morel", passwd : authenticateService.encodePassword("thomas"), enabled : "true", email : "neoseifer@gmail.com" , authorities : [adminRole, studentRole])
}