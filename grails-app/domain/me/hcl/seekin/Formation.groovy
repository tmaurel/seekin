package me.hcl.seekin

import me.hcl.seekin.Formation
import me.hcl.seekin.Util.Document

/*
 * Formation gives a description of the formation and some attached files
 */
class Formation {

	static hasMany = [ formations : Formation ]

	/** Label of the formation */
	String label
	
	/** Short description of the formation */
	String description
	
	/** File which details the formation */
	Document file
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		label(blank: false)
		description(blank: false)
                file(nullable: true)
    }
}
