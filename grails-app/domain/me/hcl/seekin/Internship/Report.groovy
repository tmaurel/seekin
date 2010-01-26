package me.hcl.seekin.Internship

import me.hcl.seekin.Util.Document

class Report extends Document {
	
	Boolean isPrivate
	
    static constraints = {
		isPrivate()
	}
}
