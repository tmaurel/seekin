package me.hcl.seekin.Util

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
            <script>
                yuiButton${action} = new YAHOO.widget.Button("${action}");
            </script>
        """
    }

    def YUILinkbutton = { attrs ->
        def value = attrs["value"]
        def action = attrs["action"]

        out << """<a id="${action}" href="${createLink(action:"${action}")}">${message(code: value)}</a>"""
        out << """
            <script>
                yuiButton${action} = new YAHOO.widget.Button("${action}");
            </script>
        """
    }

    def deleteButton = {
        out << YUISubmitbutton(value:"delete", action:"delete")
         out << """<script>
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

    def listButton = { 
        out << YUILinkbutton(value:"list", action:"list")
    }

    def buildEditButtons = { 
        out << listButton()
        out << YUISubmitbutton(value:"update", action:"update")
        out << deleteButton()
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

}
