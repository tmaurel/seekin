import me.hcl.seekin.Internship.Offer

fixture {

    firstOffer(Offer, subject: "First Offer", description: "This is a description of the first offer", beginAt: new Date(), length: 6, status: "unvalidate")
    secondOffer(Offer, subject: "Second Offer", description: "This is a description of the second offer", beginAt: new Date(), length: 6, status: "validate")

}