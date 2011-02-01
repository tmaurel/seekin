package me.hcl.seekin.Internship



import grails.converters.JSON
import me.hcl.seekin.Internship.Company
import org.hibernate.LockMode
import me.hcl.seekin.Formation.Promotion
import org.codehaus.groovy.grails.plugins.springsecurity.Secured
import me.hcl.seekin.Formation.Millesime
import me.hcl.seekin.Auth.Role.*


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

        def userInstance = authenticateService.userDomain()

        if(authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_FORMATIONMANAGER")) {
            params.each {
                if(it.key.contains("validate_") && it.value == "on") {
                    /* Get the offer and do the mofification before saving */
                    offer = Offer.get(it.key.split("_")[1].toInteger())
                    offer.validated = true
                    offer.save(flush:true)
                }
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
            if(authenticateService.ifAnyGranted("ROLE_ADMIN")) {
                status = new HashSet()
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        status.add it2.getStatus()
                    }
                }
            }

            else if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER")) {
                status = new HashSet()
                def manager = FormationManager.findByUser(userInstance)
                Promotion.getCurrentForFormation(manager?.formation)?.offers.each() {
                    it.each() { it2 ->
                        status.add it2.getStatus()
                    }
                }
            }
            /* If the user is an external or a member staff, status we want to display are all the status affiliated to the offers created by the current user */
            else if(authenticateService.ifAnyGranted("ROLE_EXTERNAL,ROLE_STAFF")) {
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

    @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER','ROLE_STAFF','ROLE_EXTERNAL'])
    def save = {

        /* Build the offer with correct parameter and save it*/
        def offerInstance = new Offer()

        offerInstance.subject = params.subject
        offerInstance.description = params.description
        offerInstance.beginAt = params.beginAt
        offerInstance.length = params.length.toInteger()

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

        /* An offer is directly validated if the author is an admin or a formation manager */
        if(authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_FORMATIONMANAGER")) {
                offerInstance.validated = true
        }
        else {
                offerInstance.validated = false
        }

        offerInstance.assignated = false
        
        offerInstance.author = userInstance

        offerInstance.validate()

        if (params.promotions == null || params.promotions == "") {
            offerInstance.errors.rejectValue(
                'promotions',
                'offer.promotions.nullable.error'
            )
        }

        if (!offerInstance.hasErrors()) {
            if(request.getFile( 'data' ).getSize() > 0)
            {
                def document = new InternshipSubjectFile()
                document.title = params.subject
                document.fileData = fileService.createFile(request.getFile( 'data' ))
                offerInstance.file = document
            }
            if ((offerInstance = offerInstance?.save(flush: true))) 
            {
                params.promotions.each {
                    def promo = Promotion.get(it)
                    if(promo)
                        promo.addToOffers(offerInstance)
                }

                flash.message = "offer.created"
                flash.args = [offerInstance.id]
                flash.defaultMessage = "Offer ${offerInstance.id} created"
                redirect(action: "show", id: offerInstance.id)
            }
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
        Boolean editable = false
        Boolean deletable = false
        
        /* Get the offer with id parameter */
        def offerInstance = Offer.get(params.id)
        if(offerInstance) {
            /* If the user have an external role, he is allowed to show only offers which he had created */
            if(authenticateService.ifAnyGranted("ROLE_EXTERNAL")) {
                if(offerInstance?.author?.id == userInstance?.id) {
                    showable = true
                    editable = true
                    deletable = true

                    if (offerInstance?.validated == false) {
                        def currentPromo = offerInstance.promotions.find {
                            it.millesime == Millesime.getCurrent()
                        }
                        if(currentPromo == null) {
                            deletable = false
                            editable = false
                        }
                    }
                    else
                    {
                        deletable = false
                        editable = false
                    }

                }
            }
            /* If the user is the administrator, he can show all offers */
            else if(authenticateService.ifAnyGranted("ROLE_ADMIN")) {
                showable = true
                editable = true
                deletable = true

                if (offerInstance?.validated == false) {
                    def currentPromo = offerInstance.promotions.find {
                        it.millesime == Millesime.getCurrent()
                    }
                    if(currentPromo == null) {
                        editable = false
                    }
                }
                else
                {
                    editable = false
                }

            }
            else if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER")) {
                def manager = FormationManager.findByUser(userInstance)
                if(offerInstance?.promotions?.formation?.id?.contains(manager?.formation?.id))
                {
                    showable = true
                    editable = true
                    deletable = true

                    if (offerInstance?.validated == false) {
                        def currentPromo = offerInstance.promotions.find {
                            it.millesime == Millesime.getCurrent()
                        }
                        if(currentPromo == null) {
                            editable = false
                        }
                    }
                    else
                    {
                        editable = false
                    }
                }
            }
            /* If the user is a staff's member, he is allowed to show offers which he had created and offers that are validated */
            else if(authenticateService.ifAnyGranted("ROLE_STAFF")) {
                if(offerInstance?.author?.id == userInstance?.id) {
                    showable = true
                    editable = true
                    deletable = true

                    if (offerInstance?.validated == false) {
                        def currentPromo = offerInstance.promotions.find {
                            it.millesime == Millesime.getCurrent()
                        }
                        if(currentPromo == null) {
                            deletable = false
                            editable = false
                        }
                    }
                    else
                    {
                        deletable = false
                        editable = false
                    }
                }
                else if(offerInstance.validated == true && offerInstance.assignated == false) {
                    showable = true
                    editable = false
                    deletable = false
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
            return [offerInstance: offerInstance, editable: editable, deletable: deletable]
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
        def selectedPromotions
        if(offerInstance) {
            selectedPromotions = offerInstance.promotions.collect { it.id }
            /* If the user is the author of the offer or the administrator */
            if(authenticateService.ifAnyGranted("ROLE_ADMIN"))
                editable = true
            else if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
            {
                def manager = FormationManager.findByUser(userInstance)
                if(offerInstance?.promotions?.formation?.id?.contains(manager?.formation?.id))
                    editable = true
            }
            else if(offerInstance?.author?.id == userInstance?.id) {
                editable = true
            }

            /* If the offer is not validated, we verify if the offer is associate to a current promotion */
            if (offerInstance?.validated == false) {
                def currentPromo = offerInstance.promotions.find {
                    it.millesime == Millesime.getCurrent()
                }
                if(currentPromo == null) {
                    editable = false
                }
            }
            else
            {
                editable = false
            }
        }

        /* If the offer is editable, we return our needed instances */
        if(editable) {



                /* Build a list of the current promotion */
                promotionInstance = Promotion.getCurrents().collect {
                    [
                            id: it.id,
                            value: it.toString()
                    ]
                }

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

    @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER','ROLE_STAFF','ROLE_EXTERNAL'])
    def update = {


     /* We get the user logged in to verify if he is authorized to edit an offer */
        def userInstance = authenticateService.userDomain()
        sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
        Boolean editable = false

        /* Get the offer with id parameter */
        def offerInstance = Offer.get(params.id)
        def promotionInstance
        def selectedPromotions = offerInstance.promotions.collect { it.id }
        /* If the user is the author of the offer or the administrator */
        if(authenticateService.ifAnyGranted("ROLE_ADMIN"))
            editable = true
        else if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            def manager = FormationManager.findByUser(userInstance)
            if(offerInstance?.promotions?.formation?.id?.contains(manager?.formation?.id))
                editable = true
        }
        else if(offerInstance?.author?.id == userInstance?.id) {
            editable = true
        }

        /* If the offer is not validated, we verify if the offer is associate to a current promotion */
        if (offerInstance?.validated == false) {
            def currentPromo = offerInstance.promotions.find {
                it.millesime == Millesime.getCurrent()
            }
            if(currentPromo == null) {
                editable = false
            }
        }
        else
        {
            editable = false
        }

        if (offerInstance && editable) {
            
            /* Build a list of the current promotion */
            promotionInstance = Promotion.getCurrents().collect {
                [
                        id: it.id,
                        value: it.toString()
                ]
            }

            if (params.version) {
                def version = params.version.toLong()
                if (offerInstance.version > version) {
                    
                    offerInstance.errors.rejectValue("version", "offer.optimistic.locking.failure", "Another user has updated this Offer while you were editing")
                    render(view: "edit", model: [offerInstance: offerInstance, promotionInstance: promotionInstance])
                    return
                }
            }

            offerInstance.subject = params.subject
            offerInstance.description = params.description
            offerInstance.beginAt = params.beginAt
            offerInstance.length = params.length.toInteger()
            offerInstance.validated = (params.validated)?true:false
            offerInstance.reason = null

            def oldDoc = offerInstance.file

            if(request.getFile( 'data' ).getSize() > 0)
            {
                def document = new InternshipSubjectFile()
                document.title = params.subject
                document.fileData = fileService.createFile(request.getFile( 'data' ))
                offerInstance.file = document
            }

            if (!offerInstance.hasErrors() && offerInstance.save()) {

                def promos = offerInstance.promotions.collect {
                    it.id
                }
                promos.each { Promotion.get(it)?.removeFromOffers(offerInstance) }
                params.promotions.each { Promotion.get(it)?.addToOffers(offerInstance) }

                if(oldDoc) {
                    oldDoc.delete()
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

    @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER','ROLE_STAFF','ROLE_EXTERNAL'])
    def delete = {


        /* We get the user logged in to verify if he is authorized to edit an offer */
        def userInstance = authenticateService.userDomain()
        Boolean editable = false

        /* Get the offer with id parameter */
        def offerInstance = Offer.get(params.id)
        def promotionInstance
        def selectedPromotions = offerInstance.promotions.collect { it.id }
        /* If the user is the author of the offer or the administrator */
        if(authenticateService.ifAnyGranted("ROLE_ADMIN"))
            editable = true
        else if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            def manager = FormationManager.findByUser(userInstance)
            if(offerInstance?.promotions?.formation?.id?.contains(manager?.formation?.id))
                editable = true
        }
        else if(offerInstance?.author?.id == userInstance?.id) {
            editable = true

            /* If the offer is not validated, we verify if the offer is associate to a current promotion */
            if (offerInstance?.validated == false) {
                def currentPromo = offerInstance.promotions.find {
                    it.millesime == Millesime.getCurrent()
                }
                if(currentPromo == null) {
                    editable = false
                }
            }
            else
            {
                editable = false
            }
        }

        if (offerInstance && editable) {
            try {
                offerInstance.author = null
                offerInstance.company = null

                def promos = offerInstance.promotions.collect { it }

                promos.each {
                    it.removeFromOffers(offerInstance)
                }
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
    @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER'])
    def deny = {
        /* Get the offer and set reason with params.reason before saving and redirect the user to list */
        def offerInstance = Offer.get(params.id)
        def editable = false

        if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            def manager = FormationManager.findByUser(userInstance)
            if(offerInstance?.promotions?.formation?.id?.contains(manager?.formation?.id))
                editable = true
        }
        else
            editable = true

        if (offerInstance && editable) {
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
            def userInstance = authenticateService.userDomain()
			
            response.setHeader("Cache-Control", "no-store")
            /* If the user is the admin or a formation manager, status we want to display currents offers */
            if(authenticateService.ifAnyGranted("ROLE_ADMIN")) {
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        if(it2.getStatus() == params.status) {
                            status.add it2.id
                        }
                    }
                }
                list = status
            }

            else if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER")) {
                def manager = FormationManager.findByUser(userInstance)
                Promotion.getCurrentForFormation(manager?.formation)?.offers.each() {
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
                Promotion.getCurrents().offers.each() {
                    it.each() { it2 ->
                        if((it2.author.id == userInstance.id && it2.getStatus() == params.status) || (it2.getStatus() == 'offer.validated' && it2.getStatus() == params.status)) {
                            status.add it2.id
                        }
                    }
                }
                list = status
            }
            /* If the user is a student he will only see currents validated offers */
            else if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {
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
                Offer.findAllByAuthor(userInstance).each() {
                    if(it.getStatus() == params.status && it?.promotions?.millesime?.id?.contains(Millesime.getCurrent()?.id)) {
                        status.add it.id
                    }
                }
                list = status
            }

			def filter = {
				and {
				  'in'('id', list)
				  if(params.subject && params.subject != ''){
					ilike("subject", "${params.subject}%")
				  }
				}
			}
			
            def list2
            if(list.size() > 0) {
                list2 = Offer.createCriteria().list(params,filter)
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
                    totalRecords: list.size()>0?Offer.createCriteria().count(filter):0,
                    results: ret
            ]

            render data as JSON
        }

}
