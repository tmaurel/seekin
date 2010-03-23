package me.hcl.seekin.Ressource

import grails.converters.JSON
import me.hcl.seekin.Formation.*
import me.hcl.seekin.Auth.Role.FormationManager
import org.codehaus.groovy.grails.plugins.springsecurity.Secured

class EducationalDocController {

    def fileService

    def authenticateService

    static navigation = [
		group:'menu',
		order:2,
		title:'documents',
		action:'index'
	]

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {

        def formations = Formation.createCriteria().list() {
            isNotEmpty("educationalDocs")
        }

        render(view: "list", model: [formations: formations])
    }

    @Secured(['ROLE_ADMIN', 'ROLE_FORMATIONMANAGER'])
    def create = {

        def formations = []

        if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            def userInstance = authenticateService.userDomain()
            def manager = FormationManager.findByUser(userInstance)
            formations << [
                id: manager?.formation?.id,
                value: manager?.formation?.toString()
            ]
        }
        else
        {
            formations = Formation.list().collect {
                [
                        id: it.id,
                        value: it.toString()
                ]
            }
        }

        def educationalDocInstance = new EducationalDoc()
        educationalDocInstance.properties = params
        return [educationalDocInstance: educationalDocInstance, formations: formations]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_FORMATIONMANAGER'])
    def save = {
        
        def formations = []

        if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            def userInstance = authenticateService.userDomain()
            def manager = FormationManager.findByUser(userInstance)
            formations << [
                id: manager?.formation?.id,
                value: manager?.formation?.toString()
            ]
        }
        else
        {
            formations = Formation.list().collect {
                [
                        id: it.id,
                        value: it.toString()
                ]
            }
        }

        def educationalDocInstance = new EducationalDoc()
        educationalDocInstance.title = params.title
        educationalDocInstance.fileData = fileService.createFile(request.getFile( 'data' ))

        if (educationalDocInstance.save(flush: true)) {

            def formation
            params.formations.each {
                formation = Formation.get(it)
                formation.addToEducationalDocs(educationalDocInstance)
                //educationalDocInstance.addToFormations(Formation.get(it))
            }

            flash.message = "educationalDoc.created"
            flash.args = [educationalDocInstance.id]
            flash.defaultMessage = "EducationalDoc ${educationalDocInstance.id} created"
            redirect(action: "show", id: educationalDocInstance.id)
            
        }
        else {
            render(view: "create", model: [educationalDocInstance: educationalDocInstance, formations: formations])
        }
    }

    def show = {
        def educationalDocInstance = EducationalDoc.get(params.id)
        if (!educationalDocInstance) {
            flash.message = "educationalDoc.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "EducationalDoc not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [educationalDocInstance: educationalDocInstance]
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_FORMATIONMANAGER'])
    def edit = {

        def ok = false
        def formations = []
        def educationalDocInstance = EducationalDoc.get(params.id)

        if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            def userInstance = authenticateService.userDomain()
            def manager = FormationManager.findByUser(userInstance)
            formations = educationalDocInstance.formations.collect {
                [
                        id: it.id,
                        value: it.toString()
                ]
            }

            if(educationalDocInstance.formations.contains(manager?.formation))
                ok = true
        }
        else
        {
            formations = Formation.list().collect {
                [
                        id: it.id,
                        value: it.toString()
                ]
            }
            ok = true
        }

        if (!educationalDocInstance || !ok) {
            flash.message = "educationalDoc.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "EducationalDoc not found with id ${params.id}"
            redirect(action: "list")
        }
        else {

            def selectedFormations = educationalDocInstance.formations.collect {
                it.id
            }
            
            return [educationalDocInstance: educationalDocInstance, formations: formations, selectedFormations: selectedFormations]
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_FORMATIONMANAGER'])
    def update = {

        def ok = false
        def formations = []
        def educationalDocInstance = EducationalDoc.get(params.id)

        if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            def userInstance = authenticateService.userDomain()
            def manager = FormationManager.findByUser(userInstance)
            formations = educationalDocInstance.formations.collect {
                [
                        id: it.id,
                        value: it.toString()
                ]
            }

            if(educationalDocInstance.formations.contains(manager?.formation))
                ok = true
        }
        else
        {
            formations = Formation.list().collect {
                [
                        id: it.id,
                        value: it.toString()
                ]
            }
            ok = true
        }

        if (educationalDocInstance && ok) {
            if (params.version) {
                def version = params.version.toLong()
                if (educationalDocInstance.version > version) {
                    
                    educationalDocInstance.errors.rejectValue("version", "educationalDoc.optimistic.locking.failure", "Another user has updated this EducationalDoc while you were editing")
                    render(view: "edit", model: [educationalDocInstance: educationalDocInstance, formations: formations])
                    return
                }
            }
            educationalDocInstance.title = params.title

            if(request.getFile( 'data' ).getSize() > 0)
            {
                educationalDocInstance.fileData = fileService.createFile(request.getFile( 'data' ))
            }

            if (!educationalDocInstance.hasErrors() && educationalDocInstance.save()) {

                def ids = educationalDocInstance.formations.collect {
                    it.id
                }

                ids.each {
                    Formation.get(it).removeFromEducationalDocs(educationalDocInstance)
                }

                def formation
                params.formations.each {
                    formation = Formation.get(it)
                    formation.addToEducationalDocs(educationalDocInstance)
                }

                flash.message = "educationalDoc.updated"
                flash.args = [params.id]
                flash.defaultMessage = "EducationalDoc ${params.id} updated"
                redirect(action: "show", id: educationalDocInstance.id)
            }
            else {
                render(view: "edit", model: [educationalDocInstance: educationalDocInstance, formations: formations])
            }
        }
        else {
            flash.message = "educationalDoc.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "EducationalDoc not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_FORMATIONMANAGER'])
    def delete = {

        def ok = false
        def educationalDocInstance = EducationalDoc.get(params.id)

        if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            def userInstance = authenticateService.userDomain()
            def manager = FormationManager.findByUser(userInstance)

            if(educationalDocInstance.formations.contains(manager?.formation))
                ok = true
        }
        else
        {
            ok = true
        }

        if (educationalDocInstance && ok) {
            try {

                def ids = educationalDocInstance.formations.collect {
                    it.id
                }

                ids.each {
                    Formation.get(it).removeFromEducationalDocs(educationalDocInstance)
                }

                educationalDocInstance.delete()
                flash.message = "educationalDoc.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "EducationalDoc ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "educationalDoc.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "EducationalDoc ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "educationalDoc.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "EducationalDoc not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
        def list = Formation.get(params.formation)?.educationalDocs.collect {
            it.id
        }

        def list2
        if(list.size() > 0) {
            list2 = EducationalDoc.createCriteria().list(params) {
                'in'('id', list)
            }
        }

        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list2.each {
            ret << [
                title:it.title,
                urlID: it.id
            ]
        }

        def data = [
                totalRecords: list.size(),
                results: ret
        ]
       
        render data as JSON
    }

}
