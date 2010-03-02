package me.hcl.seekin.Util

import org.codehaus.groovy.grails.plugins.springsecurity.AuthorizeTools

class YUITagLib {

    def YUIButtonRessource = {
        out << yui.javascript(dir:'yahoo-dom-event', file:'yahoo-dom-event.js')
        out << yui.javascript(dir:'element', file:'element-min.js')
        out << yui.javascript(dir:'button', file:'button-min.js')
        out << yui.stylesheet(dir:'button/assets/skins/sam', file:'button.css')
    }

    def YUISubmitbutton = { attrs ->
        def value = attrs["value"]
        def action = attrs["action"]

        out << actionSubmit(id: action, action:action, value:message(code: value))
        out << """
            <script type="text/javascript">
                yuiButton${action} = new YAHOO.widget.Button("${action}");
            </script>
        """
    }

    def YUILinkbutton = { attrs ->
        def value = attrs["value"]
        def action = attrs["action"]

        out << """<a id="${action}" href="${createLink(action:"${action}")}">${message(code: value)}</a>"""
        out << """
            <script type="text/javascript">
                yuiButton${action} = new YAHOO.widget.Button("${action}");
            </script>
        """
    }

    def deleteButton = {
        out << YUISubmitbutton(value:"delete", action:"delete")
         out << """<script type="text/javascript">
                        function submit(p_oEvent) {
                            if(yuiButtondelete.hasFocus())
                            {
                                   var bSubmit = window.confirm("${message(code: 'delete.confirm')}");
                                   if(!bSubmit) {
                                        YAHOO.util.Event.preventDefault(p_oEvent);
                                   }
                            }
                        }
                        YAHOO.util.Event.addListener("crud_panel", "submit", submit);
                    </script>\n"""
    }

    def denyButton = {
        out << YUISubmitbutton(value:"deny", action:"deny")
         out << """<script type="text/javascript">
                        function submit(p_oEvent) {
                            if(yuiButtondeny.hasFocus())
                            {
                                   var bSubmit = window.prompt("${message(code: 'deny.confirm')}");
                                   var field = YAHOO.util.Dom.get('reason');

                                   field.value = bSubmit;

                                   if(!bSubmit) {
                                        YAHOO.util.Event.preventDefault(p_oEvent);
                                   }
                            }
                        }
                        YAHOO.util.Event.addListener("crud_panel", "submit", submit);
                    </script>\n"""
    }

    def listButton = { 
        out << YUILinkbutton(value:"list", action:"list")
    }

    def buildEditButtons = { 
        out << listButton()
        out << YUISubmitbutton(value:"update", action:"update")
        out << deleteButton()
    }

    def buildDenyButtons = {
        out << listButton()
        out << YUISubmitbutton(value:"update", action:"update")
        out << deleteButton()
        out << denyButton(value:"deny", action:"deny")
    }

    def buildShowButtons = { 
        out << listButton()
        out << YUISubmitbutton(value:"edit", action:"edit")
        out << deleteButton()
    }

    def buildListButtons = { 
        out << YUILinkbutton(value:"new", action:"create")
    }

    def buildCreateButtons = { 
        out << listButton()
        out << YUISubmitbutton(value:"create", action:"save")
    }

    def buildSubmitButton = { attrs ->
        def value = attrs["value"]
        def action = attrs["action"]
        out << YUISubmitbutton(value:value, action:action)
    }

    def renderSubMenu = {
        out << gui.accordion(multiple:"true", bounce:"true", fade:"true") {

            if(AuthorizeTools.ifAllGranted("ROLE_ADMIN")) {
                out << gui.accordionElement(title:message(code:"admin"), selected:"true") {

                    """<ul>
                        <li><a href="${createLink(controller:"settings", action:"index")}">${message(code:"settings")}</a></li>
                        <li><a href="${createLink(controller:"user", action:"list")}">${message(code:"user.list")}</a></li>
						<li><a href="${createLink(controller:"formation", action:"list")}">${message(code:"formation.list")}</a></li>
						<li><a href="${createLink(controller:"promotion", action:"list")}">${message(code:"promotion.list")}</a></li>
						<li><a href="${createLink(controller:"company", action:"list")}">${message(code:"company.list")}</a></li>
						<li><a href="${createLink(controller:"offer", action:"list")}">${message(code:"offer.list")}</a></li>
						<li><a href="${createLink(controller:"internship", action:"list")}">${message(code:"internship.list")}</a></li>
						<li><a href="${createLink(controller:"report", action:"list")}">${message(code:"report.list")}</a></li>
						<li><a href="${createLink(controller:"document", action:"list")}">${message(code:"document.list")}</a></li>
						<li><a href="${createLink(controller:"link", action:"list")}">${message(code:"link.list")}</a></li>
                    </ul>"""
                }
            }
			
            if(AuthorizeTools.ifAllGranted("ROLE_STUDENT")) {
                out << gui.accordionElement(title:message(code:"student"), selected:"true") {
                    
                    """<ul>
                        <li><a href="${createLink(controller:"offer", action:"list")}">${message(code:"offer.list")}</a></li>
                        <li><a href="${createLink(controller:"internship", action:"list")}">${message(code:"internship.list")}</a></li>
                        <li><a href="${createLink(controller:"internship", action:"create")}">${message(code:"internship.create")}</a></li>
                        <li><a href="${createLink(controller:"report", action:"create")}">${message(code:"report.create")}</a></li>
                        <li><a href="${createLink(controller:"student", action:"list")}">${message(code:"student.list")}</a></li>
						<li><a href="${createLink(controller:"company", action:"list")}">${message(code:"company.list")}</a></li>
                        <li><a href="${createLink(controller:"report", action:"list")}">${message(code:"report.list")}</a></li>
                    </ul>"""
                }
            }
            
            if(AuthorizeTools.ifAllGranted("ROLE_STAFF")) {
                out << gui.accordionElement(title:message(code:"staff"), selected:"true") {

                    """<ul>
                        <li><a href="${createLink(controller:"offer", action:"list")}">${message(code:"offer.list")}</a></li>
                        <li><a href="${createLink(controller:"offer", action:"create")}">${message(code:"offer.create")}</a></li>
                        <li><a href="${createLink(controller:"student", action:"list")}">${message(code:"student.list")}</a></li>
                        <li><a href="${createLink(controller:"internship", action:"list")}">${message(code:"internship.list")}</a></li>
                        <li><a href="${createLink(controller:"company", action:"list")}">${message(code:"company.list")}</a></li>
                        <li><a href="${createLink(controller:"report", action:"list")}">${message(code:"report.list")}</a></li>
                        <li><a href="${createLink(controller:"convocation", action:"list")}">${message(code:"convocation.list")}</a></li>
                    </ul>"""
                }
            }

            if(AuthorizeTools.ifAllGranted("ROLE_EXTERNAL")) {
                out << gui.accordionElement(title:message(code:"external"), selected:"true") {

                    """<ul>
                        <li><a href="${createLink(controller:"offer", action:"list")}">${message(code:"offer.list")}</a></li>
                        <li><a href="${createLink(controller:"offer", action:"create")}">${message(code:"offer.create")}</a></li>
                        <li><a href="${createLink(controller:"student", action:"list")}">${message(code:"student.list")}</a></li>
                        <li><a href="${createLink(controller:"company", action:"edit")}">${message(code:"company.edit")}</a></li>
                    </ul>"""
                }
            }

        }
    }

}
