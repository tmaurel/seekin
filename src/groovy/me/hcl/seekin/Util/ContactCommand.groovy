package me.hcl.seekin.Util


class ContactCommand
{

    String fullName

    String email

    String subject

    String body

    String captcha

    /** Constraints used to check if an instance is correct */
    static constraints = {
        fullName(blank: false)
        email(blank: false, email: true)
        subject(blank: false)
        body(blank: false)
        captcha(blank: false)
    }
}

