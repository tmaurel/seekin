
<%@ page import="me.hcl.seekin.Auth.Role.Student" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="student.show" /></title>
        
        <tm:resources />
    </head>
    <body>
        <h2><g:message code="student.show" /></h2>
        <div class="boxed_form">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
			<h3><g:fieldValue bean="${studentInstance}" field="user" /></h3>
			
			<g:if test="${studentInstance?.visible}">
			<p>
			  <label><g:message code="user.address" /></label>
			  <span class="field_value"><g:link controller="address" action="show" id="${studentInstance?.user?.address?.id}"><g:fieldValue bean="${studentInstance?.user}" field="address" /></g:link></span>
			</p>
			<p>
			  <label><g:message code="user.phone" /></label>
			  <span class="field_value"><g:fieldValue bean="${studentInstance?.user}" field="phone" /></span>
			</p>
			<g:if test="${studentInstance?.user?.showEmail}">
			<p>
			  <label><g:message code="user.email" /></label>
			  <span class="field_value"><g:fieldValue bean="${studentInstance?.user}" field="email" /></span>
			</p>
			</g:if>
			<h4><g:message code="student.promotions" /></h4>
			<ul>
			  	<g:each in="${studentInstance?.promotions}" var="promotion">
				  <li><g:link controller="promotion" action="show" id="${promotion?.id}">${promotion}</g:link></li>
				</g:each>
			</ul>
			<h4><g:message code="student.internships" /></h4>
			<g:if test="${studentInstance?.internships}">
			<ul>
			<g:each in="${studentInstance?.internships}" var="internship">
			  <li><g:link controller="internship" action="show" id="${internship.id}">${internship}</g:link> - <g:link controller="company" action="show" id="${internship?.company?.id}"><g:fieldValue bean="${internship}" field="company" /></g:link> (<g:fieldValue bean="${internship}" field="millesime" />)</li>
			</g:each>
			</ul>
			</g:if>
			<g:else>
				<g:message code="student.nointernship" />
			</g:else>
			<h4>
			  <g:message code="message.private" /> :
			  <g:link controller="message" action="write" id="${studentInstance?.user?.id}">
				  <img src="${resource(dir:'images/icons',file:'private_msg.png')}" alt="${message(code:'message.private')}"/>
			  </g:link>
			</h4>
			</g:if>
			<g:else>
			<g:message code="student.show.notvisible" />
			</g:else>
            </div>
       </body>
</html>
