
<%@ page import="me.hcl.seekin.Auth.Role" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="role.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="role.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${roleInstance?.id}" />
                    
                       <p>
                            <label><g:message code="role.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: roleInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="role.authority" default="Authority" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: roleInstance, field: "authority")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="role.description" default="Description" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: roleInstance, field: "description")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="role.people" default="People" /></label>
                            <span class="field_value">
                            
                            <ul>
                            <g:each in="${roleInstance?.people}" var="userInstance">
                                <li><g:link controller="user" action="show" id="${userInstance.id}">${userInstance.encodeAsHTML()}</g:link></li>
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
