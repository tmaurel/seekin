package me.hcl.seekin.Ressource

import me.hcl.seekin.Formation.Formation

class EducationalDoc extends Document {

    static belongsTo = Formation
    static hasMany = [formations: Formation]
    
    static constraints = {
    }
}
