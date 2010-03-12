
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="user.renewal"/></title>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="user.renewal"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}"/></div>
      </g:if>
      <g:else>
        <g:form class="boxed_form" name="crud_panel" method="post" action="renewal">
            <p>
                    <label for='username'><g:message code="user.email" /></label>
                    <g:textField name='username' value="${params.username}"/>
            </p>
            <p>
                    <label for='password'><g:message code="user.password" /></label>
                    <g:passwordField name='password' />
            </p>
            <p>
                    <label for="formation"><g:message code="student.formation" /></label>
                    <g:select name="promotion" from="${formations}"  optionKey="id" optionValue="value" value="${params.promotion}" />
            </p>
            <div class="actionpad yui-skin-sam">
              <g:buildSubmitButton action="renewal" value="user.renewal.submit" />
            </div>
        </g:form>
      </g:else>
    </body>
</html>
