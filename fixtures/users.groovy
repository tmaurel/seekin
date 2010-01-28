import me.hcl.seekin.Auth.User

include "roles"

fixture {
	nadir(User, username : "nadir", userRealName : "Nadir Boukeffa", passwd : "nadir", email : "nadir.boukeffa@gmail.com" , authorities : [adminRole, studentRole, externalRole])
	alexis(User, username : "alexis", userRealName : "Alexis Plantin", passwd : "alexis", email : "alexis.plantin@gmail.com" , authorities : [adminRole, studentRole])
	thomas(User, username : "thomas", userRealName : "Thomas Morel", passwd : "thomas", email : "neoseifer@gmail.com" , authorities : [adminRole, studentRole])
}