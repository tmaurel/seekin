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

    /** Student who is concerned by the internship */
	Student student

    /** Tutor who is member of the university */
	Staff academicTutor

    /** Tutor who is member of the company which posts the internship */  
	External companyTutor
	
	/** Report of the Internship */
	Report report

    /** Convocation for the oral test of the internship */
	Convocation convocation
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		subject(blank: false)
		beginAt(nullable: false)
		isApproval()
		report(nullable: true)
        student(nullable: false)
        academicTutor(nullable: false)
        companyTutor(nullable: false)
        convocation(nullable: true)
	}
}
