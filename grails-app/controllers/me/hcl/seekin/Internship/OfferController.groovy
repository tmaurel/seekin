package me.hcl.seekin.Internship



import grails.converters.JSON
import me.hcl.seekin.Internship.Company
import org.hibernate.LockMode
import me.hcl.seekin.Formation.Promotion
import org.codehaus.groovy.grails.plugins.springsecurity.Secured
import me.hcl.seekin.Formation.Millesime
import me.hcl.seekin.Auth.Role.Student
import me.hcl.seekin.Ressource.Document

class OfferController {

    def authenticateService
    def sessionFactory
    def fileService

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {

        /* Controls if some offers which wait for Validation are validated by examinate params */
        def offer
        params.each {
            if(it.key.contains("validate_") && it.value == "on") {
                /* Get the offer and do the mofification before saving */
                offer = Offer.get(it.key.split("_")[1].toInteger())
                offer.validated = true
                offer.save(flush:true)
            }
        }

        /* If the user is not logged in, we redirect him at the index of seekin */
        def status
        if(!authenticateService.isLoggedIn()) {
            redirect(controller: "user", action: "index")
        }
        /* Build a status list depending on roles */
        else {
            /* If the user is the admin or a formation manager, status we want to display are all the status affiliated to a current offer */
            if(authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_FORMATIONMANAGER")) {
                status = new HashSet()
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        status.add it2.getStatus()
                    }
                }
            }
            /* If the user is an external or a member staff, status we want to display are all the status affiliated to the offers created by the current user */
            else if(authenticateService.ifAnyGranted("ROLE_EXTERNAL,ROLE_STAFF")) {
                def userInstance = authenticateService.userDomain()
                sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
                status = new HashSet()
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        if(it2?.author.id == userInstance.id) {
                            status.add it2.getStatus()
                        }
                    }
                }
                /* If the user is staff member we add the status offer.validate to aim the possibility of viewing all validated offers */
                if(authenticateService.ifAnyGranted("ROLE_STAFF")) {
                    status.add 'offer.validated'        
                }
            }
            /* If we are in this section the user is a student and will only see validated offers */
            else
                status = ['offer.validated']
            
		    render(view: "list", model: [status: status])
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER','ROLE_STAFF','ROLE_EXTERNAL'])
    def create = {
        def offerInstance = new Offer()
        offerInstance.properties = params
        /* Build the list with all current promotions for the select markup */
        def promotionInstance = Promotion.getCurrents().collect {
            [
                    id: it.id,
                    value: it.toString()
            ]
        }
        return [offerInstance: offerInstance, promotionInstance: promotionInstance]
    }

    def save = {

        /* Build the offer with correct parameter and save it*/
        def offerInstance = new Offer(params)

        /* Build the list with all current promotions for the select markup */
        def promotionInstance = Promotion.getCurrents().collect {
            [
                    id: it.id,
                    value: it.toString()
            ]
        }

        /* Offer creation need a company so we verify if the company type by the user exists and create and save it if not */
        def company
        if(params.companyName != null && params.companyName != "")
        {
            if(Company.countByName(params.companyName) == 0) {
              company = new Company()
              company.name = params.companyName
            }
            else {
              company = Company.findByName(params.companyName)
            }
            offerInstance.company = company
        }

        /* We get the user logged in in userInstance to identifiate the author */
        def userInstance = authenticateService.userDomain()
        sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
        offerInstance.company = company
        offerInstance.validated = false
        offerInstance.assignated = false
        offerInstance.author = userInstance

        if (!offerInstance.hasErrors()) {

            offerInstance = offerInstance.merge()

            if(request.getFile( 'data' ).getSize() > 0)
            {
                def document = new Document()
                document.title = params.subject
                document.fileData = fileService.createFile(request.getFile( 'data' ))
                offerInstance.file = document
                document.save(flush: true)
            }

            flash.message = "offer.created"
            flash.args = [offerInstance.id]
            flash.defaultMessage = "Offer ${offerInstance.id} created"
            redirect(action: "show", id: offerInstance.id)
        }
        else {
            render(
                    view: "create",
                    model: [
                            offerInstance: offerInstance,
                            promotionInstance: promotionInstance,
                            company: params.companyName
                    ]
            )
        }
        
    }

    def show = {
        /* We get the user logged in to verify if he is authorized to show an offer */
        def userInstance = authenticateService.userDomain()
        sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
        Boolean showable = false
        
        /* Get the offer with id parameter */
        def offerInstance = Offer.get(params.id)
        /* If the user have an external role, he is allowed to show only offers which he had created */
        if(authenticateService.ifAnyGranted("ROLE_EXTERNAL")) {
            if(offerInstance?.author == userInstance) {
                showable = true
            }
        }
        /* If the user is the administrator, he can show all offers */
        else if(authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_FORMATIONMANAGER")) {
            showable = true
        }
        /* If the user is a staff's member, he is allowed to show offers which he had created and offers that are validated */
        else if(authenticateService.ifAnyGranted("ROLE_STAFF")) {
            if(offerInstance?.author == userInstance || (offerInstance.validated == true && offerInstance.assignated == false)) {
                showable = true
            }
        }
        /* If the user is a student, he can show all offers which are validated and correspond to his promotion */
        else if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {
            if(offerInstance?.validated == true && offerInstance?.assignated == false) {
                def student = Student.findByUser(userInstance)
                def currentPromo = Promotion.getCurrentForStudent(student)
                if(offerInstance.promotions.contains(currentPromo)) {
                    showable = true
                }
            }
        }

        /* If the offer doesn't exist or is not showable, we return a flash message */
        if (!offerInstance || !showable) {
            flash.message = "offer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Offer not found with id ${params.id}"
            redirect(action: "list")
        }
        /* Else we return our instance */
        else if(showable) {
            return [offerInstance: offerInstance]
        }
    }

    @Secured(['ROLE_EXTERNAL','ROLE_FORMATIONMANAGER','ROLE_STAFF','ROLE_ADMIN'])
    def edit = {
        /* We get the user logged in to verify if he is authorized to edit an offer */
        def userInstance = authenticateService.userDomain()
        sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
        Boolean editable = false

        /* Get the offer with id parameter */
        def offerInstance = Offer.get(params.id)
        def promotionInstance
        def selectedPromotions = offerInstance.promotions.collect { it.id }
        /* If the user is the author of the offer or the administrator */
        if(offerInstance?.author == userInstance || authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_FORMATIONMANAGER")) {
            /* Build a list of the current promotion */
            promotionInstance = Promotion.getCurrents().collect {
                [
                        id: it.id,
                        value: it.toString()
                ]
            }
            /* If the offer is not validated, we verify if the offer is associate to a current promotion */
            if (offerInstance?.validated == false) {
                def currentPromo = offerInstance.promotions.find {
                    it.millesime == Millesime.getCurrent()
                }
                if(currentPromo != null) {
                    editable = true
                }
            }
        }
        /* If the offer is editable, we return our needed instances */
        if(editable) {
                return [offerInstance: offerInstance, promotionInstance: promotionInstance, selectedPromotions:selectedPromotions]
        }
        /* Else we return a flash message */
        else {
            flash.message = "offer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Offer not found with id ${params.id}"
            redirect(action: "list")
        }

    }

    def update = {

        /* Build the list with all current promotions for the select markup */
        def promotionInstance = Promotion.getCurrents().collect {
            [
                    id: it.id,
                    value: it.toString()
            ]
        }


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

            offerInstance.subject = params.subject
            offerInstance.description = params.description
            offerInstance.beginAt = params.beginAt
            offerInstance.length = params.length.toInteger()
            offerInstance.validated = (params.validated)?true:false
            offerInstance.reason = null

            if (!offerInstance.hasErrors() && offerInstance.save()) {

                def promos = offerInstance.promotions.collect {
                    it.id
                }
                promos.each { Promotion.get(it)?.removeFromOffers(offerInstance) }
                params.promotions.each { Promotion.get(it)?.addToOffers(offerInstance) }

                if(request.getFile( 'data' ).getSize() > 0)
                {
                    if(offerInstance.file)
                        offerInstance.file.delete()

                    def document = new Document()
                    document.title = params.subject
                    document.fileData = fileService.createFile(request.getFile( 'data' ))
                    document.save(flush: true)
                    offerInstance.file = document
                }


                flash.message = "offer.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Offer ${params.id} updated"
                redirect(action: "show", id: offerInstance.id)
            }
            else {
                render(view: "edit", model: [offerInstance: offerInstance, promotionInstance: promotionInstance])
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

    /* It is used when an offer is deny by the administrator */
    def deny = {
        /* Get the offer and set reason with params.reason before saving and redirect the user to list */
        def offerInstance = Offer.get(params.id)
        if (offerInstance) {
            offerInstance.reason = params.reason
            offerInstance.save()
            redirect(action: "list")
        }
        /* Else we return a flash message */
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
            /* If the user is the admin or a formation manager, status we want to display currents offers */
            if(authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_FORMATIONMANAGER")) {
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        if(it2.getStatus() == params.status) {
                            status.add it2.id
                        }
                    }
                }
                list = status
            }
            /* If the user is a staff's member we get all currents offers which were created by the current user and all currents offers which are validated*/
            else if(authenticateService.ifAnyGranted("ROLE_STAFF")) {
                def userInstance = authenticateService.userDomain()
                sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        if(it2.author.id == userInstance.id && it2.getStatus() == params.status) {
                            status.add it2.id
                        }
                    }
                }
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        if(it2.getStatus() == 'offer.validated' && it2.getStatus() == params.status) {
                            status.add it2.id
                        }
                    }
                }
                list = status
            }
            /* If the user is a student he will only see currents validated offers */
            else if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {
                def userInstance = authenticateService.userDomain()
                sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
                def student = Student.findByUser(userInstance)
                def currentPromo = Promotion.getCurrentForStudent(student)
                currentPromo.offers.each() { it2 ->
                    if(it2.getStatus() == params.status) {
                        list.add it2.id
                    }
                }
            }
            /* If the user is an external he will only see currents offers he had created */
            else if(authenticateService.ifAnyGranted("ROLE_EXTERNAL")) {
                def userInstance = authenticateService.userDomain()
                sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
                list = Offer.findAllByAuthor(userInstance)
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        if(it2.author == userInstance && it2.getStatus() == params.status) {
                            status.add it2.id
                        }
                    }
                }
                list = status
            }

            //println list
            def list2
            if(list.size() > 0) {
                list2 = Offer.createCriteria().list(params) {
                    'in'('id', list)
                }
            }
        
            list2.each {
                ret << [
                   subject:it.subject,
                   company:it.company.toString(),
                   beginAt:it.beginAt?.formatDate(),
                   length:it.length,
                   status:message(code:it.getStatus()),
                   urlID: it.id
                ]
            }

            def data = [
                    totalRecords: list.size(),
                    results: ret
            ]

            render data as JSON
        }

}
