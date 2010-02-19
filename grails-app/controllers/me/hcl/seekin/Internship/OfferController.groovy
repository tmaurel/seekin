package me.hcl.seekin.Internship



import grails.converters.JSON
import me.hcl.seekin.Company

class OfferController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
    }

    def create = {
        def offerInstance = new Offer()
        offerInstance.properties = params
        return [offerInstance: offerInstance]
    }

    def save = {

        def company
        if(Company.countByName(params.companyName) == 0) {
          company = new Company()
          company.name = params.companyName
          company.save()
        }
        else {
          company = Company.findByName(params.companyName)
        }

        def offerInstance = new Offer(params)
        offerInstance.company = company
        if (!offerInstance.hasErrors() && offerInstance.save()) {
            flash.message = "offer.created"
            flash.args = [offerInstance.id]
            flash.defaultMessage = "Offer ${offerInstance.id} created"
            redirect(action: "show", id: offerInstance.id)
        }
        else {
            render(view: "create", model: [offerInstance: offerInstance])
        }
    }

    def show = {
        def offerInstance = Offer.get(params.id)
        if (!offerInstance) {
            flash.message = "offer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Offer not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [offerInstance: offerInstance]
        }
    }

    def edit = {
        def offerInstance = Offer.get(params.id)
        if (!offerInstance) {
            flash.message = "offer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Offer not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [offerInstance: offerInstance]
        }
    }

    def update = {
        def offerInstance = Offer.get(params.id)
        if (offerInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (offerInstance.version > version) {
                    
                    offerInstance.errors.rejectValue("version", "offer.optimistic.locking.failure", "Another user has updated this Offer while you were editing")
                    render(view: "edit", model: [offerInstance: offerInstance])
                    return
                }
            }
            offerInstance.properties = params
            if (!offerInstance.hasErrors() && offerInstance.save()) {
                flash.message = "offer.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Offer ${params.id} updated"
                redirect(action: "show", id: offerInstance.id)
            }
            else {
                render(view: "edit", model: [offerInstance: offerInstance])
            }
        }
        else {
            flash.message = "offer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Offer not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def offerInstance = Offer.get(params.id)
        if (offerInstance) {
            try {
                offerInstance.delete()
                flash.message = "offer.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Offer ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "offer.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Offer ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "offer.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Offer not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
        def list = Offer.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {
            ret << [
               id:it.id,
   subject:it.subject,
   description:it.description,
   beginAt:it.beginAt?.formatDate(),
   length:it.length,
   status:it.status,

                urlID: it.id
            ]
        }

        def data = [
                totalRecords: Offer.count(),
                results: ret
        ]
       
        render data as JSON
    }

}
