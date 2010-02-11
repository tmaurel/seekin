package me.hcl.seekin.Auth.Role



import grails.converters.JSON
class ExternalController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
    }

    def create = {
        def externalInstance = new External()
        externalInstance.properties = params
        return [externalInstance: externalInstance]
    }

    def save = {
        def externalInstance = new External(params)
        if (!externalInstance.hasErrors() && externalInstance.save()) {
            flash.message = "external.created"
            flash.args = [externalInstance.id]
            flash.defaultMessage = "External ${externalInstance.id} created"
            redirect(action: "show", id: externalInstance.id)
        }
        else {
            render(view: "create", model: [externalInstance: externalInstance])
        }
    }

    def show = {
        def externalInstance = External.get(params.id)
        if (!externalInstance) {
            flash.message = "external.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "External not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [externalInstance: externalInstance]
        }
    }

    def edit = {
        def externalInstance = External.get(params.id)
        if (!externalInstance) {
            flash.message = "external.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "External not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [externalInstance: externalInstance]
        }
    }

    def update = {
        def externalInstance = External.get(params.id)
        if (externalInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (externalInstance.version > version) {
                    
                    externalInstance.errors.rejectValue("version", "external.optimistic.locking.failure", "Another user has updated this External while you were editing")
                    render(view: "edit", model: [externalInstance: externalInstance])
                    return
                }
            }
            externalInstance.properties = params
            if (!externalInstance.hasErrors() && externalInstance.save()) {
                flash.message = "external.updated"
                flash.args = [params.id]
                flash.defaultMessage = "External ${params.id} updated"
                redirect(action: "show", id: externalInstance.id)
            }
            else {
                render(view: "edit", model: [externalInstance: externalInstance])
            }
        }
        else {
            flash.message = "external.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "External not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def externalInstance = External.get(params.id)
        if (externalInstance) {
            try {
                externalInstance.delete()
                flash.message = "external.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "External ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "external.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "External ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "external.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "External not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
        def list = External.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {
            ret << [
               id:it.id,
   user:[name:it.user?.id, link:g.createLink(controller: 'user', action: 'show', id:it.user?.id)],
   company:[name:it.company?.id, link:g.createLink(controller: 'company', action: 'show', id:it.company?.id)],
   formerStudent:it.formerStudent,
   authority:it.authority,

                urlID: it.id
            ]
        }

        def data = [
                totalRecords: External.count(),
                results: ret
        ]
       
        render data as JSON
    }

}
