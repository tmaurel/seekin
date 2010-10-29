package me.hcl.seekin.Ressource

import grails.test.*

class DocumentTests extends GrailsUnitTestCase {
	/** attribute used to build a Document instance */
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
		document = new Document(title: "Document", fileData: new FileData())
		assertTrue document.validate()
		
		/** Testing blank attributes */
		document = new Document(title: "", fileData: new FileData())
		assertFalse document.validate()
		assertEquals 'title is blank.', 'blank', document.errors['title']

        document = new Document()
        assertFalse document.validate()
        assertEquals 'title is null.', 'nullable', document.errors['title']
        assertEquals 'fileData is null.', 'nullable', document.errors['fileData']
	}

    void testMethods() {
        mockDomain(Document)

        // Test toString method
        document = new Document(title: "Document", fileData: new FileData())
        assertEquals document.toString(), 'Document'        

    }
}
