package me.hcl.seekin.Auth.Role

import grails.test.*

class StaffTests extends GrailsUnitTestCase {
    /** Attribute used to build a staff instance */
    def staff

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
		/** Add the dynamic method validate to our Staff class */
		mockForConstraintsTests(Staff)

		/** Build a correct instance of Staff and test that the validation is correct */
		staff = new Staff()
		assertTrue staff.validate()

		/** Testing authority construction */
		assertEquals 'Authority construction is ok.', 'ROLE_STAFF', staff.authority
	}
}
