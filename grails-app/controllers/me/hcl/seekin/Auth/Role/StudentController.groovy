package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Formation.*
import grails.converters.JSON

class StudentController {
	def authenticateService
	
    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        if(!authenticateService.isLoggedIn()) {
            redirect(controller: "user", action: "index")
        }
		else {
			if(authenticateService.ifAnyGranted("ROLE_ADMIN")) {
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
                        else if(authenticateService.ifAnyGranted("ROLE_STUDENT")) {
                                            redirect(controller: "user", action: "index")
                        }
		}
    }

    def show = {
        def studentInstance = Student.get(params.id)
        if (!studentInstance) {
            flash.message = "student.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Student not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [studentInstance: studentInstance]
        }
    }

    def dataTableDataAsJSON = {

        def promotion = Promotion.get(params.promotion)
        def list = promotion.students.user.collect {
            it.id
        }

        def students = []
        if(list.size() > 0) {
            students = User.createCriteria().list(params) {
                'in'('id', list)
            }
        }

        def ret = []
        response.setHeader("Cache-Control", "no-store")

        students.each {
            ret << [
				firstName: it.firstName,
				lastName: it.lastName,
				email: it.showEmail?it.email:message(code:'user.email.notvisible'),
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
