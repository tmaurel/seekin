package me.hcl.seekin.Internship

import me.hcl.seekin.Ressource.Document

class InternshipSubjectFile extends Document {

    /* A subject file belongs to an Internship or an offer */
    static belongsTo = [Internship, Offer]

    static constraints = {
    }
}
