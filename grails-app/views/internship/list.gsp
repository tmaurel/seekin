
<%@ page import="me.hcl.seekin.Internship.Internship" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="internship.list" /></title>
        <gui:resources components="dataTable"/>
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
          
                        <g:set var="idInternationalized" value="${message(code:'internship.id.label')}" />
                 
                        <g:set var="subjectInternationalized" value="${message(code:'internship.subject.label')}" />
                 
                        <g:set var="beginAtInternationalized" value="${message(code:'internship.beginAt.label')}" />
                 
                        <g:set var="isApprovalInternationalized" value="${message(code:'internship.isApproval.label')}" />
                 
                        <g:set var="reportInternationalized" value="${message(code:'internship.report.label')}" />
                 
                        <g:set var="studentInternationalized" value="${message(code:'internship.student.label')}" />
                 
          <gui:dataTable
              id="dt_2"
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
                  pageReportTemplate:'{totalRecords} total records'
              ]"
              rowExpansion="false"
              rowsPerPage="10"
          />

        </div>
    </body>
</html>
