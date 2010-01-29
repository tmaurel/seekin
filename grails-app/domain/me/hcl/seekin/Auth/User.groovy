package me.hcl.seekin.Auth

/**
 * User domain class.
 */
class User {
	static hasMany = [authorities: Role]
	static belongsTo = Role

	String email
	/** MD5 Password */
	String password
	/** enabled */
	boolean enabled

	boolean showEmail

	static constraints = {
		email(blank: false, unique: true, email: true)
		password(blank: false)
		enabled()
	}
}
