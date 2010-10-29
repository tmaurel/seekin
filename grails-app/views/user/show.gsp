
<%@ page import="me.hcl.seekin.Auth.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="user.show" /></title>
        
        <tm:resources />
    </head>
    <body>

        <h2><g:message code="user.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
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

%{--                       <p>
                            <label><g:message code="message.private" default="Private Message" /></label>
                            <span class="field_value">

                            <g:link controller="message" action="write" params='[id:"${fieldValue(bean: userInstance, field: "id")}"]'>
                                <img src="${resource(dir:'images/icons',file:'private_msg.png')}" alt="${message(code:'message.private')}"/>
                            </g:link>

                            </span>
                      </p>--}%
                        
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
                        
                      <br />
                      <div class="actionpad yui-skin-sam">
                        <g:ifAnyGranted role="ROLE_ADMIN">
                            <g:buildShowButtons />
                        </g:ifAnyGranted>
                        <g:ifAnyGranted role="ROLE_FORMATIONMANAGER">
                            <g:deleteButton />
                        </g:ifAnyGranted>
                      </div>
            </g:form>
       </body>
</html>
