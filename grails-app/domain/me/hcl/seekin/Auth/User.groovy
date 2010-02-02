package me.hcl.seekin.Auth

import me.hcl.seekin.Profile.Profile

/**
 * User domain class.
 */
class User {
	static hasMany = [ authorities : Role ]
	static belongsTo = Role	
		
	String email
	/** MD5 Password */
	String password
	/** enabled */
	boolean enabled

	boolean showEmail

	Profile profile

	static constraints = {
		email(blank: false, unique: true, email: true)
		password(blank: false)
		enabled()
		profile(nullable: true)
	}
	
	static mapping = {
		profile lazy: false
	}
}
