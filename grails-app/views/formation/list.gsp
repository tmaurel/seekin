
<%@ page import="me.hcl.seekin.Formation.Formation" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="formation.list" /></title>
        
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="formation.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:ifAnyGranted role="ROLE_ADMIN">
            <g:buildListButtons />
          </g:ifAnyGranted>
          
                        <g:set var="idInternationalized" value="${message(code:'formation.id')}" />
                 
                        <g:set var="labelInternationalized" value="${message(code:'formation.label')}" />
                 
                        <g:set var="descriptionInternationalized" value="${message(code:'formation.description')}" />
                 
          <gui:dataTable
              id="dt_2"
              draggableColumns="true"
              columnDefs="[
                  
                            [key: 'id', sortable: true, resizeable: true, label: idInternationalized],
                     
                            [key: 'label', sortable: true, resizeable: true, label: labelInternationalized],
                     
                            [key: 'description', sortable: true, resizeable: true, label: descriptionInternationalized],
                     
                  [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
              ]"
              controller="formation"
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
