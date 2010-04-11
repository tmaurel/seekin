package me.hcl.seekin.Util

import org.codehaus.groovy.grails.plugins.springsecurity.Secured

class AboutController {

    static navigation = [
            group:'menu',
            order:5,
            title:'about',
            action:'index'
    ]


    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def index = {

    }

}
