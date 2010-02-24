package me.hcl.seekin.Formation

import me.hcl.seekin.Auth.Role.Student
import me.hcl.seekin.Internship.Offer

class Promotion {

	static hasMany = [students: Student, offers: Offer]
    static belongsTo = [formation: Formation, millesime: Millesime]

    static constraints = {
    }

    static getCurrentForStudent = {
        student ->
            student.promotions.find {
                it.millesime.current == true
            }
    }

    static getCurrents = {
        Promotion.findAllByMillesime(Millesime.getCurrent())
    }

    String toString() {
        formation.label + " " + millesime
    }
}
