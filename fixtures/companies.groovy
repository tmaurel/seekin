import me.hcl.seekin.Company

include "externals"

fixture {
	microsoft(Company, name : "Microsoft", address : "1 street Microsoft" , phone : "0102030405", employees : [jobsProfile])
	apple(Company, name : "Apple", address : "1 street Apple" , phone : "0102030405", employees : [balmerProfile])	
}
