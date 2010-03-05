
<%@ page import="me.hcl.seekin.Formation.Millesime" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="millesime.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="millesime.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${millesimeInstance?.id}" />
                    
                       <p>
                            <label><g:message code="millesime.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: millesimeInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="millesime.beginDate" default="Begin Date" /></label>
                            <span class="field_value">
                            
                            ${millesimeInstance?.beginDate.formatDate()}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="millesime.endDate" default="End Date" /></label>
                            <span class="field_value">
                            
                            ${millesimeInstance?.endDate.formatDate()}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="millesime.current" default="Current" /></label>
                            <span class="field_value">
                            
                            <g:formatBoolean boolean="${millesimeInstance?.current}" />
                            
                            </span>
                      </p>
                        
                      
                      <div class="actionpad yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
