package me.hcl.seekin.Util

class Settings {

	String applicationName
	def logo

    static constraints = {
		applicationName(nullable: true)
		logo(nullable: true)
    }

	static transients = ["logo"]
}
