package me.hcl.seekin.Internship

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Auth.Role.Student
import me.hcl.seekin.Auth.Role.Staff
import me.hcl.seekin.Auth.Role.External
import me.hcl.seekin.Internship.Company
import me.hcl.seekin.Formation.Millesime
import org.apache.commons.lang.StringUtils
import me.hcl.seekin.Util.Address

class Internship {

    static searchable = {
            student(component:true)
            company(component:true)
    }

    static transients = ['status']

    /** Subject of the Internship */
    String subject

    /** Description of the Internship */
    String description

    /** Beginning of the Internship */
    Date beginAt

    /** Indicate if the Internship is approved by Staff member */
    Boolean isApproval = false

    /** Indicates if the internship is from an offer which was posted on seekin */
    Boolean fromOffer = false

    /** Student who is concerned by the internship */
    Student student

    /** Tutor who is member of the university */
    Staff academicTutor

    /** Tutor who is member of the company which posts the internship */
    External companyTutor

    /** Company where the student will do this internship */
    Company company

    /** Report of the Internship */
    Report report

    /** Convocation for the oral test of the internship */
    Convocation convocation

    /** Length of the internship */
    Integer length

    /** Millesime of the internship */
    Millesime millesime

    /** Reason of the deny */
    String reason

    /** Where the internship will take place **/
    Address address

    /** Phone number to contact the company **/
    String phone
    
    /** Constraints used to check if an instance is correct */
    static constraints = {
        subject(blank: false)
        beginAt(nullable: false)
        isApproval()
        fromOffer(nullable: true)
        report(nullable: true)
        student(nullable: false)
        academicTutor(nullable: true)
        companyTutor(nullable: true)
        convocation(nullable: true)
        length(nullable: false)
        millesime(nullable: false)
        reason(nullable:true)
        description(nullable:true, maxSize:1000)
        address(nullable:true)
        phone(nullable:true)
    }

    String toString() {
            subject
    }

    String getStatus() {
        isApproval==false?(reason==null?'internship.waitForValidation':'internship.unvalidated'):'internship.validated'
    }

	/** Return student's internship for current millesime */
    static getCurrentForStudent = {
        student ->
            student.internships.find {
                it.millesime.current == true
            }
    }

}
