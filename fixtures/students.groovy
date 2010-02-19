import me.hcl.seekin.Auth.Role.Student

include "formations"

fixture {

	nadirRole(Student, promotions: [siad2009], visible: true)
	alexisRole(Student, promotions: [glia2009], visible: true)
	thomasRole(Student, promotions: [glia2009, glia2008], visible: false)
}
