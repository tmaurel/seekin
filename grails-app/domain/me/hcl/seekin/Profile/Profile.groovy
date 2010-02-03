package me.hcl.seekin.Profile

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Util.Address

/*
 * Profile class
 */
class Profile {
	
	/** User which identified the profile */
	static belongsTo = [ user :  User ]
	
	/** First name */
	String firstName
	
	/**	Last name */
	String lastName
	
	/** Address */
	Address address
	
	/** Phone number */
	String phone
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		firstName(blank: false)
		lastName(blank: false)
		address(nullable: false)
		phone(size: 10..10)
    }
}