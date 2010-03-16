
<%@ page import="me.hcl.seekin.Internship.Report" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="report.show" /></title>
        
        <tm:resources />
    </head>
    <body>

        <h2><g:message code="report.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${reportInstance?.id}" />
                    
                       <p>
                            <label><g:message code="report.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: reportInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="report.title" default="Title" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: reportInstance, field: "title")}
                            
                            </span>
                      </p>
                       <p>
                            <label><g:message code="report.internship" default="Internship" /></label>
                            <span class="field_value">
                            
                            <g:link controller="internship" action="show" id="${reportInstance?.internship?.id}">${reportInstance?.internship?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>

                       <p>
                            <label><g:message code="document.download" default="Download" /></label>
                            <g:link controller="file" action="download" id="${reportInstance?.fileData?.id}">
                                    <img src="${resource(dir:'images/icons',file:'dl.png')}" alt="Download" />
                            </g:link>
                      </p>
                      
                      <div class="actionpad yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
