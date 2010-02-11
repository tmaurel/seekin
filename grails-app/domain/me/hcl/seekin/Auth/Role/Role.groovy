package me.hcl.seekin.Auth.Role

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Util.Address

/*
 * Role class
 */
class Role {
    
    static belongsTo = [user: User]

    static transients = ['$authority','authority']

    protected transient @Lazy(soft=true) volatile String authority = {
        "ROLE_" + getRoleName().toUpperCase()
    }()
	
    /** Constraints used to check if an instance is correct */
    static constraints = {
        user(nullable: true)
    }

    def getRoleName()
    {
        this.class.name.substring(this.class.name.lastIndexOf(".") + 1)
    }

}