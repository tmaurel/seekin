import me.hcl.seekin.Auth.Role.Student

include "formations"

fixture {

	nadirRole(Student, formation: m2glia)
	alexisRole(Student)
	thomasRole(Student)
}
