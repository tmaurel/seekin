
<%@ page import="me.hcl.seekin.Auth.Role.Student" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="student.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>
        <h2><g:message code="student.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
			<h3>${studentInstance?.user?.firstName?.encodeAsHTML()} ${studentInstance?.user?.lastName?.encodeAsHTML()}</h3>
			<p>
			  <label><g:message code="user.address" /></label>
			  <span class="field_value">
			  ${studentInstance?.user?.address?.street?.encodeAsHTML()} ${studentInstance?.user?.address?.zipCode?.encodeAsHTML()} ${studentInstance?.user?.address?.town?.encodeAsHTML()}
			  </span>
			</p>
			<p>
			  <label><g:message code="formation" /></label>
			  <span class="field_value">
			  <g:link controller="formation" action="show" id="${studentInstance?.formation?.id}">${studentInstance?.formation?.label?.encodeAsHTML()}</g:link>
			  </span>
			</p>
			<h4><g:message code="student.internships" /></h4>
			<ul>
			<g:each in="${studentInstance?.internships}" var="internshipInstance">
				<li><g:link controller="internship" action="show" id="${internshipInstance.id}">${internshipInstance?.subject?.encodeAsHTML()}</g:link> @ <g:link controller="internship" action="show" id="${internshipInstance.id}">${internshipInstance?.company?.name?.encodeAsHTML()}</g:link></li>
			</g:each>
			</ul>
			<div class="submit yui-skin-sam">
			  <g:buildShowButtons />
			</div>
            </g:form>
       </body>
</html>
