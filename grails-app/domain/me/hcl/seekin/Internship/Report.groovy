package me.hcl.seekin.Internship

import me.hcl.seekin.Ressource.Document

class Report extends Document {
	
	/* Indicate if the Student wants share his Report */
	Boolean isPrivate
	
	/* A report belongs to an Internship */
	static belongsTo = [ internship : Internship ]
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		isPrivate()
	}
}
