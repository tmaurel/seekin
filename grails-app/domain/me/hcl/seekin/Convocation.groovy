package me.hcl.seekin

import me.hcl.seekin.Internship.Internship

class Convocation {
	
	/** Convocation date for oral exam */
	Date date
	
	/** Building where the oral exam will take place */
	String building
	
	/** Room where the oral exam will take place */
	String room
	
	Internship internship
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		date(nullable: false)
		building()
		room()
		internship(nullable: false)
	}
}
