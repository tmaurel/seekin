package me.hcl.seekin

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
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		name(blank: false)
		address(blank: false)
		phone(size: 10..10)
    }
}
