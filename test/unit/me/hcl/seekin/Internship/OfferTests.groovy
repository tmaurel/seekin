package me.hcl.seekin.Internship

import grails.test.*
import me.hcl.seekin.Ressource.Document
import me.hcl.seekin.Auth.User

/**
 * OfferTests class
 */
class OfferTests extends GrailsUnitTestCase {
	
	/** attribute used to build a Offer instance */
	def offer
	
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
	
	void testConstraints() {		
		
		/** Add the dynamic method validate to our offer class */
		mockForConstraintsTests(Offer)
		
		/** Build a correct instance of offer and test that the validation is correct */
		offer = new Offer(subject: "Selection of a dynamic language", description: "You need to select a dynamic language", beginAt: "2010/03/29", length: 26, validated: true, assignated: true, file: new Document(), company: new Company(), author: new User())
		assertTrue offer.validate()
		
		/** Testing blank attributes */
		offer = new Offer(subject: "", description: "")
		offer.validate()
		
		assertEquals 'subject is blank.', 'blank', offer.errors['subject']
		assertEquals 'description is blank.', 'blank', offer.errors['description']

        offer = new Offer()

        assertFalse offer.validate()
		assertEquals 'subject is null.', 'nullable', offer.errors['subject']
        assertEquals 'description is null.', 'nullable', offer.errors['description']
        assertEquals 'beginAt is null.', 'nullable', offer.errors['beginAt']
        assertEquals 'validated is null.', 'nullable', offer.errors['validated']
        assertEquals 'assignated is null.', 'nullable', offer.errors['assignated']
        assertEquals 'length is null.', 'nullable', offer.errors['length']
        assertEquals 'company is null.', 'nullable', offer.errors['company']
        assertEquals 'author is null.', 'nullable', offer.errors['author']
		
		offer.length = 0
		assertFalse offer.validate()
		assertEquals '0 is below range for length', 'range', offer.errors['length']
		
		offer.length = 100
		assertFalse offer.validate()
		assertEquals '0 is above range for length', 'range', offer.errors['length']

        offer.description = 'a' * 1001
        assertFalse offer.validate()
        assertEquals 'the description size is exceeded', 'maxSize', offer.errors['description']
		
	}

    void testMethods() {
        mockDomain(Offer)

        offer = new Offer(subject: "Selection of a dynamic language", description: "You need to select a dynamic language", beginAt: "2010/03/29", length: 26, validated: true, assignated: true, file: new Document(), company: new Company(), author: new User())
        assertEquals offer.toString(), "Selection of a dynamic language"

        assertEquals offer.getStatus(), 'offer.assignated'
        offer.assignated = false
        assertEquals offer.getStatus(), 'offer.validated'
        offer.validated = false
        assertEquals offer.getStatus(), 'offer.waitForValidation'
        offer.reason = "Some reason"
        assertEquals offer.getStatus(), 'offer.unvalidated'
    }
}
