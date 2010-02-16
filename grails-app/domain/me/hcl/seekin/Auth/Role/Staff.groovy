package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Internship.Internship

/*
 * A staff member is a particular Role
 */
class Staff extends Role {

    static hasMany = [ internships : Internship ]

    static constraints = {
    }
  
}
