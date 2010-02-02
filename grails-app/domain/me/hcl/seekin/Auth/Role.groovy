package me.hcl.seekin.Auth

/**
 * Authority domain class.
 */
class Role {

    /** A role can be assigned to many users */ 
	static hasMany = [people: User]

	/** description */
	String description
	/** ROLE String */
	String authority

    /** Constraints used to check if an instance is correct */
	static constraints = {
		authority(blank: false, unique: true)
		description()
	}
}
