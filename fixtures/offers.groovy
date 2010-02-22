import me.hcl.seekin.Internship.Offer

include "users"
include "companies"
include "formations"

fixture {

    firstOffer(Offer, subject: "First Offer", description: "This is a description of the first offer", beginAt: new Date(), length: 6, validated: false, assignated: false, author:hcl, company: apple, promotions: [glia2009, glia2008])
    secondOffer(Offer, subject: "Second Offer", description: "This is a description of the second offer", beginAt: new Date(), length: 6, validated:false, assignated: false, reason: "bad offer", author:jobs, company: apple, promotions: [glia2008])
    thirdOffer(Offer, subject: "Third Offer", description: "This is a description of the third offer", beginAt: new Date(), length: 6, validated: true, assignated: true, author:balmer, company: microsoft, promotions: [glia2009, siad2009])
    fourthOffer(Offer, subject: "Fourth Offer", description: "This is a description of the fourth offer", beginAt: new Date(), length: 6, validated: true, assignated: false, author:jobs, company: apple, promotions: [siad2009])
    fifthOffer(Offer, subject: "Fifth Offer", description: "This is a description of the fifth offer", beginAt: new Date(), length: 6, validated: false, assignated: false, author:jobs, company: apple, promotions: [siad2008])
    sixthOffer(Offer, subject: "Sixth Offer", description: "This is a description of the sixth offer", beginAt: new Date(), length: 6, validated: false, assignated: false, author:jobs, company: apple, promotions: [glia2009, glia2008])

}