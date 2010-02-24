
<%@ page import="me.hcl.seekin.Internship.Internship" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="internship.list" /></title>
        <gui:resources components="dataTable, tabView"/>
        <g:javascript src="datatable.js" />
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="internship.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:buildListButtons />
          
                        <g:set var="idInternationalized" value="${message(code:'internship.id')}" />
                 
                        <g:set var="subjectInternationalized" value="${message(code:'internship.subject')}" />
                 
                        <g:set var="beginAtInternationalized" value="${message(code:'internship.beginAt')}" />
                 
                        <g:set var="isApprovalInternationalized" value="${message(code:'internship.isApproval')}" />
                 
                        <g:set var="reportInternationalized" value="${message(code:'internship.report')}" />
                 
                        <g:set var="studentInternationalized" value="${message(code:'internship.student')}" />


           <gui:tabView id="myTabView">
			<g:each var="status" in="${status}" status="i">
			<g:set var="idStatus" value="${status}" />
			<gui:tab id="${idStatus}" label="${message(code:idStatus)}" active="${(i==0)? 1:0}">
			  <h2><g:message code="${idStatus}" /></h2>
                <g:ifAnyGranted role="ROLE_ADMIN,ROLE_MANAGERFORMATION">
                  <g:if test="${idStatus=='internship.waitForValidation'}">
                    <g:form action="list">
                       <gui:dataTable
                            id="dt_${i}"
                            draggableColumns="true"
                            columnDefs="[

                                          [key: 'id', sortable: true, resizeable: true, label: idInternationalized],

                                          [key: 'subject', sortable: true, resizeable: true, label: subjectInternationalized],

                                          [key: 'beginAt', sortable: true, resizeable: true, label: beginAtInternationalized],

                                          [key: 'isApproval', sortable: true, resizeable: true, label: isApprovalInternationalized],

                                          [key: 'report', sortable: true, resizeable: true, label: reportInternationalized, formatter: 'customLinkFormatter'],

                                          [key: 'student', sortable: true, resizeable: true, label: studentInternationalized, formatter: 'customLinkFormatter'],

                                [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'validatePanelFormatter']
                            ]"
                            controller="internship"
                            action="dataTableDataAsJSON"
                            paginatorConfig="[
                                template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                                pageReportTemplate:'{totalRecords} ' + message(code:'list.total.records')
                            ]"
                            rowExpansion="false"
                            rowsPerPage="10"
                            params="[status: idStatus]"
                        />
                      <g:YUISubmitbutton action="list" value="validate" />
                    </g:form>
                  </g:if>
                  <g:else>
                       <gui:dataTable
                            id="dt_${i}"
                            draggableColumns="true"
                            columnDefs="[

                                          [key: 'id', sortable: true, resizeable: true, label: idInternationalized],

                                          [key: 'subject', sortable: true, resizeable: true, label: subjectInternationalized],

                                          [key: 'beginAt', sortable: true, resizeable: true, label: beginAtInternationalized],

                                          [key: 'isApproval', sortable: true, resizeable: true, label: isApprovalInternationalized],

                                          [key: 'report', sortable: true, resizeable: true, label: reportInternationalized, formatter: 'customLinkFormatter'],

                                          [key: 'student', sortable: true, resizeable: true, label: studentInternationalized, formatter: 'customLinkFormatter'],

                                [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
                            ]"
                            controller="internship"
                            action="dataTableDataAsJSON"
                            paginatorConfig="[
                                template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                                pageReportTemplate:'{totalRecords} ' + message(code:'list.total.records')
                            ]"
                            rowExpansion="false"
                            rowsPerPage="10"
                            params="[status: idStatus]"
                        />
                  </g:else>
              </g:ifAnyGranted>
              <g:ifNotGranted role="ROLE_ADMIN,ROLE_MANAGERFORMATION">
                       <gui:dataTable
                            id="dt_${i}"
                            draggableColumns="true"
                            columnDefs="[

                                          [key: 'id', sortable: true, resizeable: true, label: idInternationalized],

                                          [key: 'subject', sortable: true, resizeable: true, label: subjectInternationalized],

                                          [key: 'beginAt', sortable: true, resizeable: true, label: beginAtInternationalized],

                                          [key: 'isApproval', sortable: true, resizeable: true, label: isApprovalInternationalized],

                                          [key: 'report', sortable: true, resizeable: true, label: reportInternationalized, formatter: 'customLinkFormatter'],

                                          [key: 'student', sortable: true, resizeable: true, label: studentInternationalized, formatter: 'customLinkFormatter'],

                                [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
                            ]"
                            controller="internship"
                            action="dataTableDataAsJSON"
                            paginatorConfig="[
                                template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                                pageReportTemplate:'{totalRecords} ' + message(code:'list.total.records')
                            ]"
                            rowExpansion="false"
                            rowsPerPage="10"
                            params="[status: idStatus]"
                        />
              </g:ifNotGranted>
        </gui:tab>
        </g:each>
      </gui:tabView>

        </div>
    </body>
</html>
