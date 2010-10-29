<head>
	<meta name='layout' content='main' />
	<title><g:message code="login.page.title" /></title>
	<gui:resources components="autoComplete"/>
        
</head>

<body>
		<h2><g:message code="profile" /></h2>
        <g:form name="registerForm" action="profile" class="boxed_form yui-skin-sam">

            <g:if test="${flash.message}">
                <div class="flash_message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${userInstance}">
                <div class="flash_message">
                        <g:renderErrors bean="${userInstance}" />
                </div>
            </g:hasErrors>
            <p>
                    <label for="email"><g:message code="user.enteryourmail" /></label>
                    <g:textField name="email" value="${userInstance?.email}" class="field${hasErrors(bean:userInstance,field:'email','error')}"/>
            </p>
            <p>
                    <label for='password'><g:message code="user.password" /></label>
                    <g:passwordField name='password' value="" class="field${hasErrors(bean:userInstance,field:'password','error')}"/>
            </p>
            <p>
                    <label for='password'><g:message code="user.repassword" /></label>
                    <g:passwordField name='repassword' value="" />
            </p>

            <p>
                    <label for="firstName"><g:message code="user.firstName" /></label>
                    <g:textField name="firstName" value="${userInstance?.firstName}" class="field${hasErrors(bean:userInstance,field:'firstName','error')}"/>
            </p>
            <p>
                    <label for="lastName"><g:message code="user.lastName" /></label>
                    <g:textField name="lastName" value="${userInstance?.lastName}" class="field${hasErrors(bean:userInstance,field:'lastName','error')}"/>
            </p>
            <p>
                    <label for="address.street"><g:message code="address.street" /></label>
                    <g:textField name="address.street" value="${userInstance?.address?.street}" class="field${hasErrors(bean:userInstance,field:'address.street','error')}"/>
            </p>
            <p>
                    <label for="address.zipCode"><g:message code="address.zipCode" /></label>
                    <g:textField name="address.zipCode" value="${userInstance?.address?.zipCode}" class="field${hasErrors(bean:userInstance,field:'address.zipCode','error')}"/>
            </p>
            <p>
                    <label for="address.town"><g:message code="address.town" /></label>
                    <g:textField name="address.town" value="${userInstance?.address?.town}" class="field${hasErrors(bean:userInstance,field:'address.town','error')}"/>
            </p>
            <p>
                    <label for="phone"><g:message code="user.phone" /></label>
                    <g:textField name="phone" value="${userInstance?.phone}" class="field${hasErrors(bean:userInstance,field:'phone','error')}"/>
            </p>
            <p>
              <label for="showEmail"><g:message code="user.showEmail" default="Show Email" /></label>
              <g:checkBox name="showEmail" value="${userInstance?.showEmail}" />
            </p>
            <g:ifAllGranted role="ROLE_EXTERNAL">
            <div>
                <label for="company"><g:message code="external.company" /></label>
                <gui:autoComplete
                  id="company"
                  controller="company"
                  action="listCompanyAsJSON"
                  minQueryLength='2'
                  queryDelay='0.5'
                  value="${company}"

                />
            </div>
            <p>
              <label for="formerStudent"><g:message code="external.formerstudent" /></label>
              <g:checkBox name="formerStudent" value="${profile?.formerStudent}" />
            </p>
            </g:ifAllGranted>
            <g:ifAllGranted role="ROLE_STUDENT">
                  <p>
                    <label for="formation"><g:message code="student.formation" /></label>
                    <g:select name="promotion" from="${formations}"  optionKey="id" optionValue="value" value="${promotion}" />
                  </p>
                  <p>
                    <label for="visible"><g:message code="student.visible" /></label>
                    <g:checkBox name="visible" value="${profile?.visible}" />
                  </p>
            </g:ifAllGranted>
                          
            <p class="submit">
                    <g:buildSubmitButton value="profile.edit" action="profile"/>
            </p>
        </g:form>
</body>
