package me.hcl.seekin.Formation

import grails.test.*
import me.hcl.seekin.Ressource.Document;

/**
 * FormationTests class
 */
class FormationTests extends GrailsUnitTestCase {
	
	/** attribute used to build a Formation instance */
	def formation
	
    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }
	
	void testConstraints() {		
		
		/** Add the dynamic method validate to our Formation class */
		mockForConstraintsTests(Formation)
		
		/** Build a correct instance of Formation and test that the validation is correct */
		formation = new Formation(label: "Mohammed", description: "this is a short descrition")
		assertTrue formation.validate()

		/** Testing blank attributes */
		formation = new Formation(label: "", description: "")
		assertFalse formation.validate()	
		assertEquals 'label is blank.', 'blank', formation.errors['label']
		assertEquals 'description is blank.', 'blank', formation.errors['description']
		
	}

    void testMethods() {
        mockDomain(Formation)

        // Test toString Method
        formation = new Formation(label: "Mohammed", description: "this is a short descrition")
        assertEquals formation.toString(), 'Mohammed'

    }
}
