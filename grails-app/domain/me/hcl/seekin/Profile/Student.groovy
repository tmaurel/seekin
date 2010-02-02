package me.hcl.seekin.Profile

import me.hcl.seekin.Internship.Internship

class Student extends Profile {
	
	static hasMany = [ internships : Internship ]
	
    static constraints = {
    }
}
