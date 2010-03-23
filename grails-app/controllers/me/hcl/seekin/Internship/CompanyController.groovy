package me.hcl.seekin.Internship

import grails.converters.JSON
import jofc2.model.Chart
import jofc2.model.axis.YAxis
import jofc2.model.axis.XAxis
import jofc2.model.axis.Label
import jofc2.model.elements.PieChart


class CompanyController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]


    def getAddress = {
        def companyInstance = Company.get(params.id)
        if (companyInstance) {
            def internship = Internship.findByCompany(companyInstance)
            if(internship?.address)
            {
                def address = [
                    [
                        key     :   'street',
                        value   :   internship.address.street
                    ],
                    [
                        key     :   'town',
                        value   :   internship.address.town
                    ],
                    [
                        key     :   'zipCode',
                        value   :   internship.address.zipCode
                    ]
                ]

                render address as JSON
            }
            
        }
    }

    def list = {
    }


    def show = {
        def companyInstance = Company.get(params.id)
        if (!companyInstance) {
            flash.message = "company.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Company not found with id ${params.id}"
            redirect(action: "list")
        }
        else {

            def addresses = new HashSet()
            companyInstance.internships.each {
                if(it?.address)
                    addresses.add it?.address
            }

            println addresses

            def phones = new HashSet()
            companyInstance.internships.each {
                if(it?.phone)
                    phones.add it?.phone
            }

            return [companyInstance: companyInstance, addresses: addresses, phones: phones]
        }
    }

    def edit = {
        def companyInstance = Company.get(params.id)
        if (!companyInstance) {
            flash.message = "company.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Company not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [companyInstance: companyInstance]
        }
    }

    def update = {
        def companyInstance = Company.get(params.id)
        if (companyInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (companyInstance.version > version) {

                    companyInstance.errors.rejectValue("version", "company.optimistic.locking.failure", "Another user has updated this Company while you were editing")
                    render(view: "edit", model: [companyInstance: companyInstance])
                    return
                }
            }
            companyInstance.properties = params
            if (!companyInstance.hasErrors() && companyInstance.save()) {
                flash.message = "company.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Company ${params.id} updated"
                redirect(action: "show", id: companyInstance.id)
            }
            else {
                render(view: "edit", model: [companyInstance: companyInstance])
            }
        }
        else {
            flash.message = "company.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Company not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def companyInstance = Company.get(params.id)
        if (companyInstance) {
            try {
                companyInstance.delete()
                flash.message = "company.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Company ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "company.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Company ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "company.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Company not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
        def list = Company.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {

            ret << [
                name:it.name,
                urlID:it.id
            ]
        }

        def data = [
                totalRecords: Company.count(),
                results: ret
        ]

        render data as JSON
    }

    def listCompanyAsJSON = {
        def list = Company.findAllByNameLike("${params.query}%")
        def jsonList = list.collect { [ id: it.id, name: it.name ] }
        def jsonResult = [
            result: jsonList
        ]
        render jsonResult as JSON

    }

    def piechart = {
        PieChart p = new PieChart()
                        .setAnimate(true)
                        .setStartAngle(35)
                        .setBorder(2)
                        .setAlpha(0.6f)                        
                        .setTooltip("#val# of #total#<br>#percent# of 100%")



        //.addSlice(6.5f, "hello (6.5)")
        def companies = Internship.createCriteria().list() {
            projections {
                groupProperty('company')
                count('company', 'count')
            }
            eq('isApproval', true)
            maxResults(10)
            order('count','desc')
        }.each {
            p.addSlice(it[1], it[0].name)
        }

        Chart c = new Chart(message(code:'company.internship.repartition')).addElements(p).setBackgroundColour('#f2f2f2')
        render c;
    }

}
