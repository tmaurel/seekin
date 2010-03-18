package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Internship.Internship

/*
 * A staff member is a particular Role
 */
class Staff extends Role {

    /** Make Staff searchable */
    static searchable = {
            user(component:true)
            spellCheck "include"

    }
	
    static hasMany = [ internships : Internship ]

    static constraints = {
    }

	String toString() {
            user?.firstName + " " + user?.lastName
    }
}
