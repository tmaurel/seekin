
<%@ page import="me.hcl.seekin.Auth.Role.Student" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="student.list" /></title>
        <gui:resources components="dataTable, tabView" />
        <g:javascript src="datatable.js" />
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="student.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:buildListButtons />
          
						<g:set var="firstNameInternationalized" value="${message(code:'user.firstName')}" />

						<g:set var="lastNameInternationalized" value="${message(code:'user.lastName')}" />
                 
                        <g:set var="emailInternationalized" value="${message(code:'user.email')}" />


		  <gui:tabView id="myTabView">
			<g:each var="formation" in="${promotions.formation}">
			<gui:tab label="${formation.label}" active="true">
			  <h2>${promotions.formation.label}</h2>
				<gui:dataTable
				  id="dt_${formation.id}"
				  draggableColumns="true"
				  columnDefs="[
								[key: 'lastName', sortable: true, resizeable: true, label: lastNameInternationalized],
								[key: 'firstName', sortable: true, resizeable: true, label: firstNameInternationalized],
								[key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
				  ]"
				  controller="student"
				  action="dataTableDataAsJSON"
				  paginatorConfig="[
					  template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
					  pageReportTemplate:'{totalRecords} ' + message(code:'list.total.records')
				  ]"
				  rowExpansion="false"
				  rowsPerPage="10"
				  sortedBy="lastName"
			  />
			</gui:tab>
			</g:each>
		  </gui:tabView>
        </div>
    </body>
</html>
