package me.hcl.seekin.Auth.Role

import grails.test.*

class ExternalTests extends GrailsUnitTestCase {
    /** Attribute used to build an external instance */
    def external

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
		/** Add the dynamic method validate to our External class */
		mockForConstraintsTests(External)

		/** Build a correct instance of External and test that the validation is correct */
		external = new External()
		assertTrue external.validate()

		/** Testing authority construction */
		assertEquals 'Authority construction is ok.', 'ROLE_EXTERNAL', external.authority
	}
}
