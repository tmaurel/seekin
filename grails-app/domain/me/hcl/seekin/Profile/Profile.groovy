package me.hcl.seekin.Profile

class Profile {
	
	/** First name */
	String firstName
	
	/**	Last name */
	String lastName
	
	/** Address */
	String address
	
	/** phone number */
	String phone
	
    static constraints = {
		firstName(blank: false)
		lastName(blank: false)
		address(blank: false)
		phone(size:10)
    }
}
