package me.hcl.seekin.Formation



import grails.converters.JSON
class FormationController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
    }

    def create = {
        def formationInstance = new Formation()
        formationInstance.properties = params
        return [formationInstance: formationInstance]
    }

    def save = {
        def formationInstance = new Formation(params)
        if (!formationInstance.hasErrors() && formationInstance.save()) {
            flash.message = "formation.created"
            flash.args = [formationInstance.id]
            flash.defaultMessage = "Formation ${formationInstance.id} created"
            redirect(action: "show", id: formationInstance.id)
        }
        else {
            render(view: "create", model: [formationInstance: formationInstance])
        }
    }

    def show = {
        def formationInstance = Formation.get(params.id)
        if (!formationInstance) {
            flash.message = "formation.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Formation not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [formationInstance: formationInstance]
        }
    }

    def edit = {
        def formationInstance = Formation.get(params.id)
        if (!formationInstance) {
            flash.message = "formation.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Formation not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [formationInstance: formationInstance]
        }
    }

    def update = {
        def formationInstance = Formation.get(params.id)
        if (formationInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (formationInstance.version > version) {
                    
                    formationInstance.errors.rejectValue("version", "formation.optimistic.locking.failure", "Another user has updated this Formation while you were editing")
                    render(view: "edit", model: [formationInstance: formationInstance])
                    return
                }
            }
            formationInstance.properties = params
            if (!formationInstance.hasErrors() && formationInstance.save()) {
                flash.message = "formation.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Formation ${params.id} updated"
                redirect(action: "show", id: formationInstance.id)
            }
            else {
                render(view: "edit", model: [formationInstance: formationInstance])
            }
        }
        else {
            flash.message = "formation.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Formation not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def formationInstance = Formation.get(params.id)
        if (formationInstance) {
            try {
                formationInstance.delete()
                flash.message = "formation.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Formation ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "formation.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Formation ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "formation.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Formation not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
        def list = Formation.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {
            ret << [
               id:it.id,
               label:it.label,
               description:it.description,
               urlID: it.id
            ]
        }

        def data = [
                totalRecords: Formation.count(),
                results: ret
        ]
       
        render data as JSON
    }

}
