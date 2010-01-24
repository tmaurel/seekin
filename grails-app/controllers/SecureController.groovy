import org.codehaus.groovy.grails.plugins.springsecurity.Secured

class SecureController {
	@Secured(['ROLE_ADMIN'])
    def index = { }
}
