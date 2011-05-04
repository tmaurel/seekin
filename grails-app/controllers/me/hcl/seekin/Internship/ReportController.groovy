package me.hcl.seekin.Internship

import org.hibernate.LockMode
import me.hcl.seekin.Auth.Role.Student
import grails.converters.JSON
import me.hcl.seekin.Auth.Role.*
import me.hcl.seekin.Formation.Promotion
import org.codehaus.groovy.grails.plugins.springsecurity.Secured

class ReportController {

    def authenticateService
    def sessionFactory
    def fileService

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER', 'ROLE_STUDENT', 'ROLE_STAFF'])
    def list = {
    }

    @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER', 'ROLE_STUDENT'])
    def create = {
        def reportInstance = new Report()
        reportInstance.properties = params
        
        def userInstance = authenticateService.userDomain()
        sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)

        def internships = []

        if(authenticateService.ifAnyGranted("ROLE_STUDENT"))
        {
            // Get the student instance logged in
            def student = Student.findByUser(userInstance)

            if(student)
            {
                internships = Internship.createCriteria().list() {
                    isNull("report")
                    eq("student", student)
                }
            }
        }
        else if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            def manager = FormationManager.findByUser(userInstance)

            if(manager)
            {
                def students = []
                def promotions = Promotion.findAllByFormation(manager?.formation)
                promotions.each {
                    it.students.each {
                        students << it
                    }
                }

                internships = Internship.createCriteria().list() {
                    'in'('student', students)
                    isNull("report")
                }
            }
        }
        else
        {
                internships = Internship.createCriteria().list() {
                    isNull("report")
                }
        }

        if(internships.size() > 0)
            return [reportInstance: reportInstance, internships: internships]
        else
        {
            flash.message = "report.no.internship.found"
            redirect action: "list"
        }
   
    }

    @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER', 'ROLE_STUDENT'])
    def save = {
        def reportInstance = new Report()

        def userInstance = authenticateService.userDomain()
        sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)

        def internships = []

        if(authenticateService.ifAnyGranted("ROLE_STUDENT"))
        {
            // Get the student instance logged in
            def student = Student.findByUser(userInstance)

            if(student)
            {
                internships = Internship.createCriteria().list() {
                    isNull("report")
                    eq("student", student)
                }
            }
        }
        else if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            def manager = FormationManager.findByUser(userInstance)

            if(manager)
            {
                def students = []
                def promotions = Promotion.findAllByFormation(manager?.formation)
                promotions.each {
                    it.students.each {
                        students << it
                    }
                }

                internships = Internship.createCriteria().list() {
                    'in'('student', students)
                    isNull("report")
                }
            }
        }
        else
        {
                internships = Internship.createCriteria().list() {
                    isNull("report")
                }
        }

        if(internships.size() > 0)
        {
            reportInstance.title = params.title
            reportInstance.isPrivate = (params.isPrivate)?true:false
            reportInstance.fileData = fileService.createFile(request.getFile( 'data' ))

            def internship = Internship.get(params.internship)
            reportInstance.internship = internship

            if (!reportInstance.hasErrors() && (internship.report = reportInstance.save())) {
                flash.message = "report.created"
                flash.args = [reportInstance.id]
                flash.defaultMessage = "Report ${reportInstance.id} created"
                redirect(action: "show", id: reportInstance.id)
            }
            else {
                render(view: "create", model: [reportInstance: reportInstance, internships: internships])
            }
        }
        else
        {
            flash.message = "report.no.internship.found"
            redirect action: "list"
        }
    }

    def show = {
        def reportInstance = Report.get(params.id)

        def ok = true
        def keep = true
        def visible = true

        def userInstance = authenticateService.userDomain()

        if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {
            ok = false
            def student = Student.findByUser(userInstance)

            if(reportInstance?.internship?.student?.id == student?.id)
                ok = true

            if(reportInstance?.isPrivate)
                visible = false
        }
        else if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            ok = false
            def manager = FormationManager.findByUser(userInstance)

            def promotion =  reportInstance?.internship?.student?.promotions.find {
                it.millesime?.id == reportInstance?.internship?.millesime?.id
            }

            if(promotion?.formation?.id == manager?.formation?.id)
                ok = true
        }
        else if(authenticateService.ifAnyGranted("ROLE_EXTERNAL"))
        {
            keep = false
            def external = External.findByUser(userInstance)
            if(reportInstance?.internship?.companyTutor?.id == external?.id)
                keep = true

            if(reportInstance?.isPrivate)
                visible = false
        }

        if (!reportInstance || !keep) {
            flash.message = "report.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Report not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [reportInstance: reportInstance, ok: ok, visible: visible]
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER', 'ROLE_STUDENT'])
    def edit = {
        def reportInstance = Report.get(params.id)

        def ok = true
        def userInstance = authenticateService.userDomain()

        if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {
            ok = false
            def student = Student.findByUser(userInstance)

            if(reportInstance?.internship?.student?.id == student?.id)
                ok = true
        }
        else if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            ok = false
            def manager = FormationManager.findByUser(userInstance)

            def promotion =  reportInstance?.internship?.student?.promotions.find {
                it.millesime?.id == reportInstance?.internship?.millesime?.id
            }

            if(promotion?.formation?.id == manager?.formation?.id)
                ok = true
        }

        if (!reportInstance || !ok) {
            flash.message = "report.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Report not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [reportInstance: reportInstance, ok: ok]
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER', 'ROLE_STUDENT'])
    def update = {
        def reportInstance = Report.get(params.id)

        def ok = true
        def userInstance = authenticateService.userDomain()

        if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {
            ok = false
            def student = Student.findByUser(userInstance)

            if(reportInstance?.internship?.student?.id == student?.id)
                ok = true
        }
        else if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            ok = false
            def manager = FormationManager.findByUser(userInstance)

            def promotion =  reportInstance?.internship?.student?.promotions.find {
                it.millesime?.id == reportInstance?.internship?.millesime?.id
            }

            if(promotion?.formation?.id == manager?.formation?.id)
                ok = true
        }

        if (reportInstance && ok) {
            if (params.version) {
                def version = params.version.toLong()
                if (reportInstance.version > version) {
                    
                    reportInstance.errors.rejectValue("version", "report.optimistic.locking.failure", "Another user has updated this Report while you were editing")
                    render(view: "edit", model: [reportInstance: reportInstance, ok: ok])
                    return
                }
            }
            reportInstance.properties = params
            reportInstance.isPrivate = (params.isPrivate)?true:false
            if(request.getFile( 'data' ).getSize() > 0)
            {
                reportInstance.fileData = fileService.createFile(request.getFile( 'data' ))
            }

            if (!reportInstance.hasErrors() && reportInstance.save()) {
                flash.message = "report.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Report ${params.id} updated"
                redirect(action: "show", id: reportInstance.id)
            }
            else {
                render(view: "edit", model: [reportInstance: reportInstance, ok: ok])
            }
        }
        else {
            flash.message = "report.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Report not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER'])
    def delete = {
        def reportInstance = Report.get(params.id)
        def ok = true
        def userInstance = authenticateService.userDomain()
        if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            ok = false
            def manager = FormationManager.findByUser(userInstance)
            def promotion =  reportInstance?.internship?.student?.promotions.find {
                it.millesime?.id == reportInstance?.internship?.millesime?.id
            }

            if(promotion?.formation?.id == manager?.formation?.id)
                ok = true
        }

        if (reportInstance && ok) {
            try {
                reportInstance.internship.report = null
                reportInstance.delete()
                flash.message = "report.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Report ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "report.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Report ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "report.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Report not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    @Secured(['ROLE_ADMIN','ROLE_FORMATIONMANAGER', 'ROLE_STUDENT', 'ROLE_STAFF'])
    def dataTableDataAsJSON = {
        def list = Report.list().collect{ it.id }

        def ret = []
        response.setHeader("Cache-Control", "no-store")


        def list2
        if(list.size() > 0) {
            // TODO : SORTING DISABLED / TO FIX
            list2 = Report.createCriteria().list() {
                'in'('id', list)
                if(params.title && params.title != ''){
                  ilike("title", "${params.title}%")
                }
            }
        }

        list2.each {
            ret << [
                title:it.title,
                internship:[name:it.internship?.subject, link:g.createLink(controller: 'internship', action: 'show', id:it.internship?.id)],
                urlID: it.id
            ]
        }

        def data = [
                totalRecords: list2!=null?list2.size():0,
                results: ret
        ]
       
        render data as JSON
    }

}
