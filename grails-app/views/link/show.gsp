
<%@ page import="me.hcl.seekin.Ressource.Link" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="link.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="link.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
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
                            
                            ${fieldValue(bean: linkInstance, field: "url")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="link.description" default="Description" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: linkInstance, field: "description")}
                            
                            </span>
                      </p>
                        
                      
                      <div class="submit yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
