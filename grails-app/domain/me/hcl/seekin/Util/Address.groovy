package me.hcl.seekin.Util

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Company

/*
 * Address
 */
class Address {
	
	/** Make addresses searchable */
	static searchable = {
		all false
	}

    static belongsTo = [ User, Company ]

    /** Street of the address */
    String street

    /** Zip code */
    String zipCode

    /** Town */
    String town

    static constraints = {
      street(blank: true)
      zipCode(blank: true, matches: /\d+/)
      town(blank: true)
    }

	String toString() {
		street + " " + zipCode + " " + town
	}
}
