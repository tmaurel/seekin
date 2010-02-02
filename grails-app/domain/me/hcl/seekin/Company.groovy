package me.hcl.seekin

import me.hcl.seekin.Profile.External

/*
 * Company identifies a company and groups contact information
 */
class Company {
	
	/** Name of the company */ 
	String name
	
	/** Address */
	String address
	
	/** Phone number */
	String phone
	
	static hasMany = [ employees : External ]
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		name(blank: false)
		address(blank: false)
		phone(size: 10..10)
    }
}
