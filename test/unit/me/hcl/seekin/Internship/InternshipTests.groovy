package me.hcl.seekin.Internship

import grails.test.*

class InternshipTests extends GrailsUnitTestCase {
	/** Attribute used to build a Internship instance */
	def internship
	
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
		/** Add the dynamic method validate to our Internship class */
		mockForConstraintsTests(Internship)
		
		/** Build a correct instance of Internship and test that the validation is correct */
		internship = new Internship(subject: "Testing dynamic languages", beginAt: "2010/03/28", isApproval: true, report: new Report())
		assertTrue internship.validate()
		
		/** Testing blank attributes */
		internship = new Internship(subject: "", beginAt: "2010/03/28", isApproval: true, report: new Report())
		assertFalse internship.validate()
		assertEquals 'subject is blank.', 'blank', internship.errors['subject']
		
		/** Testing nullable attributes */
		internship = new Internship()
		assertFalse internship.validate(subject: "Testing dynamic languages", isApproval: true)
		assertEquals 'beginAt is null.', 'nullable', internship.errors['beginAt']
	}
}
