package me.hcl.seekin.Ressource

import grails.test.*

class LinkTests extends GrailsUnitTestCase {
	/** attribute used to build a Document instance */
	def link
	
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

		/** Add the dynamic method validate to our Link class */
		mockForConstraintsTests(Link)

		/** Build a correct instance of Link and test that the validation is correct */
		link = new Link(title: "Google", url: "http://google.fr", description: "Search Engine")
		assertTrue link.validate()

		/** Testing blank attributes */
		link = new Link(title: "", url: "", description: "")
		link.validate()
		assertEquals 'title is blank.', 'blank', link.errors['title']
		assertEquals 'url is blank.', 'blank', link.errors['url']
		assertEquals 'descripiton is null', 'blank', link.errors['description']

		/** Testing URL syntax */
		link = new Link(title: "Google", url: "google", description: "Search Engine")
		assertFalse link.validate()

        link = new Link()
        assertFalse link.validate()
        assertEquals 'title is null.', 'nullable', link.errors['title']
		assertEquals 'url is null.', 'nullable', link.errors['url']
		assertEquals 'descripiton is null', 'nullable', link.errors['description']
	}

    void testMethods() {
        mockDomain(Link)

        link = new Link(title: "Google", url: "http://google.fr", description: "Search Engine")
        assertEquals link.toString(), "Google"
    }
}
