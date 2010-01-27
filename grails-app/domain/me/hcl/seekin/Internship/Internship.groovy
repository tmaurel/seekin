package me.hcl.seekin.Internship

class Internship {
	
	/** Subject of the Internship */
	String subject
	
	/** Beginning of the Internship */
	Date beginAt
	
	/** Indicate if the Internship is approved by Staff member */
	Boolean isApproval
	
	/** Report of the Internship */
	Report report
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		subject(blank: false)
		beginAt(nullable: false)
		isApproval()
		report()
	}
}
