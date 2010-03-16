
<%@ page import="me.hcl.seekin.Auth.Role.Staff" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="staff.list" /></title>
        
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="staff.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:buildListButtons />
          
                        <g:set var="idInternationalized" value="${message(code:'staff.id')}" />
                 
                        <g:set var="userInternationalized" value="${message(code:'staff.user')}" />
                 
                        <g:set var="authorityInternationalized" value="${message(code:'staff.authority')}" />
                 
                        <g:set var="roleNameInternationalized" value="${message(code:'staff.roleName')}" />
                 
          <gui:dataTable
              id="dt_2"
              draggableColumns="true"
              columnDefs="[
                  
                            [key: 'id', sortable: true, resizeable: true, label: idInternationalized],
                     
                            [key: 'user', sortable: true, resizeable: true, label: userInternationalized, formatter: 'customLinkFormatter'],
                     
                            [key: 'authority', sortable: true, resizeable: true, label: authorityInternationalized],
                     
                            [key: 'roleName', sortable: true, resizeable: true, label: roleNameInternationalized],
                     
                  [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
              ]"
              controller="staff"
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
