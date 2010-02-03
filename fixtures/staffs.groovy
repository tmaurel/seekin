import me.hcl.seekin.Profile.Staff

include "users"

fixture {
	chuckProfile(Staff, user : chuck, firstName : "Chuck", lastName : "Norris" , address : "1 rue nobody knows", phone : "0102030405")
}
