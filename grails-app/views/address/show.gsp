
<%@ page import="me.hcl.seekin.Util.Address" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="address.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="address.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${addressInstance?.id}" />
                    
                       <p>
                            <label><g:message code="address.id" default="Id" /></label>
                            
                            ${fieldValue(bean: addressInstance, field: "id")}
                            

                        
                       <p>
                            <label><g:message code="address.street" default="Street" /></label>
                            
                            ${fieldValue(bean: addressInstance, field: "street")}
                            

                        
                       <p>
                            <label><g:message code="address.zipCode" default="Zip Code" /></label>
                            
                            ${fieldValue(bean: addressInstance, field: "zipCode")}
                            

                        
                       <p>
                            <label><g:message code="address.town" default="Town" /></label>
                            
                            ${fieldValue(bean: addressInstance, field: "town")}
                            

                        
                      </p>
                      <div class="submit yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
