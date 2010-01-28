<head>
	<meta name='layout' content='main' />
	<title><g:message code="login.page.title" /></title>
	<gui:resources components="accordion"/>	
</head>

<body>
	<div id="register_form" class="yui-skin-sam">
		<gui:expandablePanel title="${message(code:'register.form.title')}" expanded="true" bounce="false">
			<g:form name="registerForm" url="[action:'register',controller:'user']" class="boxed_form">	
				<p>
					<label for="username"><g:message code="register.form.enteryourname" /></label>
					<g:textField name="username" />
				</p>
				<p class="centered_para">
					<g:message code="register.form.type" />
				</p>
				<p class="centered_para">
					<g:radioGroup name="lovesGrails" labels="['register.type.student', 'register.type.staff', 'register.type.external']" values="[1,2,3]" value="1">
						${it.radio} <g:message code="${it.label}" />
					</g:radioGroup>
				</p>
				<p class="submit">
					<g:submitButton name="processRegister" value="${message(code:'register.form.submit')}" />
				</p>
			</g:form>
		</gui:expandablePanel>
	</div>
	<div id="login_form" class="yui-skin-sam">	
		<gui:expandablePanel title="${message(code:'login.form.title')}" expanded="true" bounce="false">
			<form name="loginForm" id="loginForm" method="post" action="${postUrl}" class="boxed_form">		
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
