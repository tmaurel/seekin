package me.hcl.seekin.Util

class SettingsController {

    def index = { redirect(action: "edit") }

    // the delete, save and update actions only accept POST requests
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def install = {
        def settingsInstance = Settings.get(1)
        if(settingsInstance != null) {
            redirect(controller: "user", action: "auth")
        }
        else {
          settingsInstance = new Settings()
          settingsInstance.properties = params
          return [settingsInstance: settingsInstance]
        }
    }

    def save = {
        def settingsInstance = new Settings(params)
        if (!settingsInstance.hasErrors() && settingsInstance.save()) {
            flash.message = "settings.created"
            flash.args = [1]
            flash.defaultMessage = "Settings created"
            redirect(action: "edit")
        }
        else {
            render(view: "edit", model: [settingsInstance: settingsInstance])
        }
    }

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
