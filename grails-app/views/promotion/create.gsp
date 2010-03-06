
<%@ page import="me.hcl.seekin.Formation.Promotion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="promotion.create" /></title>
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>  
      <h2><g:message code="promotion.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${promotionInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${promotionInstance}" as="list" />
      </div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" action="save" method="post" >
          
                <p>
                      <label for="millesime"><g:message code="promotion.millesime" default="Millesime" />:</label>
                      <g:select name="millesime.id" from="${me.hcl.seekin.Formation.Millesime.list()}" optionKey="id" value="${promotionInstance?.millesime?.id}"  />

                </p>
          
                <p>
                      <label for="formation"><g:message code="promotion.formation" default="Formation" />:</label>
                      <g:select name="formation.id" from="${me.hcl.seekin.Formation.Formation.list()}" optionKey="id" value="${promotionInstance?.formation?.id}"  />

                </p>
          
          <div class="actionpad yui-skin-sam">
             <g:buildCreateButtons />
          </div>

      </g:form>

    </body>
</html>
