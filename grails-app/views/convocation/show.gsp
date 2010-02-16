
<%@ page import="me.hcl.seekin.Convocation" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="convocation.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="convocation.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${convocationInstance?.id}" />
                    
                       <p>
                            <label><g:message code="convocation.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: convocationInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="convocation.date" default="Date" /></label>
                            <span class="field_value">
                            
                            <g:formatDate date="${convocationInstance?.date}" />
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="convocation.building" default="Building" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: convocationInstance, field: "building")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="convocation.room" default="Room" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: convocationInstance, field: "room")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="convocation.internship" default="Internship" /></label>
                            <span class="field_value">
                            
                            <g:link controller="internship" action="show" id="${convocationInstance?.internship?.id}">${convocationInstance?.internship?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                      
                      <div class="submit yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
