
<%@ page import="me.hcl.seekin.Formation.Promotion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="promotion.edit"/></title>
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="promotion.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${promotionInstance}">
      <div class="flash_message"><g:renderErrors bean="${promotionInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" >
          <g:hiddenField name="id" value="${promotionInstance?.id}" />
          <g:hiddenField name="version" value="${promotionInstance?.version}" />
          
                <p>

                          <label for="students"><g:message code="promotion.students" default="Students" /></label>
                          <g:select name="students"
from="${me.hcl.seekin.Auth.Role.Student.list()}"
size="5" multiple="yes" optionKey="id"
value="${promotionInstance?.students}" />


                </p>
          
                <p>

                          <label for="millesime"><g:message code="promotion.millesime" default="Millesime" /></label>
                          <g:select name="millesime.id" from="${me.hcl.seekin.Formation.Millesime.list()}" optionKey="id" value="${promotionInstance?.millesime?.id}"  />

                </p>
          
                <p>

                          <label for="offers"><g:message code="promotion.offers" default="Offers" /></label>
                          <g:select name="offers"
from="${me.hcl.seekin.Internship.Offer.list()}"
size="5" multiple="yes" optionKey="id"
value="${promotionInstance?.offers}" />


                </p>
          
                <p>

                          <label for="formation"><g:message code="promotion.formation" default="Formation" /></label>
                          <g:select name="formation.id" from="${me.hcl.seekin.Formation.Formation.list()}" optionKey="id" value="${promotionInstance?.formation?.id}"  />

                </p>
          
          <div class="actionpad yui-skin-sam">
            <g:buildEditButtons />
          </div>
      </g:form>
    </body>
</html>
