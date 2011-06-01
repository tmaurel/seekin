
<%@ page import="me.hcl.seekin.Auth.User" %>
<g:javascript library="yui" />
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="user.list" /></title>
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="user.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
      <g:buildListButtons />
      <g:JQueryLinkbutton action="validate" value="validate" />
      <g:set var="idInternationalized" value="${message(code:'user.id')}" />
      <g:set var="emailInternationalized" value="${message(code:'user.email')}" />
      <g:set var="roleInternationalized" value="${message(code:'user.role')}" />
      <g:set var="enabledInternationalized" value="${message(code:'user.enabled')}" />
      <g:set var="firstNameInternationalized" value="${message(code:'user.firstName')}" />
      <g:set var="lastNameInternationalized" value="${message(code:'user.lastName')}" />
        <g:filterPanel id="dt_2" filters="[
                [name: firstNameInternationalized, field: 'firstName'],
                [name: lastNameInternationalized, field: 'lastName'],
        ]" />
        <br />
          <gui:dataTable
              id="dt_2"
              draggableColumns="true"
              columnDefs="[
                            [key: 'id', sortable: true, resizeable: true, label: idInternationalized],
                            [key: 'email', sortable: true, resizeable: true, label: emailInternationalized],
                            [key: 'firstName', sortable: true, resizeable: true, label: firstNameInternationalized],
                            [key: 'lastName', sortable: true, resizeable: true, label: lastNameInternationalized],
                            [key: 'roles', sortable: false, resizeable: true, label: roleInternationalized],
                            [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
              ]"
              controller="user"
              action="dataTableDataAsJSON"
              paginatorConfig="[
                  template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                  pageReportTemplate:'{totalRecords} ' + message(code:'list.total.records')
              ]"
              rowExpansion="false"
              rowsPerPage="3"
          />

        </div>
    </body>
</html>
