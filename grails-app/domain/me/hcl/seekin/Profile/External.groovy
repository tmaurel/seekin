package me.hcl.seekin.Profile

import me.hcl.seekin.Company

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
