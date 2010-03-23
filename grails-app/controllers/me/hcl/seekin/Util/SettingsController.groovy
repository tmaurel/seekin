package me.hcl.seekin.Util

import me.hcl.seekin.Auth.User
import me.hcl.seekin.Auth.Role.Admin
import me.hcl.seekin.Auth.Role.Staff
import org.codehaus.groovy.grails.plugins.springsecurity.Secured

class SettingsController {

	def authenticateService

    def index = { redirect(action: "edit") }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [update: "POST"]

    // First Installation
    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
    def install = {
        def settingsInstance = Settings.get(1)
        if(settingsInstance != null) {
            redirect(controller: "user", action: "auth")
        }
        else {
			def adminInstance

			if(request.method == 'POST') {
				adminInstance = new User(
					email: params?.emailAdmin,
					password: authenticateService.encodePassword(params.passwordAdmin),
					firstName: params.firstNameAdmin,
					lastName: params.lastNameAdmin,
					enabled: true,
					address: new Address(street: params.streetAdmin, town: params.townAdmin, zipCode: params.zipCodeAdmin)
				)

				settingsInstance = new Settings(applicationName: params.applicationName)

				if(!params.passwordAdmin && !params.repasswdAdmin) {
					flash.message = message(code:"user.password.nullable.error")
				}
				else {
					if (params.passwordAdmin != params.repasswordAdmin) {
						flash.message = message(code:"user.password.dismatch")
					}
					else {
						adminInstance.addToAuthorities(new Admin())
						adminInstance.addToAuthorities(new Staff())
						if(!adminInstance.hasErrors() && adminInstance.save() && !settingsInstance.hasErrors() && settingsInstance.save()) {
							redirect(controller: "user", action: "auth")
						}
					}
				}
				return [settingsInstance: settingsInstance, adminInstance: adminInstance]
			}
		}
    }

    @Secured(['ROLE_ADMIN'])
    def edit = {
        def settingsInstance = Settings.get(1)
        if (!settingsInstance) {
            flash.message = "settings.not.found"
            flash.args = [1]
            redirect(action: "install")
        }
        else {
            return [settingsInstance: settingsInstance]
        }
    }

    @Secured(['ROLE_ADMIN'])
    def update = {
        def settingsInstance = Settings.get(1)
        if (settingsInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (settingsInstance.version > version) {
                    
                    settingsInstance.errors.rejectValue("version", "settings.optimistic.locking.failure", "Another user has updated this Settings while you were editing")
                    render(view: "edit", model: [settingsInstance: settingsInstance])
                    return
                }
            }
            settingsInstance.properties = params

			def logoFile = request.getFile('logo')
			if(!logoFile?.empty)
			{
				if(logoFile.contentType != "image/png")
				{
					flash.message = "settings.errors.logo.type"
					render(view: "edit", model: [settingsInstance: settingsInstance])
					return
				}
				if(logoFile.size > 1024*200)
				{
					flash.message = "settings.errors.logo.size"
					render(view: "edit", model: [settingsInstance: settingsInstance])
					return
				}
				logoFile.transferTo(new File("web-app/images/skin/logo.png"))
			}
			
            if (!settingsInstance.hasErrors() && settingsInstance.save()) {
                flash.message = "settings.updated"
                flash.args = [1]
                redirect(action: "edit")
            }
            else {
                render(view: "edit", model: [settingsInstance: settingsInstance])
            }
        }
        else {
            flash.message = "settings.not.found"
            flash.args = [1]
            flash.defaultMessage = "Settings not found"
            redirect(action: "edit")
        }
    }
}
