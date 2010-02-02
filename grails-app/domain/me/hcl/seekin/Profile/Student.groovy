package me.hcl.seekin.Profile

import me.hcl.seekin.Internship.Internship

/*
 * A student is a particular profile
 */
class Student extends Profile {

    /** A student can have many internship during his studies */
	static hasMany = [ internships : Internship ]
	
    static constraints = {
    }
  
}
