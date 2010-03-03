package me.hcl.seekin.Internship



import grails.converters.JSON
import me.hcl.seekin.Auth.Role.Staff
import me.hcl.seekin.Auth.Role.Student
import me.hcl.seekin.Auth.User
import me.hcl.seekin.Auth.UserController
import me.hcl.seekin.Auth.Role.External
import me.hcl.seekin.Util.Address
import me.hcl.seekin.Company
import me.hcl.seekin.Formation.Millesime
import me.hcl.seekin.Formation.Promotion
import org.hibernate.LockMode

class InternshipController {
    
    def authenticateService
    def sessionFactory

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {

        /* Controls if some internships which wait for Validation are validated by examinate params */
        def internship
        params.each {
            if(it.key.contains("validate_") && it.value == "on") {
                /* Get the offer and do the mofification before saving */
                internship = Internship.get(it.key.split("_")[1].toInteger())
                internship.isApproval = true
                internship.save(flush:true)
            }
        }

        /* If the user is not logged in, we redirect him at the index of seekin */
        def status = new HashSet()
        if(!authenticateService.isLoggedIn()) {
            redirect(controller: "user", action: "index")
        }
        else {
            if(authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_FORMATIONMANAGER")) {
                //status.add 'internship.validated'
                //status.add 'internship.unvalidated'
                //status.add 'internship.waitForValidation'
                Internship.list().each {
                    status.add it.getStatus(new Staff())
                }
            }
            else if(authenticateService.ifAnyGranted("ROLE_STAFF")) {
                def userInstance = authenticateService.userDomain()
                sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
                def statusStaff = Staff.findByUser(userInstance)
                Internship.list().each {
                    if(it.getStatus(new Staff()) == 'internship.validated')
                        status.add it.getStatus(new Staff())
                    if(it.getStatus(statusStaff) == 'internship.mine')
                        status.add it.getStatus(statusStaff)                    
                }
                //status.add 'internship.validated'
                //status.add 'internship.mine'

            }
            else if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {
                Internship.list().each {
                    def userInstance = authenticateService.userDomain()
                    sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
                    if(it.student.user == userInstance) {
                        status.add it.getStatus(new Staff())
                    }
                }
            }
            else {
                Internship.list().each {
                    if(it.getStatus(new Staff()) == 'internship.validated')
                        status.add 'internship.validated'                  
                }
            }

		    render(view: "list", model: [status: status])
        }
    }

    def create = {
        def staff = Staff.list().collect {
            [
                    id: it.id,
                    value: it.user?.firstName + " " + it.user?.lastName
            ]
        }
        def student = Student.list().collect {
            [
                    id: it.id,
                    value: it.user?.firstName + " " + it.user?.lastName
            ]
        }
        def offer = Offer.list().collect {
            [
                    id: it.id,
                    value: it.subject
            ]
        }
        def internshipInstance
        if(params?.offer?.id != null) {
            def offerFromSelection = Offer.get(params.offer.id)
            internshipInstance = new Internship()
                internshipInstance.properties = params
                internshipInstance.subject = offerFromSelection.subject
                internshipInstance.beginAt = offerFromSelection.beginAt
                internshipInstance.length = offerFromSelection.length
                internshipInstance.company = offerFromSelection.company
                internshipInstance.fromOffer = true
                internshipInstance.description = offerFromSelection.description
        }
        else {
            internshipInstance = new Internship()
            internshipInstance.properties = params
        }
        return [internshipInstance: internshipInstance, staff: staff, student: student, offer: offer]
    }

    def save = {
        def internshipInstance = new Internship()
        def company
        if(Company.countByName(params.companyName) == 0) {
          company = new Company()
          company.name = params.companyName
          company.save()
        }
        else {
          company = Company.findByName(params.companyName)
        }

        def role = new External()
        role.company = Company.findByName(params.companyName)
        role.formerStudent = false

        def companyTutor

        if(User.countByEmail(params.email) == 0) {
            companyTutor = new User()
            companyTutor.properties = params
            companyTutor.password = UserController.generatePwd(8)
            companyTutor.addToAuthorities(role)
            companyTutor.save(flush:true)
        }
        else {
            companyTutor = User.findByEmail(params.email)
            role = External.findByUser(companyTutor)
        }
        internshipInstance.companyTutor = role

        internshipInstance.properties = params
        internshipInstance.company = company
        internshipInstance.millesime = Millesime.getCurrent()
        if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {
            def userInstance = authenticateService.userDomain()
            sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
            def statusStudent = Student.findByUser(userInstance)
            internshipInstance.student = statusStudent
            internshipInstance.isApproval = false
        }

        internshipInstance = internshipInstance.merge()
        if (!internshipInstance.hasErrors() && internshipInstance.save(flush:true)) {
            flash.message = "internship.created"
            flash.args = [internshipInstance.id]
            flash.defaultMessage = "Internship ${internshipInstance.id} created"
            redirect(action: "show", id: internshipInstance.id)
        }
        else {
            render(view: "create", model: [internshipInstance: internshipInstance])
        }
    }

