
<%@ page import="me.hcl.seekin.Formation.Promotion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="promotion.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="promotion.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${promotionInstance?.id}" />
                    
                       <p>
                            <label><g:message code="promotion.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: promotionInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <div class="properties_list">
                            <label><g:message code="promotion.students" default="Students" /></label>
                            
                            <ul>
                            <g:each in="${promotionInstance?.students}" var="studentInstance">
                                <li><g:link controller="student" action="show" id="${studentInstance.id}">${studentInstance.encodeAsHTML()}</g:link></li>
                            </g:each>
                            </ul>

                      </div>
                        
                       <p>
                            <label><g:message code="promotion.millesime" default="Millesime" /></label>
                            <span class="field_value">
                            
                            <g:link controller="millesime" action="show" id="${promotionInstance?.millesime?.id}">${promotionInstance?.millesime?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                       <div class="properties_list">
                            <label><g:message code="promotion.offers" default="Offers" /></label>
                            
                            <ul>
                            <g:each in="${promotionInstance?.offers}" var="offerInstance">
                                <li><g:link controller="offer" action="show" id="${offerInstance.id}">${offerInstance.encodeAsHTML()}</g:link></li>
                            </g:each>
                            </ul>
                            
                      </div>
                        
                       <p>
                            <label><g:message code="promotion.formation" default="Formation" /></label>
                            <span class="field_value">
                            
                            <g:link controller="formation" action="show" id="${promotionInstance?.formation?.id}">${promotionInstance?.formation?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                      
                      <div class="actionpad yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
