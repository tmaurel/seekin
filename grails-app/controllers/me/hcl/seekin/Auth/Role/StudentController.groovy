package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Formation.*
import grails.converters.JSON
import org.hibernate.FetchMode as FM
import org.codehaus.groovy.grails.plugins.springsecurity.Secured

class StudentController {

    def projections = [
        "student.id",
        "user.firstName",
        "user.lastName",
        "user.email",
        "user.phone",
        "address.street",
        "address.town",
        "address.zipCode",
        "formation.label",
        "internship.subject",
        "internship.description",
        "company.name",
        "company.phone",
        "academicTutor.firstName",
        "academicTutor.lastName",
        "companyTutor.firstName",
        "companyTutor.lastName"
    ]


    def sessionFactory
    def authenticateService
	
    def index = { redirect(action: "list", params: params) }

    @Secured(['ROLE_ADMIN', 'ROLE_FORMATIONMANAGER'])
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

        def promotions

        if(authenticateService.ifAnyGranted('ROLE_ADMIN'))
            promotions = Promotion.findAllByMillesime(millesimeSelected)
        else
        {
            def userInstance = authenticateService.userDomain()
            def manager = FormationManager.findByUser(userInstance)

            promotions = Promotion.findAllByMillesimeAndFormation(millesimeSelected, manager?.formation)
        }

        render(view: "list", model: [promotions: promotions, millesimes: millesimes])
				
    }

    def show = {
		def userInstance = User.get(params.id)
        def studentInstance = Student.findByUser(userInstance)
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

    @Secured(['ROLE_ADMIN', 'ROLE_FORMATIONMANAGER'])
    def dataTableDataAsJSON = {

        def promotion = Promotion.get(params.promotion)
        def list = promotion.students.user.collect {
            it.id
        }

        def filter = {
          'in'('id', list)
          and {
            if(params.lastName && params.lastName != ''){
                ilike("lastName", "${params.lastName}%")
            }
            if(params.firstName && params.firstName != ''){
                ilike("firstName", "${params.firstName}%")
            }
          }
        }

        def students = []
        if(list.size() > 0) {
            students = User.createCriteria().list(params, filter)
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
            totalRecords: list.size()>0?User.createCriteria().count(filter):0,
            results: ret
        ]
       
        render data as JSON
    }

    @Secured(['ROLE_ADMIN', 'ROLE_FORMATIONMANAGER'])
    def table = {
        def millesimes = Millesime.findAllByBeginDateLessThan(new Date())
        def millesimeCurrent = Millesime.getCurrent()

        def columnDefs = projections.collect {
            def key = it.replaceAll(/\./, '_')
            [key: key, sortable: true, resizeable: true, label: "${message(code:it)}", formatter:'managerTableFormatter']
        }

        return [projections: projections, columnDefs: columnDefs, millesimes: millesimes, millesimeCurrent: millesimeCurrent]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_FORMATIONMANAGER'])
    def tableAsJSON = {

        def millesimeCurrent = Millesime.getCurrent()
        def millesimeSelected

        if(params.millesime)
        {
            millesimeSelected = Millesime.get(params.millesime)
            session.millesime = params.millesime
        }
        else if(session.millesime)
            millesimeSelected = Millesime.get(session.millesime)
        else
            millesimeSelected = millesimeCurrent

        response.setHeader("Cache-Control", "no-store")

        def current = Millesime.getCurrent()

        def query = "SELECT "
        projections.eachWithIndex { obj, i ->
            query += obj
            if(i < projections.size() - 1)
                query += ", "
        }
        query += " FROM Student as student,  Promotion as promotion, Formation as formation"
        query += " INNER JOIN student.user as user"
        query += " INNER JOIN user.address as address"
        query += " LEFT OUTER JOIN student.internships as internship"
        query += "      WITH internship.millesime.id = " + millesimeSelected.id
        query += " LEFT OUTER JOIN internship.company as company"
        query += " LEFT OUTER JOIN internship.academicTutor.user as academicTutor"
        query += " LEFT OUTER JOIN internship.companyTutor.user as companyTutor"
        query += " WHERE student.id IN ELEMENTS(promotion.students)"
        query += " AND promotion.millesime = " + millesimeSelected.id
        query += " AND promotion.formation = formation.id"

        if(authenticateService.ifAnyGranted("ROLE_FORMATIONMANAGER"))
        {
            def userInstance = authenticateService.userDomain()
            def manager = FormationManager.findByUser(userInstance)
            query += " AND formation.id = " + manager.formation.id
        }



        query += " ORDER BY " + params.sort.replaceAll(/\_/, '.') + " " + params.order

        def students = sessionFactory.currentSession.createQuery(query).list()

        def ret = []

        students.each { obj ->
            def val = [:]
            projections.eachWithIndex { obj2, i ->
                def key = obj2.replaceAll(/\./, '_')
                val[key] = obj[i]
            }
            ret << val
        }

        def data = [
                totalRecords: students.size(),
                results: ret
        ]

        render data as JSON

    }


    
}
