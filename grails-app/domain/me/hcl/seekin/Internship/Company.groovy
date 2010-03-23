package me.hcl.seekin.Internship

import me.hcl.seekin.Auth.Role.External
import me.hcl.seekin.Util.Address


/*
 * Company identifies a company and groups contact information
 */
class Company {

        static belongsTo = [Internship, Offer]

	/** Make Companies searchable */
	static searchable = {
		spellCheck "include"
	}

	/** Name of the company */ 
	String name
	
	/** Address */
	Address address
	
	/** Phone number */
	String phone

    /** A company may have many employees */
	/** A company may be ossociate with many internships */
    /** A company may propose many offers */

	static hasMany = [ employees : External, internships : Internship, offers: Offer]

	/** Constraints used to check if an instance is correct */
    static constraints = {
		name(nullable:false, blank: false)
		address(nullable: true)
		phone(nullable:true, size: 10..10)
    }

    String toString() {
        name
    }
}
