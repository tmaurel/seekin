package me.hcl.seekin.Internship

import me.hcl.seekin.Ressource.Document
import me.hcl.seekin.Formation.Promotion
import me.hcl.seekin.Auth.User
import me.hcl.seekin.Company

class Offer {

    static hasMany = [promotions: Promotion]
    static belongsTo = [Promotion, Company]
    static transients = ['status']

    /** Subject of the Offer */
    String subject

    /** Short description of the offer */
    String description

    /** Date of the internship's beginning */
    Date beginAt

    /** Length of the internship */
    Integer length

    /** Show if the offer is validated*/
    Boolean validated

    /** Show if the offer is assignated (converted into an internship) */
    Boolean assignated

    /** Explains the reason of the deny */
    String reason

    /** File which details the offer */
    Document file

    /** User who have created the offer */
    User author

    Company company
	
	/** Constraints used to check if an instance is correct */
    static constraints = {
		subject(blank: false)
		description(blank: false)
		beginAt(nullable: false)
        validated(nullable: false)
        assignated(nullable: false)
		length(range: 1..52)
		reason(nullable:true, blank: true)
        file(nullable: true)
        company(nullable: false)
        promotions(nullable: false)
    }

	String toString() {
		subject
	}

    String getStatus() {
        validated==false?(reason==null?'offer.waitForValidation':'offer.unvalidated'):(assignated==false?'offer.validated':'offer.assignated')
    }

	/** Return offers waiting for validation for a promotion */
    static getOffersWaitingForValidationForPromotion = {
        promotion ->
			Offer.list().findAll {
				it.getStatus() == "offer.waitForValidation"  && it.promotions?.contains(promotion)
			}
    }
}
