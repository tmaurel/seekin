package me.hcl.seekin

import grails.converters.JSON

class CompanyController {
    def scaffold = true

    def listCompanyAsJSON = {
        def list = Company.findAllByNameLike("${params.query}%")
        def jsonList = list.collect { [ id: it.id, name: it.name ] }
        def jsonResult = [
            result: jsonList
        ]
        render jsonResult as JSON

    }

}
