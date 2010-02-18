package me.hcl.seekin.Formation

class Millesime {

    /* Beginning date */
    Date beginDate

    /* End date */
    Date endDate

    static transients = ['$current','current']

    protected transient @Lazy(soft=true) volatile Boolean current = {
        def date = new Date()
        (date.after(beginDate) && date.before(endDate))
    }()

    static constraints = {
        beginDate(nullable: false)
        endDate(nullable: false)
    }
}
