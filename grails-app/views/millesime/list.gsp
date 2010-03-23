
<%@ page import="me.hcl.seekin.Formation.Millesime" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="millesime.list" /></title>
        
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="millesime.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:ifAnyGranted role="ROLE_ADMIN">
            <g:buildListButtons />
          </g:ifAnyGranted>
          
                        <g:set var="idInternationalized" value="${message(code:'millesime.id')}" />
                 
                        <g:set var="beginDateInternationalized" value="${message(code:'millesime.beginDate')}" />
                 
                        <g:set var="endDateInternationalized" value="${message(code:'millesime.endDate')}" />
                 
                        <g:set var="currentInternationalized" value="${message(code:'millesime.current')}" />
                 
          <gui:dataTable
              id="dt_2"
              draggableColumns="true"
              columnDefs="[
                  
                            [key: 'id', sortable: true, resizeable: true, label: idInternationalized],
                     
                            [key: 'beginDate', sortable: true, resizeable: true, label: beginDateInternationalized],
                     
                            [key: 'endDate', sortable: true, resizeable: true, label: endDateInternationalized],
                     
                            [key: 'current', sortable: true, resizeable: true, label: currentInternationalized],
                     
                  [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
              ]"
              controller="millesime"
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
