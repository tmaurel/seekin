<head>
	<meta name='layout' content='main' />
	<title><g:message code="login.page.title" /></title>
	<gui:resources components="accordion"/>	
</head>

<body>
	<div id="register_form" class="yui-skin-sam">
		<gui:expandablePanel title="${message(code:'register.form.title')}" expanded="true" bounce="false">
			<form action='register' method='post' id='registerForm' class="boxed_form">	
				<p>
					<label for="r_username"><g:message code="register.form.enteryourname" /></label>
					<g:textField name="r_username" />
				</p>
				<p class="centered_para">
					<g:message code="register.form.type" />
				</p>
				<p class="centered_para">
					<g:radioGroup name="lovesGrails" labels="[message(code:'register.type.student'),message(code:'register.type.staff'),message(code:'register.type.external')]" values="[1,2,3]" >
						${it.radio} ${it.label}
					</g:radioGroup>
				</p>
				<p class="submit">
					<g:submitButton name="processRegister" value="${message(code:'register.form.submit')}" />
				</p>
			</form>
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
					<g:textField  name='j_username' value='${request.remoteUser}' />
				</p>
				<p>
					<label for='j_password'><g:message code="login.label.password" /></label>
					<g:passwordField name='j_password' />
				</p>
				<p>
					<label for='remember_me'><g:message code="login.label.rememberme" /></label>
					<g:checkBox class='chk' name='_spring_security_remember_me' value="${hasCookie}" />
				</p>
				<p class="submit">
					<g:submitButton name="processLogin" value="${message(code:'login.form.submit')}" />
				</p>
			</form>
		</gui:expandablePanel>
	</div>	
	
	<script type='text/javascript'>
	<!--
	(function(){
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
	</script>
</body>
