
<%@ page import="me.hcl.seekin.Internship.Offer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="offer.list" /></title>
        <gui:resources components="dataTable"/>
        <g:javascript src="datatable.js" />
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="offer.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:buildListButtons />
          
                        <g:set var="idInternationalized" value="${message(code:'offer.id')}" />
                 
                        <g:set var="subjectInternationalized" value="${message(code:'offer.subject')}" />
                 
                        <g:set var="descriptionInternationalized" value="${message(code:'offer.description')}" />
                 
                        <g:set var="beginAtInternationalized" value="${message(code:'offer.beginAt')}" />
                 
                        <g:set var="lengthInternationalized" value="${message(code:'offer.length')}" />
                 
                        <g:set var="statusInternationalized" value="${message(code:'offer.status')}" />
                 
          <gui:dataTable
              id="dt_2"
              draggableColumns="true"
              columnDefs="[
                  
                            [key: 'id', sortable: true, resizeable: true, label: idInternationalized],
                     
                            [key: 'subject', sortable: true, resizeable: true, label: subjectInternationalized],
                     
                            [key: 'description', sortable: true, resizeable: true, label: descriptionInternationalized],
                     
                            [key: 'beginAt', sortable: true, resizeable: true, label: beginAtInternationalized],
                     
                            [key: 'length', sortable: true, resizeable: true, label: lengthInternationalized],
                     
                            [key: 'status', sortable: true, resizeable: true, label: statusInternationalized],
                     
                  [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
              ]"
              controller="offer"
              action="dataTableDataAsJSON"
              paginatorConfig="[
                  template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                  pageReportTemplate:'{totalRecords} ' + message(code:'list.total.records')
              ]"
              rowExpansion="false"
              rowsPerPage="10"
          />

        </div>
    </body>
</html>
