<%! import me.hcl.seekin.Util.Settings %>
<% def settingsName = (Settings.get(1)?.applicationName)?: "seekin'" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>${settingsName} - <g:layoutTitle /></title>
        <g:layoutHead />
        <link rel="stylesheet" href="${resource(dir:'css',file:'layout.css')}" type="text/css" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <gui:resources components="accordion"/>
        <g:javascript library="application" />	
        <nav:resources/>	
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        <div id="header">
        	<div id="banner">
        		<div id="banner_cont">
        			<h1>seekin'</h1>
        			<div id="langage_selection">
	        			<g:link controller="${controllerName}" action="${actionName}" id="${params.id}" params="[lang:'fr']">
							<img src="${resource(dir:'images/icons/flags',file:'fr.png')}" alt="Français" />
						</g:link>
						<g:link controller="${controllerName}" action="${actionName}" id="${params.id}" params="[lang:'en']">
							<img src="${resource(dir:'images/icons/flags',file:'en.png')}" alt="Français" />
						</g:link>
                                        
	        		</div>
                                <g:isLoggedIn>
                                <ul id="logout_block">
                                        <li><g:link controller="user" action="profile"><g:message code="user.profile" /></g:link></li>
                                        <li><g:link controller="user" action="logout"><g:message code="user.logout" /></g:link></li>
                                </ul>
                                </g:isLoggedIn>
        		</div>
       		</div>
       		<div id="menu">
       			<div id="menu_cont">
					<nav:render group="menu"/>
					<div id="search_bar">
					  <g:form controller="search">
							<g:textField name="q" value="${params?.q}"/>
					  </g:form>
					</div>
				</div>
   			</div>
        </div>
        <div id="main">
                <g:isLoggedIn>
                    <div id="content">
                </g:isLoggedIn>
                    <g:layoutBody />
                <g:isLoggedIn>
                    </div>
                    <div class="yui-skin-sam" id="submenu">
                        <g:renderSubMenu />
                    </div>
                </g:isLoggedIn>
        </div>	
        <div id="bar_powered">
            <a href="http://seekin.hcl.me"><img src="${resource(dir:'images/icons',file:'powered.png')}" alt="Powered by seekin'" /></a>
        </div>
    </body>	
</html>