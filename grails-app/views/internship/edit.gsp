
<%@ page import="me.hcl.seekin.Internship.Internship" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="internship.edit"/></title>
        
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="internship.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${internshipInstance}">
      <div class="flash_message"><g:renderErrors bean="${internshipInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" >
          <g:hiddenField name="id" value="${internshipInstance?.id}" />
          <g:hiddenField name="version" value="${internshipInstance?.version}" />
          <g:hiddenField name="reason" value="${internshipInstance?.reason}" />
          
                <p>

                          <label for="subject"><g:message code="internship.subject" default="Subject" /></label>
                          <g:textField name="subject" class="field${hasErrors(bean:internshipInstance ,field:'subject','error')}" value="${internshipInstance?.subject}" />

                </p>

                <p>

                          <label for="description"><g:message code="internship.description" default="Description" /></label>
                          <g:textField name="description" class="field${hasErrors(bean:internshipInstance ,field:'description','error')}" value="${internshipInstance?.description}" />

                </p>
          
                <p>

                          <label for="beginAt"><g:message code="internship.beginAt" default="Begin At" /></label>
                          <g:datePicker name="beginAt" value="${internshipInstance?.beginAt}" precision="day" />

                </p>

                <p>
                    <label><g:message code="internship.length" default="Length" /></label>
                    <span class="field_value">

                    ${fieldValue(bean: internshipInstance, field: "length")}

                    </span>
                </p>

                <p>

                          <label for="report"><g:message code="internship.report" default="Report" /></label>
                          <span class="field_value">

                            <g:link controller="report" action="show" id="${internshipInstance?.report?.id}">${internshipInstance?.report?.title?.encodeAsHTML()}</g:link>

                          </span>

                </p>
          
                <p>

                          <label for="student"><g:message code="internship.student" default="Student" /></label>
                          <span class="field_value">

                            <g:link controller="user" action="show" id="${internshipInstance?.student?.id}">${internshipInstance?.student?.user?.firstName?.encodeAsHTML()} ${internshipInstance?.student?.user?.lastName?.encodeAsHTML()}</g:link>

                          </span>

                </p>
          
                <p>

                          <label for="academicTutor"><g:message code="internship.academicTutor" default="Academic Tutor" /></label>
                          <span class="field_value">

                            <g:link controller="user" action="show" id="${internshipInstance?.academicTutor?.id}">${internshipInstance?.academicTutor?.user?.firstName?.encodeAsHTML()} ${internshipInstance?.academicTutor?.user?.lastName?.encodeAsHTML()}</g:link>

                          </span>

                </p>

                 <p>
                      <label><g:message code="internship.company" default="Company" /></label>
                      <span class="field_value">

                      <g:link controller="company" action="show" id="${internshipInstance?.company?.id}">${internshipInstance?.company?.name?.encodeAsHTML()}</g:link>

                      </span>
                </p>

                <p>
                      <label for="address.street"><g:message code="address.street" /></label>
                      <g:textField name="address.street" class="field${hasErrors(bean:internshipInstance?.address ,field:'street','error')}" value="${internshipInstance?.address?.street}" />

                </p>

                <p>
                      <label for="address.town"><g:message code="address.town" /></label>
                      <g:textField name="address.town" class="field${hasErrors(bean:internshipInstance?.address ,field:'town','error')}" value="${internshipInstance?.address?.town}" />

                </p>

                <p>
                      <label for="address.zipCode"><g:message code="address.zipCode" /></label>
                      <g:textField name="address.zipCode" class="field${hasErrors(bean:internshipInstance?.address ,field:'zipCode','error')}" value="${internshipInstance?.address?.zipCode}" />

                </p>

                <p>
                      <label for="phone"><g:message code="company.phone" /></label>
                      <g:textField name="phone" class="field${hasErrors(bean:internshipInstance ,field:'phone','error')}" value="${internshipInstance.phone}" />

                </p>
          
                <p>

                          <label for="companyTutor"><g:message code="internship.companyTutor" default="Company Tutor" /></label>
                          <span class="field_value">

                            <g:link controller="user" action="show" id="${internshipInstance?.companyTutor?.id}">${internshipInstance?.companyTutor?.user?.firstName?.encodeAsHTML()} ${internshipInstance?.companyTutor?.user?.lastName?.encodeAsHTML()}</g:link>

                          </span>

                </p>
                <g:if test ="${internshipInstance?.convocation}">
                <p>

                          <label for="convocation"><g:message code="internship.convocation" default="Convocation" /></label>
                          <span class="field_value">

                            <g:link controller="convocation" action="show" id="${internshipInstance?.convocation?.id}">${internshipInstance?.convocation?.date?.formatDate().encodeAsHTML()}</g:link>

                          </span>

                </p>
                </g:if>
                <g:ifAnyGranted role="ROLE_ADMIN,ROLE_FORMATIONMANAGER">

                          <p>

                                    <label for="isApproval"><g:message code="internship.isApproval" default="Is Approval" /></label>
                                    <g:checkBox name="isApproval" value="${internshipInstance?.isApproval}" />

                          </p>

                </g:ifAnyGranted>
          
          
          <div class="actionpad yui-skin-sam">

             <g:ifAnyGranted role="ROLE_ADMIN">

                <g:buildDenyButtons />

             </g:ifAnyGranted>
             <g:ifNotGranted role="ROLE_ADMIN">

                <g:buildEditButtons />

             </g:ifNotGranted>
          </div>
      </g:form>
    </body>
</html>
