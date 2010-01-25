<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>seekin' - <g:layoutTitle /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'layout.css')}" type="text/css" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:javascript library="application" />	
        <g:layoutHead />
        <nav:resources/>	
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        <debugToolbar:renderMe/>
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
        		</div>
       		</div>
       		<div id="menu">
       			<div id="menu_cont">
					<nav:render group="menu"/>
					<div id="search_bar">
						<form>
							<g:textField name="search_field" value="${message(code:'search.field.default')}"/>
						</form>
					</div>
				</div>
   			</div>
        </div>
        <div id="main">
			<g:layoutBody />		
        </div>	
        <div id="bar">
        	<div id="bar_cont">
        		<nav:render group="bar_left" id="bar_left"/>
        		<nav:render group="bar_right" id="bar_right"/>
        	</div>
        </div>
    </body>	
</html>