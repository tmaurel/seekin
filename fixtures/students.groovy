import me.hcl.seekin.Auth.Role.Student

include "formations"

fixture {

	nadirRole(Student, promotion: siad2009, visible: true)
	alexisRole(Student, promotion: glia2009, visible: true)
	thomasRole(Student, promotion: glia2009, visible: false)
}
