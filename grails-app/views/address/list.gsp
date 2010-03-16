
<%@ page import="me.hcl.seekin.Util.Address" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="address.list" /></title>
        
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="address.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:buildListButtons />
          
                        <g:set var="idInternationalized" value="${message(code:'address.id')}" />
                 
                        <g:set var="streetInternationalized" value="${message(code:'address.street')}" />
                 
                        <g:set var="zipCodeInternationalized" value="${message(code:'address.zipCode')}" />
                 
                        <g:set var="townInternationalized" value="${message(code:'address.town')}" />
                 
          <gui:dataTable
              id="dt_2"
              draggableColumns="true"
              columnDefs="[
                  
                            [key: 'id', sortable: true, resizeable: true, label: idInternationalized],
                     
                            [key: 'street', sortable: true, resizeable: true, label: streetInternationalized],
                     
                            [key: 'zipCode', sortable: true, resizeable: true, label: zipCodeInternationalized],
                     
                            [key: 'town', sortable: true, resizeable: true, label: townInternationalized],
                     
                  [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
              ]"
              controller="address"
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
