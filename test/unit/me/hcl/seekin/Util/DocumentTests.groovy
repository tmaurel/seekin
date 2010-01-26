package me.hcl.seekin.Util

import grails.test.*

class DocumentTests extends GrailsUnitTestCase {
	/** attribute used to build a Profile instance */
	def document
	
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
		
		/** Add the dynamic method validate to our Document class */
		mockForConstraintsTests(Document)
		
		/** Build a correct instance of Document and test that the validation is correct */
		document = new Document(title: "Document", uri: "upload/document/document.pdf")
		assertTrue document.validate()
		
		/** Testing blank attributes */
		document = new Document(title: "", uri: "")
		document.validate()
		assertEquals 'title is blank.', 'blank', document.errors['title']
		assertEquals 'uri is blank.', 'blank', document.errors['uri']
	}
}
