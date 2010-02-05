package me.hcl.seekin.Util

import grails.test.*

/**
 * AddressTests class
 */
class AddressTests extends GrailsUnitTestCase {

    /** attribute used to build a Address instance */
	def address

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

	/*
	 * Method used to test the validation of instances and errors returned when the validation failed
	 */
    void testConstraints() {

        /** Add the dynamic method validate to our Address class */
		mockForConstraintsTests(Address)

		/** Build a correct instance of Profile and test that the validation is correct */
		address = new Address(street: "12 rue des riveaux", zipCode: "63320", town: "chidrac")

		assertTrue address.validate()

        address = new Address(street: "", zipCode: "", town:"")
        assertFalse address.validate()

		assertEquals 'street is blank.', 'blank', address.errors['street']
		assertEquals 'zipCode is blank.', 'blank', address.errors['zipCode']
		assertEquals 'town is blank.', 'blank', address.errors['town']

        address = new Address(street: "12 rue des riveaux", zipCode: "6332a", town: "chidrac")
        assertFalse address.validate()

        assertEquals "doesn't have a zip code syntax", 'matches', address.errors['zipCode'] 

    }
}
