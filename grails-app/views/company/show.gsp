
<%@ page import="me.hcl.seekin.Internship.Company" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="company.show" /></title>
        
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
                        
                       <div class="properties_list">
                            <label><g:message code="company.address" default="Address" /></label>
                             <ul>
                            <g:if test="${companyInstance?.address}">
                              <li><g:link controller="address" action="show" id="${companyInstance?.address?.id}">${companyInstance?.address?.encodeAsHTML()}</g:link></li>
                            </g:if>
                            <g:each in="${addresses}" var="address">
                                <li><g:link controller="address" action="show" id="${address?.id}">${address?.encodeAsHTML()}</g:link></li>
                            </g:each>
                            </ul>
                      </div>
                        
                       <div class="properties_list">
                            <label><g:message code="company.phone" default="Phone" /></label>
                             <ul>
                            <g:if test="${companyInstance?.phone}">
                              <li>${companyInstance?.phone?.encodeAsHTML()}</li>
                            </g:if>
                            <g:each in="${phones}" var="phone">
                                <li>${phone?.encodeAsHTML()}</li>
                            </g:each>
                            </ul>
                      </div>
                        
                       <div class="properties_list">
                            <label><g:message code="company.employees" default="Employees" /></label>
                           
                            <ul>
                            <g:each in="${companyInstance?.employees}" var="userInstance">
                                <li><g:link controller="user" action="show" id="${userInstance.id}">${userInstance.encodeAsHTML()}</g:link></li>
                            </g:each>
                            </ul>

                      </div>
                        
                      <br />

                       <div class="actionpad yui-skin-sam">
                       <g:ifAnyGranted role="ROLE_ADMIN,ROLE_FORMATIONMANAGER">
                            <g:buildShowButtons />
                       </g:ifAnyGranted>
                        <g:ifAnyGranted role="ROLE_EXTERNAL">
                          <g:if test="${ok}">
                            <g:YUISubmitbutton value="edit" action="edit" />
                          </g:if>
                        </g:ifAnyGranted>
                        <g:ifAnyGranted role="ROLE_STUDENT,ROLE_STAFF">
                          <g:listButton />
                        </g:ifAnyGranted>
                       </div>
            </g:form>
       </body>
</html>
