package me.hcl.seekin.Formation

class Promotion {

    static belongsTo = [formation: Formation, millesime: Millesime]

    static constraints = {
    }
}
