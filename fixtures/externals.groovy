import me.hcl.seekin.Profile.External

include "users"

fixture {
	jobsProfile(External, user : dusse, firstName : "Steve", lastName : "Jobs" , address : "1 street Microsoft", phone : "0102030405", formerStudent : false)
	balmerProfile(External, user : dusse, firstName : "Steve", lastName : "Balmer" , address : "1 street iPad", phone : "0102030405", formerStudent : false)
}
