package me.hcl.seekin.Internship

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
	static belongsTo = Internship

        Internship internship
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		date(nullable: false)
		building()
		room()
	}

	String toString() {
		date.formatDate() + " - " + internship?.subject + " - " + building + " " + room
	}
}
