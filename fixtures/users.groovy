import me.hcl.seekin.Auth.User

include "roles"

fixture {
	nadir(User, email : "nadir.boukeffa@gmail.com" , password : authenticateService.encodePassword("nadir"), enabled : "true", authorities : [adminRole, studentRole, externalRole])
	alexis(User, email : "alexis.plantin@gmail.com" , password : authenticateService.encodePassword("alexis"), enabled : "true", authorities : [adminRole, studentRole])
	thomas(User, email : "neoseifer@gmail.com" , password : authenticateService.encodePassword("thomas"), enabled : "true", authorities : [adminRole, studentRole])
	balmer(User, email : "steve.balmer@gmail.com" , password : authenticateService.encodePassword("steve"), enabled : "true", authorities : [externalRole])
	jobs(User, email : "steve.jobs@gmail.com" , password : authenticateService.encodePassword("steve"), enabled : "true", authorities : [externalRole])
	hcl(User, email : "hcl@hcl.me" , password : authenticateService.encodePassword("hcl"), enabled : "true", authorities : [staffRole])
}