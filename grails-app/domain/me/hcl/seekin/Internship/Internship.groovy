package me.hcl.seekin.Internship

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Auth.Role.Student
import me.hcl.seekin.Auth.Role.Staff
import me.hcl.seekin.Auth.Role.External
import me.hcl.seekin.Company
import me.hcl.seekin.Formation.Millesime

class Internship {

	static searchable = {
		student(component:true)
		company(component:true)
	}

	/** Subject of the Internship */
	String subject
	
	/** Beginning of the Internship */
	Date beginAt
	
	/** Indicate if the Internship is approved by Staff member */
	Boolean isApproval

    /** Indicates if the internship is from an offer which was posted on seekin */
    Boolean fromOffer

        /** Student who is concerned by the internship */
	Student student

    /** Tutor who is member of the university */
	Staff academicTutor

    /** Tutor who is member of the company which posts the internship */  
	External companyTutor

	/** Company where the student will do this internship */
	Company company

	/** Report of the Internship */
	Report report

    /** Convocation for the oral test of the internship */
	Convocation convocation

    /** Length of the internship */
    Integer length

    /** Millesime of the internship */
    Millesime millesime
	
	/** Constraints used to check if an instance is correct */
	static constraints = {
		subject(blank: false)
		beginAt(nullable: false)
		isApproval()
		fromOffer(nullable: true)
		report(nullable: true)
		student(nullable: false)
		academicTutor(nullable: true)
		companyTutor(nullable: true)
		convocation(nullable: true)
        length(nullable: false)
        millesime(nullable: false)
	}

	String toString() {
		subject
	}
}
