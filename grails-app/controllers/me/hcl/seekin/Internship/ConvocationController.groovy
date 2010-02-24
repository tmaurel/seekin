package me.hcl.seekin.Internship

import me.hcl.seekin.Formation.*
import grails.converters.JSON
import org.codehaus.groovy.grails.commons.ApplicationHolder
import org.springframework.context.i18n.LocaleContextHolder as LCH
import java.text.DateFormat;
import java.text.SimpleDateFormat

class ConvocationController {

    def pdfService

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
		def millesimes = Millesime.findAllByBeginDateLessThan(new Date())
		def millesimeCurrent = millesimes.find {
			it.current == true
		}
		def millesimeSelected

		if(params.idMillesime)
		{
			millesimeSelected = Millesime.get(params.idMillesime)
		}
		else
		{
			millesimeSelected = millesimeCurrent
		}

		def promotions = Promotion.findAllByMillesime(millesimeSelected)

		render(view: "list", model: [promotions: promotions, millesimes: millesimes])
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

    def export = {

        def convocationInstance = Convocation.get(params.id)
        if (!convocationInstance)
        {
            flash.message = "convocation.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Convocation not found with id ${params.id}"
            redirect(action: "list")
        }
        else
        {
            def url = grailsApplication.config.grails.serverURL.toString() + "/"
            def path = ApplicationHolder.getApplication().getParentContext().getServletContext().getRealPath("convocation");
            def gsp = new File( path,"_convocation.gsp")
            def locale = LCH.getLocale()
            def dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);

            def model = [:]
            model.student = convocationInstance.internship.student.user
            model.companyTutor = convocationInstance.internship.companyTutor.user
            model.academicTutor = convocationInstance.internship.academicTutor.user
            model.promotion = Promotion.getCurrentForStudent(convocationInstance.internship.student)
            model.manager = model.promotion.formation.manager?.user
            model.date = dateFormat.format(convocationInstance.date)
            model.time = new SimpleDateFormat("HH:mm").format(convocationInstance.date)
            model.convocation = convocationInstance
            
            response.setContentType "application/pdf"
            response.setHeader "Content-Disposition", "inline;filename=convocation-" +
                                    pdfService.removeSpecialCharacters(model.student.firstName) + "-" +
                                    pdfService.removeSpecialCharacters(model.student.lastName) + ".pdf"
                                    
            pdfService.buildPdf(gsp, model, response, url)
            return
        }
    }


    def exportPlanning = {

        def promotionInstance = Promotion.get(params.id)
        if (!promotionInstance) {
            flash.message = "promotion.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Promotion not found with id ${params.id}"
            redirect(action: "list")
        }
        else
        {

            def url = grailsApplication.config.grails.serverURL.toString() + "/"
            def path = ApplicationHolder.getApplication().getParentContext().getServletContext().getRealPath("convocation");
            def gsp = new File( path,"_planning.gsp")
            def locale = LCH.getLocale()
            def dateFormat = DateFormat.getDateInstance(DateFormat.SHORT, locale);
            def internships = []
            def model = [convocations:[]]

            promotionInstance?.students?.internships.each {
                    if(!it.isEmpty()) {
                            it.each {
                                if(it.millesime == promotionInstance.millesime && it.convocation != null)
                                    internships << it
                            }
                    }
            }

            internships.sort {
                p1, p2 ->
                    p1.convocation.date <=> p2.convocation.date
            }

            internships.each {

                model.convocations << [
                    date: dateFormat.format(it.convocation.date),
                    time: new SimpleDateFormat("HH:mm").format(it.convocation.date),
                    name: it.student?.user?.firstName + " " + it.student?.user?.lastName,
                    building: it.convocation.building,
                    room: it.convocation.room,
                    academicTutor: it.academicTutor?.user?.lastName + " " + it.academicTutor?.user?.firstName

                ]

            }
            

            response.setContentType "application/pdf"
            response.setHeader "Content-Disposition", "inline;filename=planning-" +
                                    pdfService.removeSpecialCharacters(promotionInstance.formation.label) + "-" +
                                    pdfService.removeSpecialCharacters(promotionInstance.millesime.toString()) + ".pdf"

            pdfService.buildPdf(gsp, model, response, url)
            return
        }
    }

    def dataTableDataAsJSON = {
        def promotion = Promotion.get(params.promotion)
        def internships = []

        promotion?.students?.internships.each {
                if(!it.isEmpty()) {
                        it.each {
                            if(it.millesime == promotion.millesime)
                                internships << it
                        }
                }
        }

        def convocations = internships?.convocation

        def list = Convocation.list(params)
		
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        convocations.each {
            ret << [
				id:it.id,
				date:it.date?.formatDate(),
				building:it.building,
				room:it.room,
				internship: it.internship?.toString(),
				pdf:[name:"<img src=\"../images/icons/pdf.png\" />", link:g.createLink(controller: 'convocation', action: 'export', id:it.id)],
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