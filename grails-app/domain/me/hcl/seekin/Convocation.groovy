package me.hcl.seekin

class Convocation {
	Date date
	String building
	String room
	
    static constraints = {
		date(nullable: false)
		building()
		room()
	}
}
