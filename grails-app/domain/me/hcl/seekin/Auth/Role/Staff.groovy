package me.hcl.seekin.Auth.Role

/*
 * A staff member is a particular Role
 */
class Staff extends Role {

    /** ROLE String */
    String authority = "ROLE_STAFF"

    static constraints = {
    }
  
}
