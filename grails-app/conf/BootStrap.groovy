import org.springframework.context.ApplicationContext;
import org.codehaus.groovy.grails.web.context.ServletContextHolder;
import org.codehaus.groovy.grails.web.servlet.GrailsApplicationAttributes;



class BootStrap {

     def init = { servletContext ->
		def appContext = ServletContextHolder.getServletContext().getAttribute(GrailsApplicationAttributes.APPLICATION_CONTEXT) 
		def navigationService = appContext.getBean("navigationService")

		// Main Menu
		navigationService.registerItem('menu', [controller:'login', action:'index', title:'home', id:'home'])


		// Bottom Bar
		navigationService.registerItem('bar_left', [controller:'test', action:'index', title:'profil', id:'profil'])
		
		navigationService.registerItem('bar_right', [controller:'test', action:'index', title:'contact', id:'contact'])


		
     }
     def destroy = {
     }
} 
