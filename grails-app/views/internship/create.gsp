
<%@ page import="me.hcl.seekin.Internship.Internship" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="internship.create" /></title>
        <g:YUIButtonRessource />
        <tm:resources />
        <gui:resources components="accordion, autoComplete, tabView"/>
        <yui:javascript dir="json" file="json-min.js" />
    </head>
    <body>
      <h2><g:message code="internship.create" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <g:hasErrors bean="${internshipInstance}">
      <div class="flash_message">
          <g:renderErrors bean="${internshipInstance}" as="list" />
      </div>

      </g:hasErrors>

      <div class = "yui-skin-sam">
        <gui:tabView>
          <gui:tab label="${message(code: 'internship.createFromZero')}" active="true">
            <g:form class="boxed_form" name="crud_panel" action="save" method="post"  enctype="multipart/form-data">
                      <g:hiddenField name="fromOffer" value="${internshipInstance?.fromOffer}" />
                      <p>
                            <label for="subject"><g:message code="internship.subject" default="Subject" /></label>
                            <g:textField name="subject" class="field${hasErrors(bean:internshipInstance ,field:'subject','error')}" value="${internshipInstance?.subject}" />

                      </p>

                      <p>
                            <label for="description"><g:message code="internship.description" default="Description" /></label>
                            <g:textArea name="description" class="field${hasErrors(bean:internshipInstance ,field:'description','error')}" value="${internshipInstance?.description}" />

                      </p>

                      <p>
                            <label for="data"><g:message code="offer.file" default="File" /></label>

                            <g:if test="${internshipInstance?.fromOffer && internshipInstance?.file}">
                                    <g:hiddenField name="file.id" value="${internshipInstance?.file?.id}" />
                                    <span class="field_value">
                                      <g:link controller="file" action="download" id="${internshipInstance?.file?.fileData?.id}">
                                              ${internshipInstance?.file?.title}
                                      </g:link>
                                    </span>
                            </g:if>
                            <g:else>
                                    <input type="file" name="data"/>
                            </g:else>
                      </p>

                      <p>
                            <label for="beginAt"><g:message code="internship.beginAt" default="Begin At" /></label>
                            <g:datePicker name="beginAt" value="${internshipInstance?.beginAt}" precision="day" />

                      </p>

                      <p>
                            <label for="length"><g:message code="internship.length" default="Length" /></label>
                            <g:select name="length" from="${1..12}" value="${internshipInstance?.length}" />

                      </p>

                      <div>
                            <label for="company"><g:message code="internship.company" default="Company" /></label>

                            <gui:autoComplete
                              id="companyName"
                              controller="company"
                              action="listCompanyAsJSON"
                              minQueryLength='2'
                              queryDelay='0.5'
                              value="${company}"
                            />

                      </div>

                      <p>
                            <label for="address.street"><g:message code="address.street" /></label>
                            <g:textField name="address.street" class="field${hasErrors(bean:internshipInstance?.address ,field:'street','error')}" value="${params.street}" />

                      </p>

                      <p>
                            <label for="address.town"><g:message code="address.town" /></label>
                            <g:textField name="address.town" class="field${hasErrors(bean:internshipInstance?.address ,field:'town','error')}" value="${params.town}" />

                      </p>

                      <p>
                            <label for="address.zipCode"><g:message code="address.zipCode" /></label>
                            <g:textField name="address.zipCode" class="field${hasErrors(bean:internshipInstance?.address ,field:'zipCode','error')}" value="${params.zipCode}" />

                      </p>

                      <p>
                            <label for="phone"><g:message code="company.phone" /></label>
                            <g:textField name="phone" class="field${hasErrors(bean:internshipInstance ,field:'phone','error')}" value="${params.phone}" />

                      </p>

                      <p>
                            <label for="firstName"><g:message code="companyTutor.firstName" /></label>
                            <g:textField name="firstName" class="field${hasErrors(bean:internshipInstance ,field:'companyTutor.user.firstName','error')}" value="${firstName}" />

                      </p>

                      <p>
                            <label for="lastName"><g:message code="companyTutor.lastName" /></label>
                            <g:textField name="lastName" class="field${hasErrors(bean:internshipInstance ,field:'companyTutor.user.lastName','error')}" value="${lastName}" />

                      </p>

                      <p>
                            <label for="email"><g:message code="companyTutor.email" /></label>
                            <g:textField name="email" class="field${hasErrors(bean:internshipInstance ,field:'companyTutor.user.email','error')}" value="${email}" />

                      </p>

                      <g:ifAnyGranted role="ROLE_ADMIN,ROLE_FORMATIONMANAGER">

                            <p>
                                  <label for="student"><g:message code="internship.student" default="Student" /></label>
                                  <g:select name="student.id" from="${student}" optionKey="id" optionValue="value" value="${internshipInstance?.student?.id}" noSelection="['null': '']" />

                            </p>

                            <p>
                                  <label for="academicTutor"><g:message code="internship.academicTutor" default="Academic Tutor" /></label>
                                  <g:select name="academicTutor.id" from="${staff}" optionKey="id" optionValue="value" value="${internshipInstance?.academicTutor?.id}" noSelection="['null': '']" />

                            </p>

                            <p>
                                  <label for="isApproval"><g:message code="internship.isApproval" default="Is Approval" /></label>
                                  <g:checkBox name="isApproval" value="${internshipInstance?.isApproval}" />

                            </p>

                      </g:ifAnyGranted>

                <div class="actionpad yui-skin-sam">
                   <g:buildCreateButtons />
                </div>

            </g:form>
          </gui:tab>
          <gui:tab label="${message(code: 'internship.createFromOffer')}">
                  <g:form class="boxed_form" name="offer_form" action="create">
                        <p>
                          <label for="offers"><g:message code="offers.list" default="Offers list" /></label>
                          <g:select name="offer.id" from="${offer}" optionKey="id" optionValue="value" noSelection="['null': '']" onChange="offer_form.submit()" />
                        </p>
                  </g:form>
          </gui:tab>
        </gui:tabView>
      </div>
      <script type="text/javascript">
        var handleSuccess = function(o){
          var messages = [];

          // Use the JSON Utility to parse the data returned from the server
          try {
            messages = YAHOO.lang.JSON.parse(o.responseText);
          }
          catch (x) {
            alert("JSON Parse failed!");
          return;
          }

          for (var i = 0, len = messages.length; i < len; ++i) {
                var m = messages[i];
                var input = YAHOO.util.Dom.get(m.key);
                var value = m.value;
                if(input)
                  input.value = value;
          }

        }

        var handleFailure = function(o){

        }

        function companySelectHandler(sType, aArgs) {
          var oMyAcInstance = aArgs[0];
          var elListItem = aArgs[1];
          var aData = aArgs[2];
          var id = aData[1];
          var sUrl = "${createLink(controller:'company', action:'getAddress')}/" + id;
          var request = YAHOO.util.Connect.asyncRequest('GET', sUrl, callback);
        };

        function initHandler () {
          GRAILSUI.companyName.itemSelectEvent.subscribe(companySelectHandler);
        };

        var callback =
        {
          success:handleSuccess,
          failure:handleFailure,
          argument: { foo:"foo", bar:"bar" }
        };

        YAHOO.util.Event.onDOMReady(initHandler);

      </script>
    </body>
</html>