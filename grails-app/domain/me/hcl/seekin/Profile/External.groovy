package me.hcl.seekin.Profile

/*
 * An external person is a particular profile
 */
class External extends Profile {
  
	Company company
	Boolean formerStudent
	
    static constraints = {
    	company(nullable: true)
	}
  
}
