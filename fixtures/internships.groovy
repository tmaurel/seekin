import me.hcl.seekin.Internship.Internship

include "students"
include "staffs"
include "externals"
include "reports"
include "convocations"
include "companies"
include "millesimes"

fixture {
    firstInternship(Internship, subject: "First Internship", beginAt: new Date(), isApproval: true, student: nadirRole, academicTutor: hclRole, companyTutor: jobsRole, company: apple, report: firstInternshipReport, convocation: firstConvocation, length:6, millesime:m2009)
	secondInternship(Internship, subject: "Second Internship", beginAt: new Date(), isApproval: true, student: thomasRole, academicTutor: hclRole, companyTutor: balmerRole, company: microsoft, report: secondInternshipReport, convocation: secondConvocation, length:6, millesime:m2008)
}
