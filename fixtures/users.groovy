import me.hcl.seekin.Auth.User

include "roles"

fixture {
	nadir(User, email : "nadir.boukeffa@gmail.com" , password : authenticateService.encodePassword("nadir"), enabled : "true", authorities : [adminRole, studentRole, externalRole])
	alexis(User, email : "alexis.plantin@gmail.com" , password : authenticateService.encodePassword("alexis"), enabled : "true", authorities : [adminRole, studentRole])
	thomas(User, email : "neoseifer@gmail.com" , password : authenticateService.encodePassword("thomas"), enabled : "true", authorities : [adminRole, studentRole])
	chuck(User, email : "chuck.norris@gmail.com" , password : authenticateService.encodePassword("chuck"), enabled : "true", authorities : [staffRole])
	dusse(User, email : "dusse@gmail.com" , password : authenticateService.encodePassword("dusse"), enabled : "true", authorities : [externalRole])
}