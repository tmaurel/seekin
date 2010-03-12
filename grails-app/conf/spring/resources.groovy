// Place your Spring DSL code here

import me.hcl.seekin.Auth.SeekinDaoImpl
import org.codehaus.groovy.grails.plugins.springsecurity.AuthorizeTools

def conf = AuthorizeTools.securityConfig.security
if (!conf || !conf.active) {
        println '[active=false] Spring Security not loaded'
        return
}


beans = {    
    /** userDetailsService */
    userDetailsService(SeekinDaoImpl) {
            usernameFieldName = conf.userName
            passwordFieldName = conf.password
            enabledFieldName = conf.enabled
            authorityFieldName = conf.authorityField
            loginUserDomainClass = conf.loginUserDomainClass
            relationalAuthoritiesField = conf.relationalAuthorities
            authoritiesMethodName = conf.getAuthoritiesMethod
            sessionFactory = ref('sessionFactory')
            authenticateService = ref('authenticateService')
    }
}