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

		/** Build a correct instance of Address and test that the validation is correct */
		address = new Address(street : "1 Infinite Loop", zipCode : "95014", town : "Cupertino")
        assertTrue address.validate()

        address = new Address(street: "", zipCode: "", town:"")
        assertFalse address.validate()

		assertEquals 'street is blank.', 'blank', address.errors['street']
		assertEquals 'zipCode is blank.', 'blank', address.errors['zipCode']
		assertEquals 'town is blank.', 'blank', address.errors['town']

        address = new Address()
        assertFalse address.validate()
        assertEquals 'street is null.', 'nullable', address.errors['street']
		assertEquals 'zipCode is null.', 'nullable', address.errors['zipCode']
		assertEquals 'town is null.', 'nullable', address.errors['town']

        address = new Address(street: "12 rue des riveaux", zipCode: "6332a", town: "chidrac")
        assertFalse address.validate()
        assertEquals "doesn't have a zip code syntax", 'matches', address.errors['zipCode']

    }

    void testMethods() {
        mockDomain(Address)
        
        address = new Address(street : "1 Infinite Loop", zipCode : "95014", town : "Cupertino")
        assertEquals address.toString(), '1 Infinite Loop 95014 Cupertino'

    }
}
