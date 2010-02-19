package me.hcl.seekin.Formation

import me.hcl.seekin.Auth.Role.Student

class Promotion {

	static hasMany = [students: Student]
    static belongsTo = [formation: Formation, millesime: Millesime]

    static constraints = {
    }
}
