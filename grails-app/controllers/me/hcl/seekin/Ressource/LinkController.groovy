package me.hcl.seekin.Ressource



import grails.converters.JSON
class LinkController {


    static navigation = [
        group:'menu',
        order:3,
        title:'link',
        action:'index'
    ]

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
    }

    def create = {
        def linkInstance = new Link()
        linkInstance.properties = params
        return [linkInstance: linkInstance]
    }

    def save = {
        def linkInstance = new Link(params)
        if (!linkInstance.hasErrors() && linkInstance.save()) {
            flash.message = "link.created"
            flash.args = [linkInstance.id]
            flash.defaultMessage = "Link ${linkInstance.id} created"
            redirect(action: "show", id: linkInstance.id)
        }
        else {
            render(view: "create", model: [linkInstance: linkInstance])
        }
    }

    def show = {
        def linkInstance = Link.get(params.id)
        if (!linkInstance) {
            flash.message = "link.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Link not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [linkInstance: linkInstance]
        }
    }

    def edit = {
        def linkInstance = Link.get(params.id)
        if (!linkInstance) {
            flash.message = "link.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Link not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [linkInstance: linkInstance]
        }
    }

    def update = {
        def linkInstance = Link.get(params.id)
        if (linkInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (linkInstance.version > version) {
                    
                    linkInstance.errors.rejectValue("version", "link.optimistic.locking.failure", "Another user has updated this Link while you were editing")
                    render(view: "edit", model: [linkInstance: linkInstance])
                    return
                }
            }
            linkInstance.properties = params
            if (!linkInstance.hasErrors() && linkInstance.save()) {
                flash.message = "link.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Link ${params.id} updated"
                redirect(action: "show", id: linkInstance.id)
            }
            else {
                render(view: "edit", model: [linkInstance: linkInstance])
            }
        }
        else {
            flash.message = "link.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Link not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def linkInstance = Link.get(params.id)
        if (linkInstance) {
            try {
                linkInstance.delete()
                flash.message = "link.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Link ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "link.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Link ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "link.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Link not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
        def list = Link.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {
            ret << [
               id:it.id,
   title:it.title,
   url:it.url,
   description:it.description,

                urlID: it.id
            ]
        }

        def data = [
                totalRecords: Link.count(),
                results: ret
        ]
       
        render data as JSON
    }

}
