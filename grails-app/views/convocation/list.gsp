
<%@ page import="me.hcl.seekin.Internship.Convocation" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="convocation.list" /></title>
        <gui:resources components="dataTable"/>
        <g:javascript src="datatable.js" />
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="convocation.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:buildListButtons />
          
                        <g:set var="idInternationalized" value="${message(code:'convocation.id')}" />
                 
                        <g:set var="dateInternationalized" value="${message(code:'convocation.date')}" />
                 
                        <g:set var="buildingInternationalized" value="${message(code:'convocation.building')}" />
                 
                        <g:set var="roomInternationalized" value="${message(code:'convocation.room')}" />
                 
                        <g:set var="internshipInternationalized" value="${message(code:'convocation.internship')}" />
                 
          <gui:dataTable
              id="dt_2"
              draggableColumns="true"
              columnDefs="[
                  
                            [key: 'id', sortable: true, resizeable: true, label: idInternationalized],
                     
                            [key: 'date', sortable: true, resizeable: true, label: dateInternationalized],
                     
                            [key: 'building', sortable: true, resizeable: true, label: buildingInternationalized],
                     
                            [key: 'room', sortable: true, resizeable: true, label: roomInternationalized],
                     
                            [key: 'internship', sortable: true, resizeable: true, label: internshipInternationalized],
                     
                  [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
              ]"
              controller="convocation"
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
