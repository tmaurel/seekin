package me.hcl.seekin.Ressource

/*
 * Document which is identified by a title and have an uri
 */
class Document {

    /** Title of the document */
    String title

    /** URI of the document */
    FileData fileData
	
    /** Constraints used to check if an instance is correct */
    static constraints = {
        title(nullable: false, blank: false)
        fileData(nullable: false)
    }

    String toString() {
        title
    }
}
