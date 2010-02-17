import me.hcl.seekin.Auth.Role.Student

include "formations"

fixture {

	nadirRole(Student, formation: m2glia, visible: true)
	alexisRole(Student, formation: m2siad, visible: true)
	thomasRole(Student, formation: m2glia, visible: false)
}
