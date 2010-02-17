
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
                            <label><g:message code="user.enabled" default="Enabled" /></label>
                            <span class="field_value">
                            
                            <g:formatBoolean boolean="${userInstance?.enabled}" />
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="user.firstName" default="First Name" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: userInstance, field: "firstName")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="user.lastName" default="Last Name" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: userInstance, field: "lastName")}
                            
                            </span>
                      </p>



                      <p>
                            <label><g:message code="address.street" default="Street" /></label>
                            <span class="field_value">

                            ${fieldValue(bean: userInstance?.address, field: 'street')}

                            </span>
                      </p>

                     <p>
                            <label><g:message code="address.town" default="Town" /></label>
                            <span class="field_value">

                            ${fieldValue(bean: userInstance?.address, field: 'town')}

                            </span>
                      </p>

                     <p>
                            <label><g:message code="address.zipCode" default="Zip Code" /></label>
                            <span class="field_value">

                            ${fieldValue(bean: userInstance?.address, field: 'zipCode')}

                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="user.phone" default="Phone" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: userInstance, field: "phone")}
                            
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
                            
                            ${roleNames}
                            
                            </span>
                      </p>
                        
                      
                      <div class="submit yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
