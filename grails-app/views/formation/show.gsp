
<%@ page import="me.hcl.seekin.Formation.Formation" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="formation.show" /></title>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>

        <h2><g:message code="formation.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${formationInstance?.id}" />
                    
                       <p>
                            <label><g:message code="formation.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: formationInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="formation.label" default="Label" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: formationInstance, field: "label")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="formation.description" default="Description" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: formationInstance, field: "description")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="formation.promotions" default="Promotions" /></label>
                            <span class="field_value">
                            
                            <ul>
                            <g:each in="${formationInstance?.promotions}" var="promotionInstance">
                                <li><g:link controller="promotion" action="show" id="${promotionInstance.id}">${promotionInstance.encodeAsHTML()}</g:link></li>
                            </g:each>
                            </ul>
                            
                            </span>
                      </p>
                        
                      
                      <div class="actionpad yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
