package me.hcl.seekin

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Util.Address
import me.hcl.seekin.Internship.Internship

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
	/** A company may propose many internships */

	static hasMany = [ employees : User, internships : Internship]

	/** Constraints used to check if an instance is correct */
    static constraints = {
		name(blank: false)
		address(blank: false)
		phone(size: 10..10)
    }
}
