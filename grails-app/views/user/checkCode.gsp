
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="user.lostPassword"/></title>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="user.lostPassword"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}"/></div>
      </g:if>
      <g:else>
        <g:form class="boxed_form" name="crud_panel" method="post" action="checkCode">
            <g:hiddenField name="id" value="${params.id}" />
            <p>
                    <label for='password'><g:message code="user.password" /></label>
                    <g:passwordField name='password' />
            </p>
            <p>
                    <label for='password'><g:message code="user.repassword" /></label>
                    <g:passwordField name='repassword' />
            </p>
            <div class="actionpad yui-skin-sam">
              <g:buildSubmitButton action="checkCode" value="user.lostPassword.submit" />
            </div>
        </g:form>
      </g:else>
    </body>
</html>
