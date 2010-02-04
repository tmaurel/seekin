package me.hcl.seekin.Internship



import grails.converters.JSON
class InternshipController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        [internshipInstanceList: Internship.list(params), internshipInstanceTotal: Internship.count()]
    }

    def create = {
        def internshipInstance = new Internship()
        internshipInstance.properties = params
        return [internshipInstance: internshipInstance]
    }

    def save = {
        def internshipInstance = new Internship(params)
        if (!internshipInstance.hasErrors() && internshipInstance.save()) {
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
        def internshipInstance = Internship.get(params.id)
        if (!internshipInstance) {
            flash.message = "internship.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Internship not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [internshipInstance: internshipInstance]
        }
    }

    def edit = {
        def internshipInstance = Internship.get(params.id)
        if (!internshipInstance) {
            flash.message = "internship.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Internship not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [internshipInstance: internshipInstance]
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
        def list = Internship.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {
            ret << [
               id:it.id,
   subject:it.subject,
   beginAt:it.beginAt?.toString(),
   isApproval:it.isApproval,
   report:[name:it.report?.id, link:g.createLink(controller: 'report', action: 'show', id:it.report?.id)],
   student:[name:it.student?.id, link:g.createLink(controller: 'student', action: 'show', id:it.student?.id)],

                urlID: it.id
            ]
        }

        def data = [
                totalRecords: Internship.count(),
                results: ret
        ]
       
        render data as JSON
    }

}
