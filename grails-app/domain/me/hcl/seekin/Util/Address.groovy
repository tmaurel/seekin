package me.hcl.seekin.Util

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Company

/*
 * Address
 */
class Address {
	
	/** Make addresses searchable */

    static belongsTo = [ User, Company, Internship ]

    /** Street of the address */
    String street

    /** Zip code */
    String zipCode

    /** Town */
    String town

    static constraints = {
      street(blank: false)
      zipCode(blank: false, matches: /\d+/)
      town(blank: false)
    }

	String toString() {
		street + " " + zipCode + " " + town
	}
}
