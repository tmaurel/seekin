package me.hcl.seekin



import grails.converters.JSON
class ConvocationController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
    }

    def create = {
        def convocationInstance = new Convocation()
        convocationInstance.properties = params
        return [convocationInstance: convocationInstance]
    }

    def save = {
        def convocationInstance = new Convocation(params)
        if (!convocationInstance.hasErrors() && convocationInstance.save()) {
            flash.message = "convocation.created"
            flash.args = [convocationInstance.id]
            flash.defaultMessage = "Convocation ${convocationInstance.id} created"
            redirect(action: "show", id: convocationInstance.id)
        }
        else {
            render(view: "create", model: [convocationInstance: convocationInstance])
        }
    }

    def show = {
        def convocationInstance = Convocation.get(params.id)
        if (!convocationInstance) {
            flash.message = "convocation.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Convocation not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [convocationInstance: convocationInstance]
        }
    }

    def edit = {
        def convocationInstance = Convocation.get(params.id)
        if (!convocationInstance) {
            flash.message = "convocation.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Convocation not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [convocationInstance: convocationInstance]
        }
    }

    def update = {
        def convocationInstance = Convocation.get(params.id)
        if (convocationInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (convocationInstance.version > version) {
                    
                    convocationInstance.errors.rejectValue("version", "convocation.optimistic.locking.failure", "Another user has updated this Convocation while you were editing")
                    render(view: "edit", model: [convocationInstance: convocationInstance])
                    return
                }
            }
            convocationInstance.properties = params
            if (!convocationInstance.hasErrors() && convocationInstance.save()) {
                flash.message = "convocation.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Convocation ${params.id} updated"
                redirect(action: "show", id: convocationInstance.id)
            }
            else {
                render(view: "edit", model: [convocationInstance: convocationInstance])
            }
        }
        else {
            flash.message = "convocation.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Convocation not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def convocationInstance = Convocation.get(params.id)
        if (convocationInstance) {
            try {
                convocationInstance.delete()
                flash.message = "convocation.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Convocation ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "convocation.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Convocation ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "convocation.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Convocation not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
        def list = Convocation.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {
            ret << [
               id:it.id,
   date:it.date?.formatDate(),
   building:it.building,
   room:it.room,
   internship:[name:it.internship?.id, link:g.createLink(controller: 'internship', action: 'show', id:it.internship?.id)],

                urlID: it.id
            ]
        }

        def data = [
                totalRecords: Convocation.count(),
                results: ret
        ]
       
        render data as JSON
    }

}
