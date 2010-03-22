package me.hcl.seekin.Internship

import me.hcl.seekin.Ressource.Document

class Report extends Document {
	
    /* Indicate if the Student wants share his Report */
    Boolean isPrivate = false

    /* A report belongs to an Internship */
    static belongsTo = Internship

    Internship internship
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
        isPrivate(nullable: true)
    }

	String toString() {
		title
	}
}
