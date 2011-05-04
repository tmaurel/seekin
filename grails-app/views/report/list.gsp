
<%@ page import="me.hcl.seekin.Internship.Report" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="report.list" /></title>
        
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="report.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:buildListButtons />
          
                        <g:set var="idInternationalized" value="${message(code:'report.id')}" />
                 
                        <g:set var="titleInternationalized" value="${message(code:'report.title')}" />
                 
                        <g:set var="fileDataInternationalized" value="${message(code:'report.fileData')}" />
                 
                        <g:set var="isPrivateInternationalized" value="${message(code:'report.isPrivate')}" />
                 
                        <g:set var="internshipInternationalized" value="${message(code:'report.internship')}" />

          <br /><br />
          <g:filterPanel id="dt_2" filters="[
                  [name: titleInternationalized, field: 'title']
          ]" />
          <br />
          <gui:dataTable
              id="dt_2"
              draggableColumns="true"
              columnDefs="[
                            [key: 'title', sortable: true, resizeable: true, minWidth:400, label: titleInternationalized],
                            [key: 'internship', sortable: true, resizeable: true, label: internshipInternationalized, formatter: 'customLinkFormatter'],
                     
                  [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
              ]"
              controller="report"
              action="dataTableDataAsJSON"
              paginatorConfig="[
                  template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                  pageReportTemplate:'{totalRecords} ' + message(code:'list.total.records')
              ]"
              rowExpansion="false"
              rowsPerPage="10"
              sortedBy="title"
          />

        </div>
    </body>
</html>
