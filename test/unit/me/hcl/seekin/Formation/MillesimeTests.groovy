package me.hcl.seekin.Formation

import grails.test.*
import static java.util.Calendar.YEAR

class MillesimeTests extends GrailsUnitTestCase {

    def millesime

    protected void setUp() {
        super.setUp()
    }

    protected void tearDown() {
        super.tearDown()
    }

    void testConstraints() {
        mockForConstraintsTests(Millesime)

        def olderDate = new Date() - 365
        def newerDate = new Date() + 365
        millesime = new Millesime(beginDate: olderDate, endDate: newerDate)
        
        assertTrue millesime.validate()
        assertEquals millesime.current, true

        millesime = new Millesime()
        assertFalse millesime.validate()
        assertEquals 'beginDate is null.', 'nullable', millesime.errors['beginDate']
        assertEquals 'endDate is null.', 'nullable', millesime.errors['endDate']

    }

    void testMethods() {
        mockDomain(Millesime)

        def olderDate = new Date() - 365
        def newerDate = new Date() + 365
        millesime = new Millesime(beginDate: olderDate, endDate: newerDate)

        // Test toString Method
        def firstYear = 1900 + olderDate.getYear()
        def lastYear = 1900 + newerDate.getYear()
        assertEquals millesime.toString(), firstYear  + " - " + lastYear

        // Test getCurrent Method
        def passedMillesime = new Millesime(beginDate: olderDate - 365, endDate: olderDate)
        def futureMillesime = new Millesime(beginDate: newerDate, endDate: newerDate + 365)
        mockDomain(Millesime, [millesime, passedMillesime, futureMillesime])
        assertEquals Millesime.getCurrent(), millesime
    }
}
