import me.hcl.seekin.Auth.Role

fixture {
	defaultRole(Role, description : "Default Role", authority : "ROLE_USER")
	adminRole(Role, description : "Admin Role", authority : "ROLE_ADMIN")
	studentRole(Role, description : "Student Role", authority : "ROLE_STUDENT")
	staffRole(Role, description : "Staff Role", authority : "ROLE_STAFF")
	externalRole(Role, description : "External Role", authority : "ROLE_EXTERNAL")
}