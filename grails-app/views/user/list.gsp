
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
	<script type="text/javascript">
        	YAHOO.util.Event.onDOMReady(function () { 
        	  
            updateFilter  = function () {
                
              GRAILSUI.dt_2.customQueryString = 
                "lastName="+YAHOO.util.Dom.get("filter").value; 

              var state = GRAILSUI.dt_2.getState(); 
              state.sorting = state.sortedBy;
              state.pagination.recordOffset = 0; 
              query = GRAILSUI.dt_2.buildQueryString(state); 
      
              GRAILSUI.dt_2.getDataSource().sendRequest(query,{ 
                success : GRAILSUI.dt_2.onDataReturnReplaceRows, 
                failure : GRAILSUI.dt_2.onDataReturnReplaceRows, 
                scope   : GRAILSUI.dt_2, 
                argument: state 
              }); 
            }; 
      
            YAHOO.util.Event.on('filter','keyup',function (e) {
            	updateFilter(); 
			}); 
          }); 
	</script>
          <g:buildListButtons />
          <g:YUILinkbutton action="validate" value="validate" />
                        <g:set var="idInternationalized" value="${message(code:'user.id')}" />
                        <g:set var="emailInternationalized" value="${message(code:'user.email')}" />
                        <g:set var="roleInternationalized" value="${message(code:'user.role')}" />
                        <g:set var="enabledInternationalized" value="${message(code:'user.enabled')}" />
                        <g:set var="firstNameInternationalized" value="${message(code:'user.firstName')}" />
                        <g:set var="lastNameInternationalized" value="${message(code:'user.lastName')}" />
        <br /><br />         
		<div class="markup">
			<label for="filter"><g:message code="user.filter.lastname" /></label> <input type="text" id="filter" value="">
			<div id="tbl"></div>
		</div>
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
              rowsPerPage="10"
          />

        </div>
    </body>
</html>
