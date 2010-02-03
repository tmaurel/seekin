import me.hcl.seekin.Profile.Staff
import me.hcl.seekin.Util.Address

include "users"

fixture {
	hclAddress(Address, street : "1 rue hcl senior", zipCode : "98052", town : "Washington")
	hclProfile(Staff, user : hcl, firstName : "Senior", lastName : "Hcl" , address : hclAddress, phone : "0102030405")
}
