package me.hcl.seekin

import me.hcl.seekin.Internship.Internship

/*
 * Convocation contains information about the oral exam of the internship 
 */
class Convocation {
	
	/** Convocation date for oral exam */
	Date date
	
	/** Building where the oral exam will take place */
	String building
	
	/** Room where the oral exam will take place */
	String room

    /** A convocation is attributed to an internship */
	static belongsTo = [ internship : Internship ]
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		date(nullable: false)
		building()
		room()
	}
}
