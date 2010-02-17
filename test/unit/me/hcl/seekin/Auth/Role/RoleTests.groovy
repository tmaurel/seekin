package me.hcl.seekin.Auth.Role

import grails.test.*

class RoleTests extends GrailsUnitTestCase {
    /** Attribute used to build a role instance */
    def role

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
		/** Add the dynamic method validate to our Role class */
		mockForConstraintsTests(Role)

		/** Build a correct instance of Role and test that the validation is correct */
		role = new Role()
		assertTrue role.validate()

		/** Testing authority construction */
		assertEquals 'Authority construction is ok.', 'ROLE_ROLE', role.authority
	}
}
