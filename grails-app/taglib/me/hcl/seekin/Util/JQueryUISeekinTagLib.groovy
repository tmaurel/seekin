package me.hcl.seekin.Util

class JQueryUISeekinTagLib {
	def JQueryLinkbutton = { attrs ->
	    def value = attrs["value"]
	    def action = attrs["action"]
	    out << """<a class="button" id="${action}" href="${createLink(action:"${action}")}" class="button">${message(code: value)}</a>"""
	}

	def JQuerySubmitbutton = { attrs ->
	    def value = attrs["value"]
	    def action = attrs["action"]
	    out << actionSubmit(id: action, action:action, value:message(code: value), class:"button")
	}

	def listButton = { 
	    out << JQueryLinkbutton(value:"list", action:"list")
	}

	def deleteButton = {
		out << JQuerySubmitbutton(value:"delete", action:"delete")
	}

	def denyButton = {
	    out << JQuerySubmitbutton(value:"deny", action:"deny")
	}

	def buildEditButtons = { 
	    out << listButton()
	    out << JQuerySubmitbutton(value:"update", action:"update")
	    out << deleteButton()
	}

	def buildDenyButtons = {
	    out << listButton()
	    out << JQuerySubmitbutton(value:"update", action:"update")
	    out << deleteButton()
	    out << denyButton(value:"deny", action:"deny")
	}

	def buildShowButtons = { 
	    out << listButton()
	    out << JQuerySubmitbutton(value:"edit", action:"edit")
	    out << deleteButton()
	}

	def buildShowWithoutEditButtons = {
	    out << listButton()
	    out << deleteButton()
	}

	def buildListButtons = { 
	    out << JQueryLinkbutton(value:"new", action:"create")
	}

	def buildCreateButtons = { 
	    out << listButton()
	    out << JQuerySubmitbutton(value:"create", action:"save")
	}

	def buildSubmitButton = { attrs ->
	    def value = attrs["value"]
	    def action = attrs["action"]
	    out << JQuerySubmitbutton(value:value, action:action)
	}
}
