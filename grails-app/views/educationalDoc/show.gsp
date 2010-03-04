
<%@ page import="me.hcl.seekin.Ressource.EducationalDoc" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="educationalDoc.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="educationalDoc.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${educationalDocInstance?.id}" />
                    
                       <p>
                            <label><g:message code="educationalDoc.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: educationalDocInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="educationalDoc.title" default="Title" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: educationalDocInstance, field: "title")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="document.download" default="Download" /></label>
                            <g:link controller="file" action="download" id="${educationalDocInstance?.fileData?.id}">
                                    <img src="${resource(dir:'images/icons',file:'dl.png')}" alt="Download" />
                            </g:link>
                      </p>
                      
                      <div class="submit yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>