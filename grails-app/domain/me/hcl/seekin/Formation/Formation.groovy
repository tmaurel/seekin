package me.hcl.seekin.Formation

import me.hcl.seekin.Ressource.Document
import me.hcl.seekin.Auth.Role.Student
import me.hcl.seekin.Auth.Role.FormationManager

/*
 * Formation gives a description of the formation and some attached files
 */
class Formation {

	static hasMany = [promotions: Promotion]

        /** The manager of this formation */
        FormationManager manager

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
                    manager(nullable: true)
        }
}
