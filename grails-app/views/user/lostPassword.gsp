
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="user.lostPassword"/></title>
        
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="user.lostPassword"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}"/></div>
      </g:if>
      <g:form class="boxed_form" name="crud_panel" method="post" >

                <p>
                          <label for="email"><g:message code="user.email" default="Email" /></label>
                          <g:textField name="email" value="${params.email}" />
                </p>

          <div class="actionpad yui-skin-sam">
            <g:buildSubmitButton action="lostPassword" value="send" />
          </div>
      </g:form>
    </body>
</html>
