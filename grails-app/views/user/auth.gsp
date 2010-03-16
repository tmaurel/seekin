<head>
	<meta name='layout' content='main' />
	<title><g:message code="login.page.title" /></title>
        
</head>

<body>
	<div id="register_form" class="yui-skin-sam">
		<gui:expandablePanel title="${message(code:'register.form.title')}" expanded="true" bounce="false">
			<g:form name="registerForm" url="[action:'register',controller:'user']" class="boxed_form">	
				<p>
					<label for="email"><g:message code="register.form.enteryourmail" /></label>
					<g:textField name="email" />
				</p>
                                <p>
					<label for="firstName"><g:message code="user.firstName" /></label>
					<g:textField name="firstName" />
				</p>
                                <p>
					<label for="lastName"><g:message code="user.lastName" /></label>
					<g:textField name="lastName" />
				</p>
				<p class="centered_para">
					<g:message code="register.form.type" />
				</p>
				<p class="centered_para">
					<g:radioGroup name="usertype" labels="['register.type.student', 'register.type.staff', 'register.type.external']" values="[1,2,3]" value="1">
						${it.radio} <g:message code="${it.label}" />
					</g:radioGroup>
				</p>
				<p class="submit">
                                        <g:hiddenField name="fromHome" value="1" />
					<g:buildSubmitButton value="register.form.submit" action="register"/>
				</p>
			</g:form>
		</gui:expandablePanel>
	</div>
	<div id="login_form" class="yui-skin-sam">	
		<gui:expandablePanel title="${message(code:'login.form.title')}" expanded="true" bounce="false">
			<form name="loginForm" id="loginForm" method="post" action="${postUrl}" class="boxed_form">		
				<g:if test="${flash.message}">
                                        <div class="flash_message">${flash.message}</div>
                                    </g:if>
				<p>
					<label for='j_username'><g:message code="user.email" /></label>
					<g:textField  name='j_username' value='${request.remoteUser}' />
				</p>
				<p>
					<label for='j_password'><g:message code="user.password" /></label>
					<g:passwordField name='j_password' />
				</p>
				<p>
					<label for='remember_me'><g:message code="login.label.rememberme" /></label>
					<g:checkBox class='chk' name='_spring_security_remember_me' value="${hasCookie}" />
				</p>
				<p class="submit">
                                        <g:buildSubmitButton value="login.form.submit" action="login"/><br /><br />
                                        <a href="${createLink(action:'lostPassword')}"><g:message code="user.lostPassword"/></a>
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
