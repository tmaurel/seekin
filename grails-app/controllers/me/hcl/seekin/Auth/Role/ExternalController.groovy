package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Auth.User
import org.codehaus.groovy.grails.plugins.springsecurity.Secured


import grails.converters.JSON
class ExternalController {

    def authenticateService

    def show = {

        def userInstance = authenticateService.userDomain()
        def user = User.get(params.id)
        
        // Externals people can not see public profile of the others externals
        if(authenticateService.ifAnyGranted("ROLE_EXTERNAL") && userInstance?.id != user?.id)
        {
            redirect controller:'user', action:'accessDenied'
        }
        else
        {
            
            def externalInstance = External.findByUser(user)
            if (!externalInstance) {
                    flash.message = "external.not.found"
                    flash.args = [params.id]
                    redirect uri:'/'
            }
            else {
                    return [externalInstance: externalInstance]
            }
        }
    }

}
