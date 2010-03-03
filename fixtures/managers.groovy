import me.hcl.seekin.Auth.Role.FormationManager

include "formations"

fixture {
	managerRole(FormationManager, formation: m2glia)
}
