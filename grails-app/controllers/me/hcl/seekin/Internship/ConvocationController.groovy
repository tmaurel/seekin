package me.hcl.seekin.Internship

import me.hcl.seekin.Formation.*
import grails.converters.JSON
import grails.converters.XML

import com.itextpdf.text.*
import com.itextpdf.text.pdf.*

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

	def pdfGenerate = {
		def convocation = Convocation.get(params.id)
		def internship = convocation?.internship
		def student = internship?.student
		
		def uri = grailsAttributes.getApplicationUri(request) + "/test.pdf"

		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		PdfWriter writer = PdfWriter.getInstance(document, \
		new FileOutputStream("web-app/convocation/test.pdf"));
		document.open();
		document.add(new Paragraph(student?.user?.firstName + " " + student?.user?.lastName));
		document.add(new Paragraph(internship?.subject))
		document.add(new Paragraph(convocation?.date.toString() + " " + convocation.building + " " + convocation.room))
		document.close();

		redirect(url: "/seekin/convocation/test.pdf")
	}

	def pdfGeneratePlanning = {
		def promotion = Promotion.get(params.id)
		def internships

		promotion?.students?.internships.each {
			if(!it.isEmpty()) {
				internships = it.find {
					it2 ->
						it2.millesime == promotion.millesime
				}
			}
		}

		def convocations = internships?.convocation

		def uri = grailsAttributes.getApplicationUri(request) + "/planning.pdf"

		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		PdfWriter writer = PdfWriter.getInstance(document, \
		new FileOutputStream("web-app/convocation/planning.pdf"));
		document.open();

		convocations?.each {
			document.add(
				new Paragraph(
					it?.internship?.student?.user?.firstName + " " + 
					it?.internship?.student?.user?.lastName));
		}
		document.close();

		redirect(url: "/seekin/convocation/planning.pdf")
	}

	def showXml = {
		def convocation = Convocation.get(params.id)

		render convocation as XML
	}

    def dataTableDataAsJSON = {
		def promotion = Promotion.get(params.promotion)
		def internships
		
		promotion?.students?.internships.each {
			if(!it.isEmpty()) {
				internships = it.find {
					it2 ->
						it2.millesime == promotion.millesime
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
				pdf:[name:"<img src=\"../images/icons/pdf.png\" />", link:g.createLink(controller: 'convocation', action: 'pdfGenerate', id:it.id)],
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
