package me.hcl.seekin.profile

class ProfileTagLib {

    def isStudent = { attrs, body ->
        def profile = attrs['profile']
        if(profile != null && profile.class.name =~ "Student") {
           out << body()
        }
    }

    def isExternal = { attrs, body ->
        def profile = attrs['profile']
        if(profile != null && profile.class.name =~ "External") {
           out << body()
        }
    }

    def isStaff = { attrs, body ->
        def profile = attrs['profile']
        if(profile != null && profile.class.name =~ "Staff") {
           out << body()
        }
    }

}
