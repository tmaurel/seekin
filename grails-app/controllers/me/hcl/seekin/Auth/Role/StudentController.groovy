package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Auth.User

import grails.converters.JSON
class StudentController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
    }

    def create = {
        def studentInstance = new Student()
        studentInstance.properties = params
        return [studentInstance: studentInstance]
    }

    def save = {
        def studentInstance = new Student(params)
        if (!studentInstance.hasErrors() && studentInstance.save()) {
            flash.message = "student.created"
            flash.args = [studentInstance.id]
            flash.defaultMessage = "Student ${studentInstance.id} created"
            redirect(action: "show", id: studentInstance.id)
        }
        else {
            render(view: "create", model: [studentInstance: studentInstance])
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

    def edit = {
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

    def update = {
        def studentInstance = Student.get(params.id)
        if (studentInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (studentInstance.version > version) {
                    
                    studentInstance.errors.rejectValue("version", "student.optimistic.locking.failure", "Another user has updated this Student while you were editing")
                    render(view: "edit", model: [studentInstance: studentInstance])
                    return
                }
            }
            studentInstance.properties = params
            if (!studentInstance.hasErrors() && studentInstance.save()) {
                flash.message = "student.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Student ${params.id} updated"
                redirect(action: "show", id: studentInstance.id)
            }
            else {
                render(view: "edit", model: [studentInstance: studentInstance])
            }
        }
        else {
            flash.message = "student.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Student not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def studentInstance = Student.get(params.id)
        if (studentInstance) {
            try {
                studentInstance.delete()
                flash.message = "student.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Student ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "student.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Student ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "student.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Student not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
		def list = User.list()
		//def list = Student.list().user
		/*params.fetch = [user:"eager"]
		def list = Student.list(params)*/

		// println list

		// println params

        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {
            ret << [
				firstName: it.firstName,
				lastName: it.lastName,
				urlID: it.id
            ]
        }

        def data = [
                totalRecords: User.count(),
                results: ret
        ]
       
        render data as JSON
    }

}
