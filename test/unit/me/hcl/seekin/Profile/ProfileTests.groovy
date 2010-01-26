package me.hcl.seekin.Profile

import grails.test.*

/**
 * ProfileTests class
 */
class ProfileTests extends GrailsUnitTestCase {
	
	/** attribute used to build a Profile instance */
	def profile
	
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
	
	/*
	 * Method used to test the validation of instances and errord returned when the validation failed
	 */
	void testValidation()
	{		
		
		/** Add the dynamic method validate to our Profile class */
		mockForConstraintsTests(Profile)

		/** Build a correct instance of Profile and test that the validation is correct */
		profile = new Profile(firstName: "Mohammed", lastName: "Ben Boukeffa", address: "bled", phone: "0612345678")

		assertTrue profile.validate()
		
		/** Build an instance of Profile with a bad phone number format and test that the validation is false */
		profile = new Profile(firstName: "Mohammed", lastName: "Ben Boukeffa", address: "bled", phone: "061234567")
		
		assertFalse profile.validate()

		/** Testing blank attributes */
		profile = new Profile(firstName: "", lastName: "", address: "")
		profile.validate()
		
		assertEquals 'firstName is blank.', 'blank', profile.errors['firstName']
		assertEquals 'lastName is blank.', 'blank', profile.errors['lastName']
		assertEquals 'address is blank.', 'blank', profile.errors['address']
	}
}
