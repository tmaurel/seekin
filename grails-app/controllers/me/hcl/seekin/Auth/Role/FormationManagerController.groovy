package me.hcl.seekin.Auth.Role

import grails.converters.JSON
import me.hcl.seekin.Formation.Millesime
import org.hibernate.FetchMode as FM

class FormationManagerController {

    def sessionFactory

    def index = { }

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


    def table = {
        def millesimes = Millesime.findAllByBeginDateLessThan(new Date())

        def columnDefs = projections.collect {
            def key = it.replaceAll(/\./, '_')
            [key: key, sortable: true, resizeable: true, label: it, formatter:'managerTableFormatter']
        }

        return [projections: projections, columnDefs: columnDefs, millesimes:millesimes]
    }

    def dataTableDataAsJSON = {
    
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
