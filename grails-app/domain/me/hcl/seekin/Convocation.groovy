package me.hcl.seekin

class Convocation {
	
	/** Convocation date for oral exam */
	Date date
	
	/** Building where the oral exam will take place */
	String building
	
	/** Room where the oral exam will take place */
	String room
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		date(nullable: false)
		building()
		room()
	}
}
