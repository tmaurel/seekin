import me.hcl.seekin.Auth.User

include "roles"

fixture {
	nadir(User, username : "nadir", userRealName : "Nadir Boukeffa", passwd : authenticateService.encodePassword("nadir"), enabled : "true", email : "nadir.boukeffa@gmail.com" , authorities : [adminRole, studentRole, externalRole])
	alexis(User, username : "alexis", userRealName : "Alexis Plantin", passwd : authenticateService.encodePassword("alexis"), enabled : "true", email : "alexis.plantin@gmail.com" , authorities : [adminRole, studentRole])
	thomas(User, username : "thomas", userRealName : "Thomas Morel", passwd : authenticateService.encodePassword("thomas"), enabled : "true", email : "neoseifer@gmail.com" , authorities : [adminRole, studentRole])
}