
<%@ page import="me.hcl.seekin.Company" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="company.show" /></title>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>

        <h2><g:message code="company.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${companyInstance?.id}" />
                    
                       <p>
                            <label><g:message code="company.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: companyInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="company.name" default="Name" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: companyInstance, field: "name")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="company.address" default="Address" /></label>
                            <span class="field_value">
                            
                            <g:link controller="address" action="show" id="${companyInstance?.address?.id}">${companyInstance?.address?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="company.phone" default="Phone" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: companyInstance, field: "phone")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="company.employees" default="Employees" /></label>
                            <span class="field_value">
                            
                            <ul>
                            <g:each in="${companyInstance?.employees}" var="userInstance">
                                <li><g:link controller="user" action="show" id="${userInstance.id}">${userInstance.encodeAsHTML()}</g:link></li>
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
