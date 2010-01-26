package me.hcl.seekin.Util

class Document {
	
	String title
	String uri
	
    static constraints = {
		title(blank: false)
		uri(blank: false)
    }
}
