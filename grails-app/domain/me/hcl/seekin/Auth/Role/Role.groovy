package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Util.Address

/*
 * Role class
 */
class Role {
	static transients = [ "authority" ]
	/** User which is identified by the Role */
	static hasMany = [people: User]
        static belongsTo = User
	
	/** description */
	String description

	/** ROLE String */
	String authority = "ROLE_USER"

        /** Visible ? **/
        Boolean visible
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
        visible(nullable: true)
        authority(blank: false, unique: false)
        description(nullable: true)
    }
}