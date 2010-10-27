package me.hcl.seekin.Formation

import me.hcl.seekin.Ressource.Document
import me.hcl.seekin.Auth.Role.Student
import me.hcl.seekin.Auth.Role.FormationManager
import me.hcl.seekin.Ressource.EducationalDoc

/*
 * Formation gives a description of the formation and some attached files
 */
class Formation {

	static hasMany = [promotions: Promotion, educationalDocs: EducationalDoc]

        /** The manager of this formation */
        FormationManager manager

	/** Label of the formation */
	String label
	
	/** Short description of the formation */
	String description
	
	/** Constraints used to check if an instance is correct */
	static constraints = {
				label(blank: false)
				description(blank: false)
				manager(nullable: true)
	}

	String toString() {
		label
	}
}
