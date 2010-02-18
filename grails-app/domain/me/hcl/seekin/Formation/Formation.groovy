package me.hcl.seekin.Formation

import me.hcl.seekin.Ressource.Document
import me.hcl.seekin.Auth.Role.Student

/*
 * Formation gives a description of the formation and some attached files
 */
class Formation {

	static hasMany = [ students : Student, promotions: Promotion]

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
