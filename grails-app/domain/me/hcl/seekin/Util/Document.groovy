package me.hcl.seekin.Util

/*
 * Document which is identified by a title and have an uri
 */
class Document {
	
	/** Title of the document */
	String title
	
	/** URI of the document */
	String uri
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		title(nullable: false, blank: false)
		uri(nullable: false, blank: false)
    }
}
