
<%@ page import="me.hcl.seekin.Internship.Convocation" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="convocation.list" /></title>
        <gui:resources components="dataTable, tabView"/>
        <g:javascript src="datatable.js" />
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="convocation.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:buildListButtons />

		  <br /><br />
          
		  <g:set var="idInternationalized" value="${message(code:'convocation.id')}" />
		  <g:set var="dateInternationalized" value="${message(code:'convocation.date')}" />
		  <g:set var="buildingInternationalized" value="${message(code:'convocation.building')}" />
		  <g:set var="roomInternationalized" value="${message(code:'convocation.room')}" />
		  <g:set var="internshipInternationalized" value="${message(code:'convocation.internship')}" />
		  <g:set var="millesimeInternationalized" value="${message(code:'millesime')}" />

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
				  id="dt${promotion.id}"
				  draggableColumns="true"
				  columnDefs="[
                            [key: 'id', sortable: true, resizeable: true, label: idInternationalized],
                            [key: 'date', sortable: true, resizeable: true, label: dateInternationalized],
                            [key: 'building', sortable: true, resizeable: true, label: buildingInternationalized],
                            [key: 'room', sortable: true, resizeable: true, label: roomInternationalized],
                            [key: 'internship', sortable: true, resizeable: true, label: internshipInternationalized],
							[key: 'pdf', sortable: false, resizeable: false, label:'Pdf', formatter: 'customLinkFormatter'],
							[key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
				  ]"
				  controller="convocation"
				  action="dataTableDataAsJSON"
				  paginatorConfig="[
					  template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
					  pageReportTemplate:'{totalRecords} ' + message(code:'list.total.records')
				  ]"
				  rowExpansion="false"
				  rowsPerPage="10"
				  params="[promotion:idPromotion]"
			  />
			<g:link action="pdfGeneratePlanning" id="${promotion.id}"><g:message code="convocation.generate.planning" /></g:link>
			</gui:tab>
			</g:each>
		  </gui:tabView>
        </div>
    </body>
</html>
