package me.hcl.seekin.Util

import nl.captcha.Captcha
import nl.captcha.backgrounds.*
import nl.captcha.servlet.CaptchaServletUtil

class ContactController {

    def index = { }

    /**
     * Captcha generation
     */
    def generateCaptcha = {
        // Generate a captcha of 150px X 50px with gimp, noise and border
        // And add the associated text to the current user session
        def captcha = new Captcha.Builder(150, 50)
            .addText()
            .gimp()
            .addNoise()
            .addBorder()
            .build()
        session.captcha = captcha
        // Write the captcha
        CaptchaServletUtil.writeImage(response, captcha.image)
    }

}
