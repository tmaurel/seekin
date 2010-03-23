
<%@ page import="me.hcl.seekin.Ressource.Link" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="link.list" /></title>
        
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="link.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:ifAnyGranted role="ROLE_ADMIN,ROLE_FORMATIONMANAGER">
            <g:buildListButtons />
          </g:ifAnyGranted>
        
                        <g:set var="idInternationalized" value="${message(code:'link.id')}" />
                 
                        <g:set var="titleInternationalized" value="${message(code:'link.title')}" />
                 
                        <g:set var="urlInternationalized" value="${message(code:'link.url')}" />
                 
                        <g:set var="descriptionInternationalized" value="${message(code:'link.description')}" />
                 
          <gui:dataTable
              id="dt_2"
              draggableColumns="true"
              columnDefs="[
                            [key: 'title', sortable: true, resizeable: true, label: titleInternationalized],
                            [key: 'url', sortable: true, resizeable: true, label: urlInternationalized, formatter: 'link'],
                     
                  [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
              ]"
              controller="link"
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
