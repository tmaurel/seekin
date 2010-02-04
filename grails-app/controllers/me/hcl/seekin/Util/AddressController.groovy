package me.hcl.seekin.Util



import grails.converters.JSON
class AddressController {

    def index = { redirect(action: "list", params: params) }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def list = {
        params.max = Math.min(params.max ? params.max.toInteger() : 10,  100)
        [addressInstanceList: Address.list(params), addressInstanceTotal: Address.count()]
    }

    def create = {
        def addressInstance = new Address()
        addressInstance.properties = params
        return [addressInstance: addressInstance]
    }

    def save = {
        def addressInstance = new Address(params)
        if (!addressInstance.hasErrors() && addressInstance.save()) {
            flash.message = "address.created"
            flash.args = [addressInstance.id]
            flash.defaultMessage = "Address ${addressInstance.id} created"
            redirect(action: "show", id: addressInstance.id)
        }
        else {
            render(view: "create", model: [addressInstance: addressInstance])
        }
    }

    def show = {
        def addressInstance = Address.get(params.id)
        if (!addressInstance) {
            flash.message = "address.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Address not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [addressInstance: addressInstance]
        }
    }

    def edit = {
        def addressInstance = Address.get(params.id)
        if (!addressInstance) {
            flash.message = "address.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Address not found with id ${params.id}"
            redirect(action: "list")
        }
        else {
            return [addressInstance: addressInstance]
        }
    }

    def update = {
        def addressInstance = Address.get(params.id)
        if (addressInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (addressInstance.version > version) {
                    
                    addressInstance.errors.rejectValue("version", "address.optimistic.locking.failure", "Another user has updated this Address while you were editing")
                    render(view: "edit", model: [addressInstance: addressInstance])
                    return
                }
            }
            addressInstance.properties = params
            if (!addressInstance.hasErrors() && addressInstance.save()) {
                flash.message = "address.updated"
                flash.args = [params.id]
                flash.defaultMessage = "Address ${params.id} updated"
                redirect(action: "show", id: addressInstance.id)
            }
            else {
                render(view: "edit", model: [addressInstance: addressInstance])
            }
        }
        else {
            flash.message = "address.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Address not found with id ${params.id}"
            redirect(action: "edit", id: params.id)
        }
    }

    def delete = {
        def addressInstance = Address.get(params.id)
        if (addressInstance) {
            try {
                addressInstance.delete()
                flash.message = "address.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Address ${params.id} deleted"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "address.not.deleted"
                flash.args = [params.id]
                flash.defaultMessage = "Address ${params.id} could not be deleted"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "address.not.found"
            flash.args = [params.id]
            flash.defaultMessage = "Address not found with id ${params.id}"
            redirect(action: "list")
        }
    }

    def dataTableDataAsJSON = {
        def list = []
        //def list2 = []
        def listCorrectProperties = []
        def demoList = Address.list(params)
        response.setHeader("Cache-Control", "no-store")
        def listName = new Address().metaClass.properties*.name
        def listType = new Address().metaClass.properties*.type
        def i = 0
        listName.each {
          if(listType[i].toString() != "class java.lang.Object" && listType[i].toString() != "interface org.springframework.validation.Errors" && listType[i].toString() != "interface groovy.lang.MetaClass" && listName[i].toString() != "class" && listName[i].toString() != "version") {
            listCorrectProperties.add listName[i]
          }
          ++i
        }
        println listCorrectProperties

        /*demoList.each {
                list << [
                        id: it.id,
                        street: it.street,
                        zipCode: it.zipCode,
                        town: it.town,
                        dataUrl: g.createLink(action:'show', id:"${it.id}")
                ]
            }
        println list*/

        demoList.each {
                def hashMap = [:]
                listCorrectProperties.each {it2 ->
                  hashMap.put(it2, it."${it2}")
                }
                hashMap.put("dataUrl", g.createLink(action:'show', id:"${it.id}"))
                list.add hashMap
            }
        println list

        def data = [
                totalRecords: Address.count(),
                results: list
        ]
        println data.results
        render data as JSON
    }

    /*def dataTableDataAsJSON2 = {
          def list = []
          def list2 = []
          def demoList = ${className}.list(params)
          response.setHeader("Cache-Control", "no-store")

          demoList.each {
            def listTmp = new ${className}.metaClass.properties*.name
            def hashMap = [:]
            println listTmp
          }

          def data = [
                  totalRecords: Address.count(),
                  results: list
          ]
          println data.results
          render data as JSON
      }*/

}
