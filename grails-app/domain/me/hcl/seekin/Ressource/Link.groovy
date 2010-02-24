package me.hcl.seekin.Ressource

class Link {

	static searchable = true

	/** Link title */
	String title

	/** Link url */
	String url

	/** Link description */	
	String description
    
	static constraints = {
		title(blank: false)
		url(blank: false, url: true)
		description(blank: false)
	}

	String toString() {
		title
	}
}
