package me.hcl.seekin.Internship

import org.hibernate.LockMode
import me.hcl.seekin.Auth.Role.Student

import grails.converters.JSON
class ReportController {

    def authenticateService
    def sessionFactory
    def fileService

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
    }

    def create = {
        def reportInstance = new Report()
        reportInstance.properties = params
        
        def userInstance = authenticateService.userDomain()
        sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
        // Get the student instance logged in
        def student = Student.findByUser(userInstance)

        if(student)
        {
            def internships = Internship.createCriteria().list() {
                isNull("report")
                eq("student", student)
            }
            if(internships.size() > 0)
                return [reportInstance: reportInstance, internships: internships]
            else
            {
                flash.message = "report.no.internship.found"
                redirect action: "list"
            }
        }
        else redirect action:list
    }

    def save = {
        def reportInstance = new Report()

        def userInstance = authenticateService.userDomain()
        sessionFactory.currentSession.refresh(userInstance, LockMode.NONE)
        // Get the student instance logged in
        def student = Student.findByUser(userInstance)

        if(student)
        {
            def internships = Internship.createCriteria().list() {
                isNull("report")
                eq("student", student)
            }

            reportInstance.title = params.title
            reportInstance.isPrivate = (params.isPrivate)?true:false
            reportInstance.fileData = fileService.createFile(request.getFile( 'data' ))

            def internship = Internship.get(params.internship)

            reportInstance.internship = internship

            if (internship?.student == student && !reportInstance.hasErrors() && (internship.report = reportInstance.save())) {
                println reportInstance.internship
                flash.message = "report.created"
                flash.args = [reportInstance.id]
                flash.defaultMessage = "Report ${reportInstance.id} created"
                redirect(action: "show", id: reportInstance.id)
            }
            else {
                render(view: "create", model: [reportInstance: reportInstance, internships: internships])
            }
        }
        else redirect action:list
    }

    def show = {
        def reportInstance = Report.get(params.id)
        if (!reportInstance) {
            flash.message = "report.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Report not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            println reportInstance.internship.report
            return [reportInstance: reportInstance]
        }
    }

    def edit = {
        def reportInstance = Report.get(params.id)
        if (!reportInstance) {
            flash.message = "report.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Report not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [reportInstance: reportInstance]
        }
    }

    def update = {
        def reportInstance = Report.get(params.id)
        if (reportInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (reportInstance.version > version) {
                    
                    reportInstance.errors.rejectValue("version", "report.optimistic.locking.failure", "Another user has updated this Report while you were editing")
                    render(view: "edit", model: [reportInstance: reportInstance])
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
                render(view: "edit", model: [reportInstance: reportInstance])
            }
        }
        else {
            flash.message = "report.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Report not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def reportInstance = Report.get(params.id)
        if (reportInstance) {
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

    def dataTableDataAsJSON = {
        def list = Report.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {
            ret << [
                title:it.title,
                internship:[name:it.internship?.subject, link:g.createLink(controller: 'internship', action: 'show', id:it.internship?.id)],
                urlID: it.id
            ]
        }

        def data = [
                totalRecords: Report.count(),
                results: ret
        ]
       
        render data as JSON
    }

}
