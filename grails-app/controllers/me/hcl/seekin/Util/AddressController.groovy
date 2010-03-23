package me.hcl.seekin.Util

import grails.converters.JSON

class AddressController {

    def show = {
        def addressInstance = Address.get(params.id)
        if (!addressInstance) {
            flash.message = "address.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Address not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            redirect(url:"http://maps.google.fr/maps?q="+addressInstance.toString())
        }
    }

}
