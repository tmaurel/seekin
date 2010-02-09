package me.hcl.seekin.Auth

import me.hcl.seekin.Auth.Role
import grails.converters.JSON


/**
 * Authority Controller.
 */
class RoleController {

	// the delete, save and update actions only accept POST requests
	static Map allowedMethods = [delete: 'POST', save: 'POST', update: 'POST']

	def authenticateService

	def index = {
		redirect action: list, params: params
	}

	/**
	 * Display the list roleInstance page.
	 */
	def list = {
		if (!params.max) {
			params.max = 10
		}
		[roleInstanceList: Role.list(params)]
	}

	/**
	 * Display the show roleInstance page.
	 */
	def show = {
		def roleInstance = Role.get(params.id)
		if (!roleInstance) {
			flash.message = "Role not found with id $params.id"
			redirect action: list
			return
		}

		[roleInstance: roleInstance]
	}

	/**
	 * Delete an roleInstance.
	 */
	def delete = {
		def roleInstance = Role.get(params.id)
		if (!roleInstance) {
			flash.message = "Role not found with id $params.id"
			redirect action: list
			return
		}

		authenticateService.deleteRole(roleInstance)

		flash.message = "Role $params.id deleted."
		redirect action: list
	}

	/**
	 * Display the edit roleInstance page.
	 */
	def edit = {
		def roleInstance = Role.get(params.id)
		if (!roleInstance) {
			flash.message = "Role not found with id $params.id"
			redirect action: list
			return
		}

		[roleInstance: roleInstance]
	}

	/**
	 * Authority update action.
	 */
	def update = {

		def roleInstance = Role.get(params.id)
		if (!roleInstance) {
			flash.message = "Role not found with id $params.id"
			redirect action: edit, id: params.id
			return
		}

		long version = params.version.toLong()
		if (roleInstance.version > version) {
			roleInstance.errors.rejectValue 'version', 'roleInstance.optimistic.locking.failure',
				'Another user has updated this Role while you were editing.'
			render view: 'edit', model: [roleInstance: roleInstance]
			return
		}

		if (authenticateService.updateRole(roleInstance, params)) {
			authenticateService.clearCachedRequestmaps()
			redirect action: show, id: roleInstance.id
		}
		else {
			render view: 'edit', model: [roleInstance: roleInstance]
		}
	}

	/**
	 * Display the create new roleInstance page.
	 */
	def create = {
		[roleInstance: new Role()]
	}

	/**
	 * Save a new roleInstance.
	 */
	def save = {

		def roleInstance = new Role()
		roleInstance.properties = params
		if (roleInstance.save()) {
			redirect action: show, id: roleInstance.id
		}
		else {
			render view: 'create', model: [roleInstance: roleInstance]
		}
	}
	
	def dataTableDataAsJSON = {
        def list = Role.list(params)
        def ret = []
        response.setHeader("Cache-Control", "no-store")

        list.each {
        	ret << [
    			id:it.id,
        		description:it.description,
		   		authority:it.authority,
				urlID:it.id,
            ]
        }

        def data = [
                totalRecords: Role.count(),
                results: ret
        ]

        render data as JSON
    }
}
