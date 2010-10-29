package me.hcl.seekin.Internship

import grails.test.*
import org.springframework.context.i18n.LocaleContextHolder as LCH

class ConvocationTests extends GrailsUnitTestCase {
	
	/** Attribute used to build a Assignment instance */
	def convocation
	
	protected void setUp() {
		super.setUp()
        Date.metaClass.formatDate = { ->

			def locale = LCH.getLocale()
			def pattern
			if(locale.toString() == "fr") {
				pattern = "dd/MM/yyyy"
			}
			else {
				pattern = "MM/dd/yyyy"
			}

			return delegate.format(pattern)
		}
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

    void testMethods() {
        mockDomain(Convocation)

        convocation = new Convocation(date: "2010/09/26", building: "D", room: "A113", internship: new Internship(subject: "Good internship"))
        assertEquals convocation.toString(), "09/26/2010 - Good internship - D A113"
    }
}
