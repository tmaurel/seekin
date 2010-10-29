package me.hcl.seekin.Internship

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
		company = new Company(name: "Mohammed", phone: "0612345678")
		assertTrue company.validate()

		/** Build an instance of Company with a bad phone number format and test that the validation is false */
		company = new Company(name: "Mohammed", phone: "061234567")
		assertFalse company.validate()
        assertEquals 'bad phone number format.', 'size', company.errors['phone']
		
		/** Testing blank attributes */
		company = new Company(name: "", address: new Address())
		assertFalse company.validate()
		assertEquals 'name is blank.', 'blank', company.errors['name']
    }

    void testMethods() {
        mockDomain(Company)
        company = new Company(name: "Mohammed", phone: "0612345678")

        // Test toString Method
        assertEquals company.toString(), "Mohammed"
    }
}
