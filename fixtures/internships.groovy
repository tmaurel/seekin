import me.hcl.seekin.Internship.Internship

include "students"
include "staffs"
include "externals"
include "reports"
include "convocations"

fixture {
        firstInternship(Internship, subject: "First Internship", beginAt: new Date(), isApproval: true, student: nadirRole, academicTutor: hclRole, companyTutor: jobsRole, report: firstInternshipReport, convocation: firstConvocation)
	secondInternship(Internship, subject: "Second Internship", beginAt: new Date(), isApproval: true, student: thomasRole, academicTutor: hclRole, companyTutor: balmerRole, report: secondInternshipReport, convocation: secondConvocation)
}
