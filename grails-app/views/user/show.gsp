
<%@ page import="me.hcl.seekin.Auth.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="user.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="user.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${userInstance?.id}" />
                    
                       <p>
                            <label><g:message code="user.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: userInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="user.email" default="Email" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: userInstance, field: "email")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="user.password" default="Password" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: userInstance, field: "password")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="user.enabled" default="Enabled" /></label>
                            <span class="field_value">
                            
                            <g:formatBoolean boolean="${userInstance?.enabled}" />
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="user.profile" default="Profile" /></label>
                            <span class="field_value">
                            
                            <g:link controller="profile" action="show" id="${userInstance?.profile?.id}">${userInstance?.profile?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="user.showEmail" default="Show Email" /></label>
                            <span class="field_value">
                            
                            <g:formatBoolean boolean="${userInstance?.showEmail}" />
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="user.authorities" default="Authorities" /></label>
                            <span class="field_value">
                            
                            <ul>
                            <g:each in="${userInstance?.authorities}" var="roleInstance">
                                <li><g:link controller="role" action="show" id="${roleInstance.id}">${roleInstance.encodeAsHTML()}</g:link></li>
                            </g:each>
                            </ul>
                            
                            </span>
                      </p>
                        
                      
                      <div class="submit yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
