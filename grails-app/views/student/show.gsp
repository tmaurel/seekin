
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
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
			<h3>${studentInstance?.user?.firstName?.encodeAsHTML()} ${studentInstance?.user?.lastName?.encodeAsHTML()}</h3>
			
			<g:if test="${studentInstance?.visible}">
			<p>
			  <label><g:message code="user.address" /></label>
			  <span class="field_value">
			  ${studentInstance?.user?.address?.encodeAsHTML()}
			  </span>
			</p>
			<p>
			  <label><g:message code="user.phone" /></label>
			  <span class="field_value">
			  ${studentInstance?.user?.phone?.encodeAsHTML()}
			  </span>
			</p>
			<g:if test="${studentInstance?.user?.showEmail}">
			<p>
			  <label><g:message code="user.email" /></label>
			  <span class="field_value">
			  ${studentInstance?.user?.email?.encodeAsHTML()}
			  </span>
			</p>
			</g:if>
			<h4><g:message code="promotion" /></h4>
			<ul>
			  	<g:each in="${studentInstance?.promotions}" var="promotionInstance">
				  <li>${promotionInstance?.millesime?.beginDate?.format('yyyy')} - ${promotionInstance?.millesime?.endDate?.format('yyyy')} > <g:link controller="formation" action="show" id="${promotionInstance?.formation.id}">${promotionInstance?.formation?.label?.encodeAsHTML()}</g:link></li>
				</g:each>
			</ul>
			<h4><g:message code="student.internships" /></h4>
			<g:if test="${studentInstance?.internships}">
			<ul>
			<g:each in="${studentInstance?.internships}" var="internshipInstance">
			  <li><g:link controller="internship" action="show" id="${internshipInstance.id}">${internshipInstance?.subject?.encodeAsHTML()}</g:link> @ <g:link controller="company" action="show" id="${internshipInstance?.company?.id}">${internshipInstance?.company?.name?.encodeAsHTML()}</g:link> (${internshipInstance?.beginAt?.format('yyyy')?.encodeAsHTML()})</li>
			</g:each>
			</ul>
			</g:if>
			<g:else>
				<g:message code="student.nointernship" />
			</g:else>
			</g:if>
			<g:else>
			<g:message code="student.show.notvisible" />
			</g:else>
            </g:form>
       </body>
</html>
