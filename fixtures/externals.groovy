import me.hcl.seekin.Profile.External
import me.hcl.seekin.Util.Address

include "users"

fixture {
	balmerAddress(Address, street : "1 Microsoft Way", zipCode : "98052", town : "Washington")
	balmerProfile(External, user : balmer, firstName : "Steve", lastName : "Balmer" , address : balmerAddress, phone : "0102030405", formerStudent : false)
	
	jobsAddress(Address, street : "1 Infinite Loop", zipCode : "95014", town : "Cupertino")
	jobsProfile(External, user : jobs, firstName : "Steve", lastName : "Jobs" , address : jobsAddress, phone : "0102030405", formerStudent : true)
}
