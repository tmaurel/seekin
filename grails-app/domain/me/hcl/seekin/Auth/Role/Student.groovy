package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Formation.*
import me.hcl.seekin.Internship.Internship

/*
 * A student is a particular Role
 */
class Student extends Role {

    /** Make Students searchable */
    static searchable = {
            user(component:true)
            spellCheck "include"

    }

    static belongsTo = Promotion
    static hasMany = [ internships : Internship, promotions : Promotion ]

    /** Visible ? **/
    Boolean visible

    static constraints = {
        visible(nullable: false)
    }

    String toString() {
            user?.firstName + " " + user?.lastName
    }
}
