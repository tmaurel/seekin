package me.hcl.seekin.Internship

import me.hcl.seekin.Profile.*
import me.hcl.seekin.Convocation

class Internship {
	
	/** Subject of the Internship */
	String subject
	
	/** Beginning of the Internship */
	Date beginAt
	
	/** Indicate if the Internship is approved by Staff member */
	Boolean isApproval
	
	Student student
	
	Staff academicTutor
	
	External companyTutor
	
	/** Report of the Internship */
	Report report
	
	Convocation convocation
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		subject(blank: false)
		beginAt(nullable: false)
		isApproval()
		report(nullable: true)
	}
}
