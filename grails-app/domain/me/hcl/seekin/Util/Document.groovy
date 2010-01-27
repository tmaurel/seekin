package me.hcl.seekin.Util

class Document {
	
	/** Title of the document */
	String title
	
	/** URI of the document */
	String uri
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		title(blank: false)
		uri(blank: false)
    }
}
