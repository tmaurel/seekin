package me.hcl.seekin.Internship

import grails.test.*
import me.hcl.seekin.Util.Document;

/**
 * OfferTests class
 */
class OfferTests extends GrailsUnitTestCase {
	
	/** attribute used to build a Profile instance */
	def offer
	
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
	
	void testValidation() {		
		
		/** Add the dynamic method validate to our offer class */
		mockForConstraintsTests(Offer)
		
		/** Build a correct instance of offer and test that the validation is correct */
		offer = new Offer(subject: "Selection of a dynamic language", description: "You need to select a dynamic language", beginAt: "2010/03/29", length: 26, status: "unavailable", file: new Document())
		assertTrue offer.validate()
		
		/** Testing blank and nullable attributes */
		offer = new Offer(subject: "", description: "", beginAt: "", status: "")
		offer.validate()
		
		assertEquals 'subject is blank.', 'blank', offer.errors['subject']
		assertEquals 'description is blank.', 'blank', offer.errors['description']
		assertEquals 'beginAt is nullable.', 'nullable', offer.errors['beginAt']
		
		offer.length = 0
		assertFalse offer.validate()
		assertEquals '0 is below range for length', 'range', offer.errors['length']
		
		offer.length = 100
		assertFalse offer.validate()
		assertEquals '0 is above range for length', 'range', offer.errors['length']
		
		assertEquals 'status is blank.', 'blank', offer.errors['status']
		
	}
}
