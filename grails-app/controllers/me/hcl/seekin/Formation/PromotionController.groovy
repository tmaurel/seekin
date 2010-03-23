package me.hcl.seekin.Formation

import grails.converters.JSON
import org.codehaus.groovy.grails.plugins.springsecurity.Secured
import me.hcl.seekin.Auth.Role.FormationManager

class PromotionController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def authenticateService
    
    def list = {
    }

    @Secured(['ROLE_ADMIN', 'ROLE_FORMATIONMANAGER'])
    def create = {
        def promotionInstance = new Promotion()
        promotionInstance.properties = params

        def formations = []

        if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            def userInstance = authenticateService.userDomain()
            def manager = FormationManager.findByUser(userInstance)
            formations << manager?.formation
        }
        else
        {
            formations = Formation.list()
        }


        return [promotionInstance: promotionInstance, formations: formations]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_FORMATIONMANAGER'])
    def save = {
        def promotionInstance = new Promotion(params)
        if (!promotionInstance.hasErrors() && promotionInstance.save()) {
            flash.message = "promotion.created"
            flash.args = [promotionInstance.id]
            flash.defaultMessage = "Promotion ${promotionInstance.id} created"
            redirect(action: "show", id: promotionInstance.id)
        }
        else {
            def formations = []

            if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
            {
                def userInstance = authenticateService.userDomain()
                def manager = FormationManager.findByUser(userInstance)
                formations << manager?.formation
            }
            else
            {
                formations = Formation.list()
            }
            render(view: "create", model: [promotionInstance: promotionInstance, formations: formations])
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

    @Secured(['ROLE_ADMIN', 'ROLE_FORMATIONMANAGER'])
    def delete = {
        def promotionInstance = Promotion.get(params.id)

        def ok = true

        if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            ok = false
            def user = authenticateService.userDomain()
            def formation = FormationManager.findByUser(user)?.formation
            if(promotionInstance?.formation == formation)
                ok = true
        }

        if (promotionInstance && ok) {
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
