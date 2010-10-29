package me.hcl.seekin.Internship

import grails.test.*
import me.hcl.seekin.Auth.Role.External
import me.hcl.seekin.Auth.Role.Student
import me.hcl.seekin.Auth.Role.Staff
import me.hcl.seekin.Formation.Millesime

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
        mockForConstraintsTests(Student)
        mockForConstraintsTests(Millesime)
		
		/** Build a correct instance of Internship and test that the validation is correct */
		internship = new Internship(subject: "Testing dynamic languages", beginAt: "2010/03/28", isApproval: true, student: new Student(), length: 6, millesime: new Millesime(), company: new Company())
		assertTrue internship.validate()
		
		/** Testing blank attributes */
		internship = new Internship(subject: "", beginAt: "2010/03/28", isApproval: true, student: new Student(), length: 6, millesime: new Millesime(), company: new Company())
		assertFalse internship.validate()
		assertEquals 'subject is blank.', 'blank', internship.errors['subject']
		
		/** Testing nullable attributes */
        internship = new Internship()
        assertFalse internship.validate()
        assertEquals 'subject is null.', 'nullable', internship.errors['subject']
        assertEquals 'beginAt is null.', 'nullable', internship.errors['beginAt']
        assertEquals 'student is null.', 'nullable', internship.errors['student']
        assertEquals 'length is null.', 'nullable', internship.errors['length']
        assertEquals 'millesime is null.', 'nullable', internship.errors['millesime']
	}

    void testMethods() {
        mockDomain(Internship)

        // Test toString method
        internship = new Internship(subject: "Testing dynamic languages", beginAt: "2010/03/28", isApproval: true, student: new Student(), length: 6, millesime: new Millesime())
        assertEquals internship.toString(), "Testing dynamic languages"

        // test getStatus method
        assertEquals internship.getStatus(), 'internship.validated'
        internship.isApproval = false
        assertEquals internship.getStatus(), 'internship.waitForValidation'
        internship.reason = "Some reason"
        assertEquals internship.getStatus(), 'internship.unvalidated'
    }
}