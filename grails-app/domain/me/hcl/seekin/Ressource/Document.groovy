package me.hcl.seekin.Ressource

/*
 * Document which is identified by a title and have an uri
 */
class Document {

	static searchable = true

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
