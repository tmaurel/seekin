import me.hcl.seekin.Auth.Role.External

include "companies"

fixture {
	
	balmerRole(External, formerStudent : false, company : microsoft)
	jobsRole(External, formerStudent : true, company : apple)
}
