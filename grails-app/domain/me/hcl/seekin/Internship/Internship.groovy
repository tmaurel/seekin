package me.hcl.seekin.Internship

class Internship {
	
	String subject
	Date beginAt
	Boolean isApproval
	
    static constraints = {
		subject(blank: false)
		beginAt(nullable: false)
		isApproval()
    }
}
