import me.hcl.seekin.Profile.Student

include "users"

fixture {
	nadirProfile(Student, user : nadir, firstName : "Nadir", lastName : "Boukeffa" , address : "12 chemin Floreal", phone : "0102030405")
	alexisProfile(Student, user : alexis, firstName : "Alexis", lastName : "Plantin" , address : "12 rue des Riveaux", phone : "0102030405")
	thomasProfile(Student, user : thomas, firstName : "Thomas", lastName : "Maurel" , address : "19 rue René Brut", phone : "0102030405")
}
