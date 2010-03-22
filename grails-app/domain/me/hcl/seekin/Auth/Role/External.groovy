package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Company
import me.hcl.seekin.Internship.Internship

/*
 * An external person is a particular Role
 */
class External extends Role {

    /** Make Externals searchable */
    static searchable = {
            user(component:true)
            spellCheck "include"

    }
	
    static hasMany = [ internships : Internship ]

	Company company
        
	Boolean formerStudent = false

	static constraints = {
		company(nullable: true)
		formerStudent(nullable: true)
	}
	
    String toString() {
            user?.firstName + " " + user?.lastName
    }
}
