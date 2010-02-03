package me.hcl.seekin.Util

import me.hcl.seekin.Profile.Profile
import me.hcl.seekin.Company

/*
 * Address
 */
class Address {
	
	static belongsTo = [ Profile, Company ]

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
}
