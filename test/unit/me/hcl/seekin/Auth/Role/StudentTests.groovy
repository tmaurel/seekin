package me.hcl.seekin.Auth.Role

import grails.test.*

class StudentTests extends GrailsUnitTestCase {
    /** Attribute used to build a student instance */
    def student

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
		/** Add the dynamic method validate to our Student class */
		mockForConstraintsTests(Student)

		/** Build a correct instance of Student and test that the validation is correct */
		student = new Student()
		assertTrue student.validate()

		/** Testing authority construction */
		assertEquals 'Authority construction is ok.', 'ROLE_STUDENT', student.authority
	}
}
