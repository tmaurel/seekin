package me.hcl.seekin

import grails.test.*
import me.hcl.seekin.Util.Address

/**
 * CompanyTests class
 */
class CompanyTests extends GrailsUnitTestCase {
	
	/** attribute used to build a Company instance */
	def company
	
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraints() {		
		
		/** Add the dynamic method validate to our Company class */
		mockForConstraintsTests(Company)
		
		/** Build a correct instance of Company and test that the validation is correct */
		company = new Company(name: "Mohammed", address: new Address(), phone: "0612345678")
		assertTrue company.validate()

		/** Build an instance of Company with a bad phone number format and test that the validation is false */
		company = new Company(name: "Mohammed", address: new Address(), phone: "061234567")
		assertFalse company.validate()
		
		/** Testing blank attributes */
		company = new Company(name: "", address: new Address())
		assertFalse company.validate()
		assertEquals 'name is blank.', 'blank', company.errors['name']
    }
}
