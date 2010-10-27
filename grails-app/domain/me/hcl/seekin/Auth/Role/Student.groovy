package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Formation.*
import me.hcl.seekin.Internship.Internship

/*
 * A student is a particular Role
 */
class Student extends Role {

    static belongsTo = Promotion
    static hasMany = [ internships : Internship, promotions : Promotion ]

    /** Visible ? **/
    Boolean visible = true

    static constraints = {
        visible(nullable: false)
    }

    String toString() {
            user?.firstName + " " + user?.lastName
    }
}
