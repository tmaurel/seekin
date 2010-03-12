
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="contact" /></title>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="contact" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${contactInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${contactInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form yui-skin-sam" method="post" >

                <p>
                      <label for="fullName"><g:message code="contact.fullName" /></label>
                      <g:textField name="fullName"  class="field${hasErrors(bean:contactInstance ,field:'fullName','error')}" value="${contactInstance.fullName}" />

                </p>
                <p>
                      <label for="email"><g:message code="user.email" /></label>
                      <g:textField name="email" class="field${hasErrors(bean:contactInstance ,field:'email','error')}" value="${contactInstance.email}" />

                </p>
                <p>
                      <label for="subject"><g:message code="contact.subject" /></label>
                      <g:textField name="subject" value="${params.subject}" class="field${hasErrors(bean:contactInstance ,field:'subject','error')}" value="${contactInstance.subject}"/>

                </p>
                <p>
                      <label for="body"><g:message code="contact.body" /></label>
                      <g:textArea name="body" value="${params.body}" class="field${hasErrors(bean:contactInstance ,field:'body','error')}" value="${contactInstance.body}"/>

                </p>
                <p>
                        <label for='captcha'><g:message code="register.form.captcha" /></label>
                        <g:textField name="captcha" class="field${hasErrors(bean:contactInstance ,field:'captcha','error')}" />
                </p>
                <p class="submit">
                        <img src="${createLink(action:'generateCaptcha')}" alt="Captcha" /><br />
                        <g:buildSubmitButton value="contact.submit" action="index"/>
                </p>

      </g:form>

    </body>
</html>
