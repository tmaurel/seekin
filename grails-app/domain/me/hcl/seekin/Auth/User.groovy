package me.hcl.seekin.Auth

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Util.Address
import me.hcl.seekin.Auth.Role.Role

/**
 * User domain class.
 */
class User {

    /** A user can have some Role */
	static hasMany = [ authorities : Role ]

    /** Email which is used as a login */
	String email

	/** MD5 Password */
	String password

	/** Indicates if the user is enabled by an administrator */
	boolean enabled

	/** First name */
	String firstName

	/**	Last name */
	String lastName

	/** Address */
	Address address

	/** Phone number */
	String phone

    /** Indicates if the user wants to share his email */
	boolean showEmail

	/** Constraints used to check if an instance is correct */
	static constraints = {
		email(blank: false, unique: true, email: true)
		password(blank: false)
		enabled()
                firstName(blank: false)
		lastName(blank: false)
		address(nullable: false)
		phone(size: 10..10)
	}
}
