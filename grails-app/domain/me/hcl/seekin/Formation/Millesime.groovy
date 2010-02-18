package me.hcl.seekin.Formation

class Millesime {

    /* Beginning date */
    Date begin

    /* End date */
    Date blabla

    static transients = ['$current','current']

    protected transient @Lazy(soft=true) volatile Boolean current = {
        def date = new Date()
        (date.after(begin) && date.before(blabla))
    }()

    static constraints = {
        begin(nullable: false)
        blabla(nullable: false)
    }
}
