import me.hcl.seekin.Internship.Internship

include "students"
include "staffs"
include "externals"
include "reports"
include "convocations"
include "companies"

fixture {
        firstInternship(Internship, subject: "First Internship", beginAt: new Date(), isApproval: true, student: nadirRole, academicTutor: hclRole, companyTutor: jobsRole, company: apple, report: firstInternshipReport, convocation: firstConvocation)
	secondInternship(Internship, subject: "Second Internship", beginAt: new Date(), isApproval: true, student: thomasRole, academicTutor: hclRole, companyTutor: balmerRole, company: microsoft, report: secondInternshipReport, convocation: secondConvocation)
}
