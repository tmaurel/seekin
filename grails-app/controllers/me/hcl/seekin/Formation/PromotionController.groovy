package me.hcl.seekin.Formation



import grails.converters.JSON
class PromotionController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
    }

    def create = {
        def promotionInstance = new Promotion()
        promotionInstance.properties = params
        return [promotionInstance: promotionInstance]
    }

    def save = {
        def promotionInstance = new Promotion(params)
        if (!promotionInstance.hasErrors() && promotionInstance.save()) {
            flash.message = "promotion.created"
            flash.args = [promotionInstance.id]
            flash.defaultMessage = "Promotion ${promotionInstance.id} created"
            redirect(action: "show", id: promotionInstance.id)
        }
        else {
            render(view: "create", model: [promotionInstance: promotionInstance])
        }
    }

    def show = {
        def promotionInstance = Promotion.get(params.id)
        if (!promotionInstance) {
            flash.message = "promotion.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Promotion not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [promotionInstance: promotionInstance]
        }
    }

    def edit = {
        def promotionInstance = Promotion.get(params.id)
        if (!promotionInstance) {
            flash.message = "promotion.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Promotion not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [promotionInstance: promotionInstance]
        }
    }

    def update = {
        def promotionInstance = Promotion.get(params.id)
        if (promotionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (promotionInstance.version > version) {
                    
                    promotionInstance.errors.rejectValue("version", "promotion.optimistic.locking.failure", "Another user has updated this Promotion while you were editing")
                    render(view: "edit", model: [promotionInstance: promotionInstance])
                    return
                }
            }
            promotionInstance.properties = params
            if (!promotionInstance.hasErrors() && promotionInstance.save()) {
                flash.message = "promotion.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Promotion ${params.id} updated"
                redirect(action: "show", id: promotionInstance.id)
            }
            else {
                render(view: "edit", model: [promotionInstance: promotionInstance])
            }
        }
        else {
            flash.message = "promotion.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Promotion not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def promotionInstance = Promotion.get(params.id)
        if (promotionInstance) {
            try {
                promotionInstance.delete()
                flash.message = "promotion.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Promotion ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "promotion.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Promotion ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "promotion.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Promotion not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
        def list = Promotion.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {
            ret << [
			id:it.id,
			formation:[name:it.formation?.toString(), link:g.createLink(controller: 'formation', action: 'show', id:it.formation?.id)],
			millesime:[name:it.millesime?.toString(), link:g.createLink(controller: 'millesime', action: 'show', id:it.millesime?.id)],
			urlID: it.id
            ]
        }

        def data = [
                totalRecords: Promotion.count(),
                results: ret
        ]
       
        render data as JSON
    }

}
