
<%@ page import="me.hcl.seekin.Internship.Offer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="offer.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="offer.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${offerInstance?.id}" />
                    
                       <p>
                            <label><g:message code="offer.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: offerInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="offer.subject" default="Subject" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: offerInstance, field: "subject")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="offer.description" default="Description" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: offerInstance, field: "description")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="offer.beginAt" default="Begin At" /></label>
                            <span class="field_value">
                            
                            <g:formatDate date="${offerInstance?.beginAt.formatDate()}" />
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="offer.length" default="Length" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: offerInstance, field: "length")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="offer.status" default="Status" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: offerInstance, field: "status")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="offer.file" default="File" /></label>
                            <span class="field_value">
                            
                            <g:link controller="document" action="show" id="${offerInstance?.file?.id}">${offerInstance?.file?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                      
                      <div class="submit yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
