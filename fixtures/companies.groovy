import me.hcl.seekin.Company
import me.hcl.seekin.Util.Address

fixture {
	microsoftAddress(Address, street : "1 Microsoft Way", zipCode : "98052", town : "Washington")
	microsoft(Company, name : "Microsoft", address : microsoftAddress, phone : "0102030405")
	
	appleAddress(Address, street : "1 Infinite Loop", zipCode : "95014", town : "Cupertino")
	apple(Company, name : "Apple", address : appleAddress, phone : "0102030405")
}
