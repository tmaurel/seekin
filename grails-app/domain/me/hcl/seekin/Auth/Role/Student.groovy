package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Formation.*
import me.hcl.seekin.Internship.Internship

/*
 * A student is a particular Role
 */
class Student extends Role {

    static hasMany = [ internships : Internship ]

    /** A student follows a formation */
    Promotion promotion

    /** Visible ? **/
    Boolean visible

    static constraints = {
        promotion(nullable: true)
        visible(nullable: true)
    }

}
