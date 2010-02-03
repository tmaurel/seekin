import me.hcl.seekin.Internship.Internship

include "students"
include "staffs"
include "externals"
include "reports"
include "convocations"

fixture {
	secondInternship(Internship, subject: "Second Internship", beginAt: new Date(), isApproval: true, student: thomasProfile, academicTutor: hclProfile, companyTutor: balmerProfile, report: secondInternshipReport, convocation: secondConvocation)
	firstInternship(Internship, subject: "First Internship", beginAt: new Date(), isApproval: true, student: nadirProfile, academicTutor: hclProfile, companyTutor: jobsProfile, report: firstInternshipReport, convocation: firstConvocation)
}
