
<%@ page import="me.hcl.seekin.Auth.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="user.edit"/></title>
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="user.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${userInstance}">
      <div class="flash_message"><g:renderErrors bean="${userInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" >
          <g:hiddenField name="id" value="${userInstance?.id}" />
          <g:hiddenField name="version" value="${userInstance?.version}" />
          
                <p>

                          <label for="email"><g:message code="user.email" default="Email" /></label>
                          <g:textField name="email" value="${fieldValue(bean: userInstance, field: 'email')}" />

                </p>
          
                <p>

                          <label for="password"><g:message code="user.password" default="Password" /></label>
                          <g:textField name="password" value="${fieldValue(bean: userInstance, field: 'password')}" />

                </p>
          
                <p>

                          <label for="enabled"><g:message code="user.enabled" default="Enabled" /></label>
                          <g:checkBox name="enabled" value="${userInstance?.enabled}" />

                </p>
          
                <p>

                          <label for="profile"><g:message code="user.profile" default="Profile" /></label>
                          <g:select name="profile.id" from="${me.hcl.seekin.Profile.Profile.list()}" optionKey="id" value="${userInstance?.profile?.id}" noSelection="['null': '']" />

                </p>
          
                <p>

                          <label for="showEmail"><g:message code="user.showEmail" default="Show Email" /></label>
                          <g:checkBox name="showEmail" value="${userInstance?.showEmail}" />

                </p>
          
                <p>

                          <label for="authorities"><g:message code="user.authorities" default="Authorities" /></label>
                          

                </p>
          
          <div class="submit yui-skin-sam">
            <g:buildEditButtons />
          </div>
      </g:form>
    </body>
</html>
