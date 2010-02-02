package me.hcl.seekin.Auth

import me.hcl.seekin.Profile.Profile

/**
 * User domain class.
 */
class User {

    /** A user can have some Role */
	static hasMany = [ authorities : Role ]
	static belongsTo = Role	

    /** Email which is used as a login */
	String email

	/** MD5 Password */
	String password

	/** Indicates if the user is enabled by an administrator */
	boolean enabled

    /** Indicates if the user wants to share his email */
	boolean showEmail

    /** Profile of the user with more information about him */
	Profile profile

	/** Constraints used to check if an instance is correct */
	static constraints = {
		email(blank: false, unique: true, email: true)
		password(blank: false)
		enabled()
		profile(nullable: true)
	}

    /** Load the profile when it loads the user */
	static mapping = {
		profile lazy: false
	}
}
