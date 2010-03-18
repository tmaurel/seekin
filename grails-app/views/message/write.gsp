
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="message.write" /></title>

        <tm:resources />
    </head>
    <body>
      <h2><g:message code="message.write" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${messageInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${messageInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" >

                <p>
                      <label><g:message code="message.recipients" default="Recipients" /></label>
                      <span class="field_value">
                      <g:each in="${messageInstance?.recipients}" var="recipient" status="i">
                          <g:hiddenField name="recipients" value="${recipient?.id}" />
                          <g:if test="${i > 0}">
                            ,
                          </g:if>
                          <g:link action="write" id="${recipient?.id}"> ${recipient}</g:link>
                      </g:each>

                      </span>
                </p>

                <p>
                      <label for="subject"><g:message code="message.subject" default="Subject" /></label>
                      <g:textField name="subject" class="field${hasErrors(bean:messageInstance ,field:'subject','error')}" value="${messageInstance?.subject}" />

                </p>

                <p>
                      <label for="body"><g:message code="message.body" /></label>
                      <g:textArea name="body" class="field${hasErrors(bean:messageInstance ,field:'body','error')}" value="${messageInstance?.body}"/>

                </p>


          <div class="actionpad yui-skin-sam">
             <g:YUISubmitbutton action="save" value="send" />
          </div>

      </g:form>

    </body>
</html>
