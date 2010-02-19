
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
		
		  <g:set var="firstNameInternationalized" value="${message(code:'user.firstName')}" />
		  <g:set var="lastNameInternationalized" value="${message(code:'user.lastName')}" />
		  <g:set var="emailInternationalized" value="${message(code:'user.email')}" />
		  <g:set var="millesimeInternationalized" value="${message(code:'millesime')}" />
		  <g:set var="activedTab" value="" />
		  
		  <g:form name="millesime_form" action="list">
			<g:select name="idMillesime"
					  from="${millesimes}"
					  optionKey="id"
					  onChange="millesime_form.submit()"
					  noSelection="['null': millesimeInternationalized]"
 			/>
		  </g:form>
		  
		  <br />
		  
		  <gui:tabView id="myTabView">
			<g:each var="promotion" in="${promotions}" status="i">
			<g:set var="idPromotion" value="${promotion?.id}" />
			<gui:tab id="${promotion.id}" label="${promotion.formation.label}" active="${(i==0)? 1:0}">
			  <h2>${promotion?.formation?.label} (${promotion?.millesime})</h2>
				<gui:dataTable
				  id="dt_${promotion.id}"
				  draggableColumns="true"
				  columnDefs="[
								[key: 'lastName', sortable: true, resizeable: true, label: lastNameInternationalized],
								[key: 'firstName', sortable: true, resizeable: true, label: firstNameInternationalized],
								[key: 'email', sortable: true, resizeable: true, label: emailInternationalized],
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
				  params="[promotion:idPromotion, millesime:idMillesime]"
			  />
			</gui:tab>
			</g:each>
		  </gui:tabView>
    </body>
</html>
