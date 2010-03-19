import me.hcl.seekin.Internship.Internship

include "students"
include "staffs"
include "externals"
include "reports"
include "convocations"
include "companies"
include "millesimes"

fixture {
    firstInternship(Internship, subject: "First Internship", beginAt: new Date(), isApproval: false, student: nadirRole, companyTutor: jobsRole, company: apple, report: null, convocation: firstConvocation, length:6, millesime:m2009)
	secondInternship(Internship, subject: "Second Internship", beginAt: new Date(), isApproval: false, reason: "it's not a good internship", student: alexisRole, companyTutor: balmerRole, company: microsoft, report: null, convocation: secondConvocation, length:6, millesime:m2008)
}
