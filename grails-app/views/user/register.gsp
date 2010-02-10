<head>
	<meta name='layout' content='main' />
	<title><g:message code="login.page.title" /></title>
	<gui:resources components="accordion, autoComplete"/>
</head>

<body>
        <g:form name="registerForm" action="register" >
              <g:hiddenField name="usertype" value="${usertype}" />
              <div id="register_form" class="yui-skin-sam">
                    <gui:expandablePanel title="${message(code:'register.form.global')}" expanded="true" bounce="false">
                            <div class="boxed_form">
                                    <g:if test="${flash.message}">
                                        <div class="flash_message">${flash.message}</div>
                                    </g:if>
                                    <g:hasErrors bean="${person}">
                                        <div class="flash_message">
                                                <g:renderErrors bean="${person}" />
                                        </div>
                                    </g:hasErrors>
                                    <p>
                                            <label for="email"><g:message code="register.form.enteryourmail" /></label>
                                            <g:textField name="email" value="${person?.email?.encodeAsHTML()}" />
                                    </p>
                                    <p>
                                            <label for='password'><g:message code="login.label.password" /></label>
                                            <g:passwordField name='password' />
                                    </p>
                                    <p>
                                            <label for='password'><g:message code="register.form.repassword" /></label>
                                            <g:passwordField name='repassword' />
                                    </p>
                            </div>
                    </gui:expandablePanel>
              </div>
              <div id="captcha_form" class="yui-skin-sam">
                      <gui:expandablePanel title="${message(code:'register.form.validation')}" expanded="true" bounce="false">
                            <div class="boxed_form">
                                    <p>
                                            <label for='captcha'><g:message code="register.form.captcha" /></label>
                                            <g:textField name="captcha" />
                                            <img src="${createLink(action:'generateCaptcha')}" alt="Captcha" />
                                    </p>
                                    <p class="submit">
                                            <g:submitButton name="processRegister" value="${message(code:'register.form.submit')}" />
                                    </p>
                            </div>
                    </gui:expandablePanel>

                </div>
                <div id="profile_form" class="yui-skin-sam">
                      <gui:expandablePanel title="${message(code:'register.form.profile')}" expanded="true" bounce="false">
                            <div class="boxed_form">
                                    <p>
                                            <label for="firstName"><g:message code="profile.firstName" /></label>
                                            <g:textField name="firstName" value="${user?.firstName?.encodeAsHTML()}" />
                                    </p>
                                    <p>
                                            <label for="lastName"><g:message code="profile.lastName" /></label>
                                            <g:textField name="lastName" value="${user?.lastName?.encodeAsHTML()}" />
                                    </p>
                                    <p>
                                            <label for="street"><g:message code="address.street" /></label>
                                            <g:textField name="street" value="${user?.address?.street?.encodeAsHTML()}" />
                                    </p>
                                    <p>
                                            <label for="zipCode"><g:message code="address.zipcode" /></label>
                                            <g:textField name="zipCode" value="${user?.address?.zipCode?.encodeAsHTML()}" />
                                    </p>
                                    <p>
                                            <label for="town"><g:message code="address.city" /></label>
                                            <g:textField name="town" value="${user?.address?.town?.encodeAsHTML()}" />
                                    </p>
                                    <p>
                                            <label for="phone"><g:message code="profile.phone" /></label>
                                            <g:textField name="phone" value="${user?.phone?.encodeAsHTML()}" />
                                    </p>
                                    <g:isExternal profile="${profile}">
                                    <p>
                                      <label for="formerStudent"><g:message code="profile.external.formerstudent" /></label>
                                      <g:checkBox name="formerStudent" value="${profile?.formerStudent}" />
                                    </p>
                                    <div>
                                        <label for="company"><g:message code="profile.external.company" /></label>
                                        <gui:autoComplete
                                          id="company"
                                          controller="company"
                                          action="listCompanyAsJSON"
                                          minQueryLength='2'
                                          queryDelay='0.5'
                                          value="${company}"

                                        />
                                    </div>
                                    </g:isExternal>
                                    <g:isStudent profile="${profile}">
                                          <p>
                                            <label for="formation"><g:message code="profile.student.formation" /></label>
                                            <g:select name="formation" from="${formations}"  optionKey="id" optionValue="label"/>
                                          </p>
                                    </g:isStudent>
                                    <p>
                                      <label for="visible"><g:message code="profile.visible" /></label>
                                      <g:checkBox name="visible" value="${profile?.visible}" />
                                    </p>
                            </div>
                    </gui:expandablePanel>

                </div>
        </g:form>
</body>
