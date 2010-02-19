package me.hcl.seekin.Auth.Role
import me.hcl.seekin.Formation.Formation


/*
 * A Formation Manager is a particular Role
 */
class FormationManager extends Role {

    static belongsTo = [formation: Formation]

    static constraints = {
    }

}
