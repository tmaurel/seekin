package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Company

/*
 * An external person is a particular Role
 */
class External extends Role {
  
	Company company
        
	Boolean formerStudent

        static constraints = {
		company(nullable: true)
		formerStudent(nullable: true)
	}
  
}
