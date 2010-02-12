package me.hcl.seekin.Auth.Role

/*
 * A student is a particular Role
 */
class Student extends Role {

    /** Visible ? **/
    Boolean visible

    static constraints = {
        visible(nullable: true)
    }
  
}
