package me.hcl.seekin.Util

class Settings {

	String applicationName
	def logo
	String emailAdmin

    static constraints = {
		applicationName(nullable: true)
		logo(nullable: true)
		emailAdmin(email: true)
    }

	static transients = ["logo"]
}
