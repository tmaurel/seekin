package me.hcl.seekin.Internship

import me.hcl.seekin.Ressource.Document
import me.hcl.seekin.Formation.Promotion
import me.hcl.seekin.Auth.User
import me.hcl.seekin.Company

class Offer {

    static hasMany = [promotions: Promotion]
    static belongsTo = [Promotion, Company]

	/** Subject of the Offer */
	String subject
	
	/** Short description of the offer */
	String description
	
	/** Date of the internship's beginning */
	Date beginAt
	
	/** Length of the internship */
	Integer length
	
	/** Status of the offer */
	String status
	
	/** File which details the offer */
	Document file

    /** User who have created the offer */
    User author

    Company company
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		subject(blank: false)
		description(blank: false)
		beginAt(nullable: false)
		length(range: 1..52)
		status(blank: false)
        file(nullable: true)
        company(nullable: false)
    }
}
