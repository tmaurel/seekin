package me.hcl.seekin.Internship

import me.hcl.seekin.Ressource.Document

class Offer {
	
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
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		subject(blank: false)
		description(blank: false)
		beginAt(nullable: false)
		length(range: 1..52)
		status(blank: false)
        file(nullable: true)
    }
}
