
<%@ page import="me.hcl.seekin.Ressource.EducationalDoc" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="educationalDoc.list" /></title>
        
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="educationalDoc.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:ifAnyGranted role="ROLE_ADMIN,ROLE_FORMATIONMANAGER">
            <g:buildListButtons />
          </g:ifAnyGranted>
          
          <g:set var="idInternationalized" value="${message(code:'educationalDoc.id')}" />

          <g:set var="titleInternationalized" value="${message(code:'educationalDoc.title')}" />

          <g:set var="uriInternationalized" value="${message(code:'educationalDoc.uri')}" />

          <gui:tabView id="myTabView">
            <g:each var="formation" in="${formations}" status="i">
            <g:set var="idFormation" value="${formation?.id}" />
            <gui:tab id="${formation.id}" label="${formation.label}" active="${(i==0)? 1:0}">
              <h2>${formation?.label}</h2>
              <gui:dataTable
                  id="dt${formation.id}"
                  draggableColumns="true"
                  columnDefs="[
                      [key: 'title', minWidth:600, sortable: true, resizeable: true, label: titleInternationalized],
                      [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
                  ]"
                  sortedBy="title"
                  controller="educationalDoc"
                  action="dataTableDataAsJSON"
                  paginatorConfig="[
                      template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                      pageReportTemplate:'{totalRecords} ' + message(code:'list.total.records')
                  ]"
                  rowExpansion="false"
                  rowsPerPage="10"
                  params="[formation:idFormation]"
              />
            </gui:tab>
            </g:each>
          </gui:tabView>
        </div>
    </body>
</html>
