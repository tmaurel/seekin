import me.hcl.seekin.Internship.Offer

include "users"
include "companies"

fixture {

    firstOffer(Offer, subject: "First Offer", description: "This is a description of the first offer", beginAt: new Date(), length: 6, status: "unvalidate", author:hcl, company: apple)
    secondOffer(Offer, subject: "Second Offer", description: "This is a description of the second offer", beginAt: new Date(), length: 6, status: "validate", author:jobs, company: apple)

}