package me.hcl.seekin

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Util.Address
import me.hcl.seekin.Internship.Internship
import me.hcl.seekin.Internship.Offer

/*
 * Company identifies a company and groups contact information
 */
class Company {
	
	/** Name of the company */ 
	String name
	
	/** Address */
	Address address
	
	/** Phone number */
	String phone

    /** A company may have many employees */
	/** A company may be ossociate with many internships */
    /** A company may propose many offers */

	static hasMany = [ employees : User, internships : Internship, offers: Offer]

	/** Constraints used to check if an instance is correct */
    static constraints = {
		name(blank: false)
		address(nullable: true)
		phone(nullable:true, size: 10..10)
    }

    String toString() {
        name
    }
}
