
<%@ page import="me.hcl.seekin.Ressource.Link" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="link.show" /></title>
        
        <tm:resources />
    </head>
    <body>

        <h2><g:message code="link.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${linkInstance?.id}" />
                    
                       <p>
                            <label><g:message code="link.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: linkInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="link.title" default="Title" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: linkInstance, field: "title")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="link.url" default="Url" /></label>
                            <span class="field_value">
                            
                            <a href="${fieldValue(bean: linkInstance, field: "url")}">${fieldValue(bean: linkInstance, field: "url")}</a>
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="link.description" default="Description" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: linkInstance, field: "description")}
                            
                            </span>
                      </p>
                        
                      
                      <div class="actionpad yui-skin-sam">
                        <g:ifAnyGranted role="ROLE_ADMIN,ROLE_FORMATIONMANAGER">
                            <g:buildShowButtons />
                        </g:ifAnyGranted>
                        <g:ifNotGranted role="ROLE_ADMIN,ROLE_FORMATIONMANAGER">
                            <g:listButton />
                        </g:ifNotGranted>
                      </div>
            </g:form>
       </body>
</html>
