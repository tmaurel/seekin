package me.hcl.seekin.Internship



import grails.converters.JSON
import me.hcl.seekin.Company
import org.hibernate.LockMode
import me.hcl.seekin.Formation.Promotion

class OfferController {

    def authenticateService
    def sessionFactory

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {

        def offer
        params.each {
            if(it.key.contains("validate_") && it.value == "on") {
                offer = Offer.get(it.key.split("_")[1].toInteger())
                offer.validated = true
                offer.save(flush:true)
            }
        }
        
        def status
        if(!authenticateService.isLoggedIn()) {
            redirect(controller: "user", action: "index")
        }
        else {
            if(authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_FORMATIONMANAGER")) {
                status = new HashSet()
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        status.add it2.getStatus()
                    }
                }
            }
            else if(authenticateService.ifAnyGranted("ROLE_EXTERNAL")) {
                def userInstance = authenticateService.userDomain()
                sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
                status = new HashSet()
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        if(it2.author.toString() == userInstance.toString()) {
                            status.add it2.getStatus()
                        }
                    }
                }
            }
            else
                status = ['offer.validated']
            
		    render(view: "list", model: [status: status])
        }
    }

    def create = {
        def offerInstance = new Offer()
        offerInstance.properties = params
        return [offerInstance: offerInstance]
    }

    def save = {

        def company
        if(Company.countByName(params.companyName) == 0) {
          company = new Company()
          company.name = params.companyName
          company.save()
        }
        else {
          company = Company.findByName(params.companyName)
        }

        def offerInstance = new Offer(params)
        offerInstance.company = company
        if (!offerInstance.hasErrors() && offerInstance.save()) {
            flash.message = "offer.created"
            flash.args = [offerInstance.id]
            flash.defaultMessage = "Offer ${offerInstance.id} created"
            redirect(action: "show", id: offerInstance.id)
        }
        else {
            render(view: "create", model: [offerInstance: offerInstance])
        }
    }

    def show = {
        def offerInstance = Offer.get(params.id)
        if (!offerInstance) {
            flash.message = "offer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Offer not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [offerInstance: offerInstance]
        }
    }

    def edit = {
        def offerInstance = Offer.get(params.id)
        if (!offerInstance) {
            flash.message = "offer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Offer not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [offerInstance: offerInstance]
        }
    }

    def update = {
        def offerInstance = Offer.get(params.id)
        if (offerInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (offerInstance.version > version) {
                    
                    offerInstance.errors.rejectValue("version", "offer.optimistic.locking.failure", "Another user has updated this Offer while you were editing")
                    render(view: "edit", model: [offerInstance: offerInstance])
                    return
                }
            }
            offerInstance.properties = params
            if (!offerInstance.hasErrors() && offerInstance.save()) {
                flash.message = "offer.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Offer ${params.id} updated"
                redirect(action: "show", id: offerInstance.id)
            }
            else {
                render(view: "edit", model: [offerInstance: offerInstance])
            }
        }
        else {
            flash.message = "offer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Offer not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def offerInstance = Offer.get(params.id)
        if (offerInstance) {
            try {
                offerInstance.delete()
                flash.message = "offer.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Offer ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "offer.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Offer ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "offer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Offer not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
            def list = []
            def ret = []
            def status = new HashSet()
            response.setHeader("Cache-Control", "no-store")
            if(authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_FORMATIONMANAGER")) {
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        if(it2.getStatus() == params.status) {
                            status.add it2
                        }
                    }
                }
                list = status
            }
            else if(authenticateService.ifAnyGranted("ROLE_STAFF")) {
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        if(it2.getStatus() == params.status) {
                            status.add it2
                        }
                    }
                }
                list = status
            }
            else if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {
                def userInstance = authenticateService.userDomain()
                sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
                def student = userInstance.authorities.find {
                    it.getRoleName() == "ROLE_STUDENT"
                    def currentPromo = Promotion.getCurrentForStudent(it)
                    currentPromo.offers.each() { it2 ->
                        if(it2.getStatus() == params.status) {
                            list.add it2
                        }
                    }
                }
            }
            else if(authenticateService.ifAnyGranted("ROLE_EXTERNAL")) {
                def userInstance = authenticateService.userDomain()
                sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
                list = Offer.findAllByAuthor(userInstance)
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        if(it2.author == userInstance && it2.getStatus() == params.status) {
                            status.add it2
                        }
                    }
                }
                list = status
            }
        
            list.each {
                ret << [
                   id:it.id,
                   subject:it.subject,
                   company:it.company.toString(),
                   beginAt:it.beginAt?.formatDate(),
                   length:it.length,
                   //status:it.validated==false?(it.reason==null?message(code:'offer.waitForValidation'):message(code:'offer.unvalidated')):(it.assignated==false?message(code:'offer.validated'):message(code:'offer.assignated')),
                   status:message(code:it.getStatus()),
                   urlID: it.id
                ]
            }

            def data = [
                    totalRecords: Offer.count(),
                    results: ret
            ]

            render data as JSON
        }

}
