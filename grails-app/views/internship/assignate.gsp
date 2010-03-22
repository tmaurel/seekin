
<%@ page import="me.hcl.seekin.Auth.Role.Staff; me.hcl.seekin.Internship.Internship" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="intership.assignate" /></title>
        <g:javascript src="datatable.js" />

        <tm:resources />
    </head>
    <body>
      <h2><g:message code="intership.assignate" /></h2>

      <script type = "text/javascript">

        var assignPanelFormatter = function(elLiner, oRecord, oColumn, oData) {
          var id = oData;
          new Ajax.Request("${createLink(action:'getAcademicTutorList')}", {
                method: 'POST',
                postBody: "id=" + id,
                onSuccess: function(response) {
                   elLiner.innerHTML += response.responseText
                   elLiner.innerHTML += " <a href=\"show/" + id + "\"><img src=\"../images/icons/show.png\" /></a>";
                }
             });
        };

        YAHOO.widget.DataTable.Formatter.assignPanelFormatter = assignPanelFormatter;
      </script>


      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">

                        <g:set var="IdInternationalized" value="${message(code:'internship.id')}" />

                        <g:set var="subjectInternationalized" value="${message(code:'internship.subject')}" />

                        <g:set var="studentInternationalized" value="${message(code:'internship.student')}" />


          <g:form action="assignate">
          <gui:tabView id="myTabView">
            <g:each var="formation" in="${formations}" status="i">
              <g:set var="idFormation" value="${formation?.id}" />
              <gui:tab id="${formation.id}" label="${formation.label}" active="${(i==0)? 1:0}">
              <h2>${formation?.label}</h2>
              
                <g:hiddenField name="nameFormation" value="${idFormation}" />
                <gui:dataTable
                    id="dt_${idFormation}"
                    draggableColumns="true"
                    columnDefs="[

                                  [key: 'id', sortable: true, resizeable: true, label: IdInternationalized],

                                  [key: 'subject', sortable: true, resizeable: true, label: subjectInternationalized],

                                  [key: 'student', sortable: true, resizeable: true, label: studentInternationalized, formatter: 'customLinkFormatter'],

                        [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'assignPanelFormatter']
                    ]"
                    controller="internship"
                    action="dataTableAsJSON"
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
          <g:YUISubmitbutton action="assignate" value="validate" />
          </g:form>
        </div>
    </body>
</html>