    def show = {
        /* We get the user logged in to verify if he is authorized to show an internship */
        def userInstance = authenticateService.userDomain()
        sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
        Boolean showable = false

        /* Get the offer with id parameter */
        def internshipInstance = Internship.get(params.id)
        /* If the user have an external role, he is allowed to show only offers which he had created */
        if(authenticateService.ifAnyGranted("ROLE_EXTERNAL")) {
            if(internshipInstance?.companyTutor?.user == userInstance && internshipInstance?.isApproval == true) {
                showable = true
            }
        }
        /* If the user is the administrator, he can show all offers */
        else if(authenticateService.ifAnyGranted("ROLE_ADMIN")) {
            showable = true
        }
        /* If the user is a staff's member, he is allowed to show offers which he had created and offers that are validated */
        else if(authenticateService.ifAnyGranted("ROLE_STAFF")) {
            if(internshipInstance?.academicTutor?.user == userInstance && internshipInstance.isApproval == true) {
                showable = true
            }
        }
        /* If the user is a student, he can show all offers which are validated and correspond to his promotion */
        else if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {
            if(internshipInstance?.student?.user ==  userInstance) {
                showable = true
            }
        }

        /* If the offer doesn't exist or is not showable, we return a flash message */
        if (!internshipInstance || !showable) {
            flash.message = "internship.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Internship not found with id ${params.id}"
            redirect(action: "list")
        }
        /* Else we return our instance */
        else if(showable) {
            return [internshipInstance: internshipInstance]
        }
    }

    def edit = {
        /* We get the user logged in to verify if he is authorized to edit an internship */
        def userInstance = authenticateService.userDomain()
        sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
        Boolean editable = false

        /* Get the internship with id parameter */
        def internshipInstance = Internship.get(params.id)
        /* If the internship is not validated, it is editable */
        if (internshipInstance?.isApproval == false) {
            editable = true
        }

        /* If the offer is editable, we return our needed instance */
        if(editable) {
                return [internshipInstance: internshipInstance]
        }
        /* Else we return a flash message */
        else {
            flash.message = "internship.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Internship not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def update = {
        def internshipInstance = Internship.get(params.id)
        if (internshipInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (internshipInstance.version > version) {
                    
                    internshipInstance.errors.rejectValue("version", "internship.optimistic.locking.failure", "Another user has updated this Internship while you were editing")
                    render(view: "edit", model: [internshipInstance: internshipInstance])
                    return
                }
            }
            internshipInstance.properties = params
            internshipInstance.reason = null
            if (!internshipInstance.hasErrors() && internshipInstance.save()) {
                flash.message = "internship.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Internship ${params.id} updated"
                redirect(action: "show", id: internshipInstance.id)
            }
            else {
                render(view: "edit", model: [internshipInstance: internshipInstance])
            }
        }
        else {
            flash.message = "internship.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Internship not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    /* It is used when an internship is deny by the administrator */
    def deny = {
        /* Get the internship and set reason with params.reason before saving and redirect the user to list */
        def internshipInstance = Internship.get(params.id)
        if (internshipInstance) {
            internshipInstance.reason = params.reason
            internshipInstance.save()
            redirect(action: "list")
        }
        /* Else we return a flash message */
        else {
            flash.message = "internship.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Internship not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def delete = {
        def internshipInstance = Internship.get(params.id)
        if (internshipInstance) {
            try {
                internshipInstance.delete()
                flash.message = "internship.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Internship ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "internship.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Internship ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "internship.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Internship not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
        def list = []
        def status = new HashMap()
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        def userInstance = authenticateService.userDomain()
        sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
        Internship.list().each() {
            if(authenticateService.ifAnyGranted("ROLE_ADMIN,ROLE_FORMATIONMANAGER") && it.getStatus(new Staff()) == params.status)
                list.add it.id
            if(authenticateService.ifAnyGranted("ROLE_STUDENT") && it.getStatus(new Staff()) == params.status && it.student.user == userInstance)
                list.add it.id
            if(authenticateService.ifAnyGranted("ROLE_EXTERNAL") && it.getStatus(new Staff()) == params.status && it.companyTutor.user == userInstance)
                list.add it.id
            if(authenticateService.ifAnyGranted("ROLE_STAFF")) {
                def statusStaff = Staff.findByUser(userInstance)
                if(it.getStatus(statusStaff) == params.status && (it.companyTutor.user == userInstance || it.isApproval == true)) {
                    list.add it.id
                }
            }
        }

        def list2
        if(list.size() > 0) {
            list2 = Internship.createCriteria().list(params) {
                'in'('id', list)
            }
        }

        list2.each {
            ret << [
               id:it.id,
   subject:it.subject,
   beginAt:it?.beginAt.formatDate(),
   isApproval:it.isApproval,
   report:[name:it.report?.uri, link:g.createLink(controller: 'report', action: 'show', id:it.report?.id)],
   student:[name:it.student?.user?.firstName + " " + it.student?.user?.lastName, link:g.createLink(controller: 'user', action: 'show', id:it.student?.id)],

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
