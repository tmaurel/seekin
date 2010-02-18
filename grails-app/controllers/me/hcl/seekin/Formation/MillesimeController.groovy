package me.hcl.seekin.Formation



import grails.converters.JSON
class MillesimeController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
    }

    def create = {
        def millesimeInstance = new Millesime()
        millesimeInstance.properties = params
        return [millesimeInstance: millesimeInstance]
    }

    def save = {
        def millesimeInstance = new Millesime(params)
        if (!millesimeInstance.hasErrors() && millesimeInstance.save()) {
            flash.message = "millesime.created"
            flash.args = [millesimeInstance.id]
            flash.defaultMessage = "Millesime ${millesimeInstance.id} created"
            redirect(action: "show", id: millesimeInstance.id)
        }
        else {
            render(view: "create", model: [millesimeInstance: millesimeInstance])
        }
    }

    def show = {
        def millesimeInstance = Millesime.get(params.id)
        if (!millesimeInstance) {
            flash.message = "millesime.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Millesime not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [millesimeInstance: millesimeInstance]
        }
    }

    def edit = {
        def millesimeInstance = Millesime.get(params.id)
        if (!millesimeInstance) {
            flash.message = "millesime.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Millesime not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [millesimeInstance: millesimeInstance]
        }
    }

    def update = {
        def millesimeInstance = Millesime.get(params.id)
        if (millesimeInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (millesimeInstance.version > version) {
                    
                    millesimeInstance.errors.rejectValue("version", "millesime.optimistic.locking.failure", "Another user has updated this Millesime while you were editing")
                    render(view: "edit", model: [millesimeInstance: millesimeInstance])
                    return
                }
            }
            millesimeInstance.properties = params
            if (!millesimeInstance.hasErrors() && millesimeInstance.save()) {
                flash.message = "millesime.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Millesime ${params.id} updated"
                redirect(action: "show", id: millesimeInstance.id)
            }
            else {
                render(view: "edit", model: [millesimeInstance: millesimeInstance])
            }
        }
        else {
            flash.message = "millesime.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Millesime not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def millesimeInstance = Millesime.get(params.id)
        if (millesimeInstance) {
            try {
                millesimeInstance.delete()
                flash.message = "millesime.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Millesime ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "millesime.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Millesime ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "millesime.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Millesime not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
        def list = Millesime.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {
            ret << [
               id:it.id,
   beginDate:it.beginDate?.formatDate(),
   endDate:it.endDate?.formatDate(),
   current:it.current,

                urlID: it.id
            ]
        }

        def data = [
                totalRecords: Millesime.count(),
                results: ret
        ]
       
        render data as JSON
    }

}
