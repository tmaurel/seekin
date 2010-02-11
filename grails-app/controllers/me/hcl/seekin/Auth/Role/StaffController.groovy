package me.hcl.seekin.Auth.Role



import grails.converters.JSON
class StaffController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
    }

    def create = {
        def staffInstance = new Staff()
        staffInstance.properties = params
        return [staffInstance: staffInstance]
    }

    def save = {
        def staffInstance = new Staff(params)
        if (!staffInstance.hasErrors() && staffInstance.save()) {
            flash.message = "staff.created"
            flash.args = [staffInstance.id]
            flash.defaultMessage = "Staff ${staffInstance.id} created"
            redirect(action: "show", id: staffInstance.id)
        }
        else {
            render(view: "create", model: [staffInstance: staffInstance])
        }
    }

    def show = {
        def staffInstance = Staff.get(params.id)
        if (!staffInstance) {
            flash.message = "staff.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Staff not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [staffInstance: staffInstance]
        }
    }

    def edit = {
        def staffInstance = Staff.get(params.id)
        if (!staffInstance) {
            flash.message = "staff.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Staff not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [staffInstance: staffInstance]
        }
    }

    def update = {
        def staffInstance = Staff.get(params.id)
        if (staffInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (staffInstance.version > version) {
                    
                    staffInstance.errors.rejectValue("version", "staff.optimistic.locking.failure", "Another user has updated this Staff while you were editing")
                    render(view: "edit", model: [staffInstance: staffInstance])
                    return
                }
            }
            staffInstance.properties = params
            if (!staffInstance.hasErrors() && staffInstance.save()) {
                flash.message = "staff.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Staff ${params.id} updated"
                redirect(action: "show", id: staffInstance.id)
            }
            else {
                render(view: "edit", model: [staffInstance: staffInstance])
            }
        }
        else {
            flash.message = "staff.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Staff not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def staffInstance = Staff.get(params.id)
        if (staffInstance) {
            try {
                staffInstance.delete()
                flash.message = "staff.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Staff ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "staff.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Staff ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "staff.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Staff not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
        def list = Staff.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {
            ret << [
               id:it.id,
   user:[name:it.user?.id, link:g.createLink(controller: 'user', action: 'show', id:it.user?.id)],
   authority:it.authority,

                urlID: it.id
            ]
        }

        def data = [
                totalRecords: Staff.count(),
                results: ret
        ]
       
        render data as JSON
    }

}
