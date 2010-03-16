
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="message.show" /></title>
        
        <tm:resources />
    </head>
    <body>

        <h2><g:message code="message.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${messageInstance?.id}" />

                      
                       <p>
                            <label><g:message code="message.dateCreated" default="Date Sent" /></label>
                            <span class="field_value">

                            ${messageInstance?.dateCreated?.formatDateHour()}

                            </span>
                      </p>

                      <p>
                            <label><g:message code="message.recipients" default="Recipients" /></label>
                            <span class="field_value">

                            <g:each in="${messageInstance?.recipients}" var="recipient" status="i">
                                <g:if test="${i > 0}">
                                  ,
                                </g:if>
                                ${recipient}
                            </g:each>

                            </span>
                      </p>

                       <p>
                            <label><g:message code="message.subject" default="Subject" /></label>
                            <span class="field_value">

                            ${messageInstance?.subject}

                            </span>
                      </p>

                       <p>
                            <label><g:message code="message.body" default="Body" /></label>
                            <span class="field_value">

                            ${messageInstance?.body}

                            </span>
                      </p>

                      <br/>
                      <div class="actionpad yui-skin-sam">
                        
                      </div>
            </g:form>
       </body>
</html>
