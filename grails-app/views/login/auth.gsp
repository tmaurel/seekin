<head>
	<meta name='layout' content='main' />
	<title><g:message code="login.page.title" /></title>
	<gui:resources components="accordion"/>	
</head>

<body>
	<div id="register_form" class="yui-skin-sam">
		<gui:expandablePanel title="${message(code:'register.form.title')}" expanded="true" bounce="false">
			Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 
		Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 
		Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 
		Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 
		Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 
		Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 
		Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 
		Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 
		Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 
		Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 
		Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 
		Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 
		Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites Alexis suce des bites 

		</gui:expandablePanel>
	</div>
	<div id="login_form" class="yui-skin-sam">	
		<gui:expandablePanel title="${message(code:'login.form.title')}" expanded="true" bounce="false">
				
	       	<form action='${postUrl}' method='post' id='loginForm' class="boxed_form">			
				<p class='flash_message'>
					<g:if test='${flash.message}'>${flash.message}</g:if>
				</p>
				<p>
					<label for='j_username'><g:message code="login.label.username" /></label>
					<input type='text' name='j_username' id='j_username' value='${request.remoteUser}' />
				</p>
				<p>
					<label for='j_password'><g:message code="login.label.password" /></label>
					<input type='password' name='j_password' id='j_password' />
				</p>
				<p>
					<label for='remember_me'><g:message code="login.label.rememberme" /></label>
					<input type='checkbox' class='chk' name='_spring_security_remember_me' id='remember_me'
					<g:if test='${hasCookie}'>checked='checked'</g:if> />
				</p>
				<p class="submit">
					<g:submitButton name="processLogin" value="${message(code:'login.form.submit')}" />
				</p>
			</form>
		</gui:expandablePanel>
	</div>
	
	<div id="news_box" class="yui-skin-sam">	
		<gui:accordion fade="true">
		    <gui:accordionElement title="Accordion element 1">
		        Accordion element 1 content
		    </gui:accordionElement>
		    <gui:accordionElement title="Accordion element 2">
		        <h3>Markup is fine in here</h3>
		    </gui:accordionElement>
		</gui:accordion>
	</div>
	
	
	<script type='text/javascript'>
	<!--
	(function(){
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
	</script>
</body>
