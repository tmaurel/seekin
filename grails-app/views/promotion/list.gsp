
<%@ page import="me.hcl.seekin.Formation.Promotion" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="promotion.list" /></title>
        <gui:resources components="dataTable"/>
        <g:javascript src="datatable.js" />
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="promotion.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:buildListButtons />
		  <g:set var="idInternationalized" value="${message(code:'promotion.id')}" />
		  <g:set var="millesimeInternationalized" value="${message(code:'promotion.millesime')}" />
		  <g:set var="formationInternationalized" value="${message(code:'promotion.formation')}" />
          <gui:dataTable
              id="dt_2"
              draggableColumns="true"
              columnDefs="[
                            [key: 'id', sortable: true, resizeable: true, label: idInternationalized],
                            [key: 'formation', sortable: true, resizeable: true, label: formationInternationalized, formatter: 'customLinkFormatter'],
                            [key: 'millesime', sortable: true, resizeable: true, label: millesimeInternationalized, formatter: 'customLinkFormatter'],
							[key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
              ]"
              controller="promotion"
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
