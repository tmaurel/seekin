
<%@ page import="me.hcl.seekin.Internship.Offer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="offer.list" /></title>
        <gui:resources components="dataTable, tabView"/>
        <g:javascript src="datatable.js" />
        <g:YUIButtonRessource />
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="offer.list" /></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
        <g:ifNotGranted role="ROLE_STUDENT">
          <g:buildListButtons />
        </g:ifNotGranted>
          
                        <g:set var="idInternationalized" value="${message(code:'offer.id')}" />
                 
                        <g:set var="subjectInternationalized" value="${message(code:'offer.subject')}" />
                 
                        <g:set var="beginAtInternationalized" value="${message(code:'offer.beginAt')}" />
                 
                        <g:set var="lengthInternationalized" value="${message(code:'offer.length')}" />

                        <g:set var="companyInternationalized" value="${message(code:'offer.company')}" />

         <gui:tabView id="myTabView">
			<g:each var="status" in="${status}" status="i">
			<g:set var="idStatus" value="${status}" />
			<gui:tab id="${idStatus}" label="${message(code:idStatus)}" active="${(i==0)? 1:0}">
			  <h3><g:message code="${idStatus}" /></h3>
                <g:ifAnyGranted role="ROLE_ADMIN,ROLE_FORMATIONMANAGER">
                  <g:if test="${idStatus=='offer.waitForValidation'}">
                    <g:form action="list">
                      <gui:dataTable
                        id="dt${i}"
                        draggableColumns="true"
                        columnDefs="[

                                      [key: 'id', sortable: true, resizeable: true, label: idInternationalized],

                                      [key: 'subject', sortable: true, resizeable: true, label: subjectInternationalized],

                                      [key: 'company', sortable: true, resizeable: true, label: companyInternationalized],

                                      [key: 'beginAt', sortable: true, resizeable: true, label: beginAtInternationalized],

                                      [key: 'length', sortable: true, resizeable: true, label: lengthInternationalized],

                            [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'validatePanelFormatter']
                        ]"
                        controller="offer"
                        action="dataTableDataAsJSON"
                        paginatorConfig="[
                            template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                            pageReportTemplate:'{totalRecords} ' + message(code:'list.total.records')
                        ]"
                        rowExpansion="false"
                        rowsPerPage="10"
                        params="[status: idStatus]"
                      />
                      <g:YUISubmitbutton action="list" value="validate" />
                    </g:form>
                  </g:if>
                  <g:else>
                    <gui:dataTable
                      id="dt${i}"
                      draggableColumns="true"
                      columnDefs="[

                                    [key: 'id', sortable: true, resizeable: true, label: idInternationalized],

                                    [key: 'subject', sortable: true, resizeable: true, label: subjectInternationalized],

                                    [key: 'company', sortable: true, resizeable: true, label: companyInternationalized],

                                    [key: 'beginAt', sortable: true, resizeable: true, label: beginAtInternationalized],

                                    [key: 'length', sortable: true, resizeable: true, label: lengthInternationalized],

                          [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
                      ]"
                      controller="offer"
                      action="dataTableDataAsJSON"
                      paginatorConfig="[
                          template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                          pageReportTemplate:'{totalRecords} ' + message(code:'list.total.records')
                      ]"
                      rowExpansion="false"
                      rowsPerPage="10"
                      params="[status: idStatus]"
                  />
                  </g:else>
                </g:ifAnyGranted>
                <g:ifNotGranted role="ROLE_ADMIN,ROLE_FORMATIONMANAGER">
                  <gui:dataTable
                      id="dt${i}"
                      draggableColumns="true"
                      columnDefs="[

                                    [key: 'id', sortable: true, resizeable: true, label: idInternationalized],

                                    [key: 'subject', sortable: true, resizeable: true, label: subjectInternationalized],

                                    [key: 'company', sortable: true, resizeable: true, label: companyInternationalized],

                                    [key: 'beginAt', sortable: true, resizeable: true, label: beginAtInternationalized],

                                    [key: 'length', sortable: true, resizeable: true, label: lengthInternationalized],

                          [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
                      ]"
                      controller="offer"
                      action="dataTableDataAsJSON"
                      paginatorConfig="[
                          template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                          pageReportTemplate:'{totalRecords} ' + message(code:'list.total.records')
                      ]"
                      rowExpansion="false"
                      rowsPerPage="10"
                      params="[status: idStatus]"
                  />
                </g:ifNotGranted>
			</gui:tab>
			</g:each>
		  </gui:tabView>

        </div>
    </body>
</html>
