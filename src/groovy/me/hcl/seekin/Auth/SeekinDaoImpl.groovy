package me.hcl.seekin.Auth

import org.codehaus.groovy.grails.plugins.springsecurity.GrailsDaoImpl
import org.apache.log4j.Logger
import org.springframework.security.GrantedAuthority
import org.springframework.security.GrantedAuthorityImpl
import org.springframework.security.userdetails.UserDetails
import org.springframework.security.userdetails.UserDetailsService
import org.springframework.security.userdetails.UsernameNotFoundException
import org.springframework.dao.DataAccessException
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsWebApplicationObjectSupport
import org.codehaus.groovy.grails.plugins.springsecurity.GrailsUserImpl
import me.hcl.seekin.Formation.Promotion
import me.hcl.seekin.Auth.Role.Student
/**
 *
 * @author Thomas Maurel
 */
class SeekinDaoImpl extends GrailsDaoImpl {

    
	/**
	 * Create the {@link UserDetails} instance. Subclasses can override to inherit core functionality
	 * but determine the concrete class without reimplementing the entire class.
	 * @param username the username
	 * @param password the password
	 * @param enabled set to <code>true</code> if the user is enabled
	 * @param authorities the authorities that should be granted to the caller
	 * @param user  the user domain instance
	 * @return  the instance
	 */
	protected UserDetails createUserDetails(
			String username, String password, boolean enabled,
			GrantedAuthority[] authorities, Object user) {

                def accountNonExpired = true
                def isStudent = false
                authorities.each {
                    if(it.getAuthority() == "ROLE_STUDENT")
                        isStudent = true
                }

                if(isStudent)
                {
                    def student = Student.findByUser(user)
                    def promotion = Promotion.getCurrentForStudent(student)
                    if(!promotion)
                    {
                        accountNonExpired = false
                    }
                }
                
		new GrailsUserImpl(
				username, password, enabled,
				accountNonExpired, true, true, authorities, user)
	}
	
}

