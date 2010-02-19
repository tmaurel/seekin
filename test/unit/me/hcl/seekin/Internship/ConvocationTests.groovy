package me.hcl.seekin.Internship

import grails.test.*

class ConvocationTests extends GrailsUnitTestCase {
	
	/** Attribute used to build a Assignment instance */
	def convocation
	
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
		
		/** Add the dynamic method validate to our Assignment class */
		mockForConstraintsTests(Convocation)
		
		/** Build a correct instance of Assignment and test that the validation is correct */
		convocation = new Convocation(date: "2010/09/26", building: "D", room: "A113", internship: new Internship())
		assertTrue convocation.validate()
		
		/** Testing blank attributes */
		convocation = new Convocation()
		assertFalse convocation.validate()
		assertEquals 'date is null.', 'nullable', convocation.errors['date']
	}
}
