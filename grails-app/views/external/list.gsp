
<%@ page import="me.hcl.seekin.Auth.Role.External" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="external.list" /></title>
        <gui:resources components="dataTable"/>
        <g:javascript src="datatable.js" />
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="external.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:buildListButtons />
          
                        <g:set var="idInternationalized" value="${message(code:'external.id')}" />
                 
                        <g:set var="userInternationalized" value="${message(code:'external.user')}" />
                 
                        <g:set var="companyInternationalized" value="${message(code:'external.company')}" />
                 
                        <g:set var="formerStudentInternationalized" value="${message(code:'external.formerStudent')}" />
                 
                        <g:set var="authorityInternationalized" value="${message(code:'external.authority')}" />
                 
                        <g:set var="roleNameInternationalized" value="${message(code:'external.roleName')}" />
                 
          <gui:dataTable
              id="dt_2"
              draggableColumns="true"
              columnDefs="[
                  
                            [key: 'id', sortable: true, resizeable: true, label: idInternationalized],
                     
                            [key: 'user', sortable: true, resizeable: true, label: userInternationalized, formatter: 'customLinkFormatter'],
                     
                            [key: 'company', sortable: true, resizeable: true, label: companyInternationalized, formatter: 'customLinkFormatter'],
                     
                            [key: 'formerStudent', sortable: true, resizeable: true, label: formerStudentInternationalized],
                     
                            [key: 'authority', sortable: true, resizeable: true, label: authorityInternationalized],
                     
                            [key: 'roleName', sortable: true, resizeable: true, label: roleNameInternationalized],
                     
                  [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
              ]"
              controller="external"
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
