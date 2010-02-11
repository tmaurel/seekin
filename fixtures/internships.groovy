import me.hcl.seekin.Internship.Internship

include "students"
include "staffs"
include "externals"
include "reports"
include "convocations"

fixture {
        firstInternship(Internship, subject: "First Internship", beginAt: new Date(), isApproval: true, student: nadir, academicTutor: hcl, companyTutor: jobs, report: firstInternshipReport, convocation: firstConvocation)
	secondInternship(Internship, subject: "Second Internship", beginAt: new Date(), isApproval: true, student: thomas, academicTutor: hcl, companyTutor: balmer, report: secondInternshipReport, convocation: secondConvocation)
}
