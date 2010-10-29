package me.hcl.seekin.Ressource

import grails.test.*

class FileDataTests extends GrailsUnitTestCase {

    def fileData

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    /*data(nullable: false, minSize: 1, maxSize: TEN_MEG_IN_BYTES )
        size(nullable: false)
        dateCreated(nullable: true)
        contentType(nullable: false, validator: { val, obj ->
            return val == "application/pdf" || val == "application/msword"
        })*/
    void testConstraints() {
        mockForConstraintsTests(FileData)

        fileData = new FileData(fileName: 'file name', dateCreated: new Date(), size: 1000, data: 'blabla', contentType: 'application/pdf', extension: 'pdf', document: new Document())
        assertTrue fileData.validate()


        fileData = new FileData()
        assertFalse fileData.validate()
        assertEquals 'fileName is null.', 'nullable', fileData.errors['fileName']
        assertEquals 'data is null.', 'nullable', fileData.errors['data']
        assertEquals 'contentType is null.', 'nullable', fileData.errors['contentType']
        assertEquals 'extension is null.', 'nullable', fileData.errors['extension']
        assertEquals 'document is null.', 'nullable', fileData.errors['document']

        fileData.data = new byte[1024 * 1024 * 10 + 1]
        assertFalse fileData.validate()
        assertEquals 'data is too big.', 'maxSize', fileData.errors['data']

        fileData.contentType = 'application/bad'
        assertFalse fileData.validate()
        assertEquals 'content type is not right.', 'validator', fileData.errors['contentType']
    }
}
