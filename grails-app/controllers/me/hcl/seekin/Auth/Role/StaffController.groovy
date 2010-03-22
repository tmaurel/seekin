package me.hcl.seekin.Auth.Role

import grails.converters.JSON
import org.codehaus.groovy.grails.plugins.springsecurity.Secured


class StaffController {

    def show = {
        def staffInstance = Staff.get(params.id)
        if (!staffInstance) {
            flash.message = "staff.not.found"
            flash.args = [params.id]
            redirect uri:'/'
        }
        else {
            return [staffInstance: staffInstance]
        }
    }

}
