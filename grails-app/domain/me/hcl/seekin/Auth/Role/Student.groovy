package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Internship.Internship

/*
 * A student is a particular Role
 */
class Student extends Role {

    static hasMany = [ internships : Internship ]

    /** Visible ? **/
    Boolean visible

    static constraints = {
        visible(nullable: true)
    }
  
}
