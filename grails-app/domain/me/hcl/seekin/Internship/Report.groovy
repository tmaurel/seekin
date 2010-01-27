package me.hcl.seekin.Internship

import me.hcl.seekin.Util.Document

class Report extends Document {
	
	/* Indicate if the Student wants share his Report */
	Boolean isPrivate
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		isPrivate()
	}
}
