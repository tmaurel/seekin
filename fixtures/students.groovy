import me.hcl.seekin.Profile.Student
import me.hcl.seekin.Util.Address

include "users"

fixture {
	nadirAddress(Address, street : "12 chemin floreal", zipCode : "63000", town : "Clermont-Ferrand")
	nadirProfile(Student, user : nadir, firstName : "Nadir", lastName : "Boukeffa" , address : nadirAddress , phone : "0102030405")
	
	alexisAddress(Address, street : "12 rue des Riveaux", zipCode : "63320", town : "Chidrac")
	alexisProfile(Student, user : alexis, firstName : "Alexis", lastName : "Plantin" , address : alexisAddress, phone : "0102030405")
	
	thomasAddress(Address, street : "12 chemin floreal", zipCode : "63110", town : "Beaumont")
	thomasProfile(Student, user : thomas, firstName : "Thomas", lastName : "Maurel" , address : thomasAddress, phone : "0102030405")
}
