package me.hcl.seekin.Auth.Role

import grails.test.*

class AdminTests extends GrailsUnitTestCase {
    /** Attribute used to build an admin instance */
    def admin

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    /*
	 * Method used to test the validation of instances and errors returned when the validation failed
	 */
	void testConstraints()
	{
		/** Add the dynamic method validate to our Admin class */
		mockForConstraintsTests(Admin)

		/** Build a correct instance of Admin and test that the validation is correct */
		admin = new Admin()
		assertTrue admin.validate()

		/** Testing authority construction */
		assertEquals 'Authority construction is ok.', 'ROLE_ADMIN', admin.authority
	}
}
