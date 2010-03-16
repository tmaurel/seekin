<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title><g:message code="student.table" /></title>
        <gui:resources components="accordion"/>
        <g:javascript library="yui" />
        <gui:resources components="dataTable"/>
        
        <style type="text/css">

          .left_menu {
            line-height: 33px;
          }

        </style>
    </head>
    <body>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" /></div>
      </g:if>
      <g:set var="millesimeInternationalized" value="${message(code:'millesime')}" />
      <g:form name="buildTable" class="yui-skin-sam left_menu">
        <gui:expandablePanel title="${message(code:'student.table.projections')}" expanded="true" bounce="false">

          <g:each var="projection" in="${projections}">
            <input type="checkbox" id="${projection}" name="${projection}" value="on"/>
          </g:each>
          <g:select name="idMillesime"
                              from="${millesimes}"
                              optionKey="id"
                              onChange="refreshDataSource()"
            />
          <input id="submit" type="submit" value="submit" style="display:none;"/>
        </gui:expandablePanel>
      </g:form>
      <div id="dataTable" class="yui-skin-sam">
        <gui:dataTable
              id="dt_fm"
              draggableColumns="true"
              columnDefs="${columnDefs}"
              controller="student"
              action="tableAsJSON"
              rowExpansion="false"
              paginatorConfig="[
                  template:'',
                  pageReportTemplate:''
              ]"
              sortedBy="student_id"

          />

      </div>

       <script type="text/javascript">
          function refreshDataSource()
          {
              var millesime = YAHOO.util.Dom.get('idMillesime');

              var oCallback = {
                  success : GRAILSUI.dt_fm.onDataReturnInitializeTable,
                  failure : GRAILSUI.dt_fm.onDataReturnInitializeTable,
                  scope : GRAILSUI.dt_fm,
                  argument: GRAILSUI.dt_fm.getState() // data payload that will be returned to the callback function
              };

              GRAILSUI.dt_fm.getDataSource().sendRequest(
                "sort=${columnDefs[0]['key']}&order=ASC&millesime=" + millesime.value,
                oCallback
              );
          }


          function onCheckedChange(p_oEvent, projection) {
            var obj = YAHOO.util.Dom.get(projection);
            var column = GRAILSUI.dt_fm.getColumn(projection.replace(/\./g, "_"));

            if(p_oEvent.newValue)
              GRAILSUI.dt_fm.showColumn(column);
            else
              GRAILSUI.dt_fm.hideColumn(column);
          }

          function hideColumns() {
            <g:each var="column" in="${columnDefs}">
               GRAILSUI.dt_fm.hideColumn(GRAILSUI.dt_fm.getColumn("${column.key}"));
            </g:each>

          }

          <g:each var="projection" in="${projections}">
            new YAHOO.widget.Button("${projection}", { label:"${message(code:projection)}" }).on("checkedChange", onCheckedChange, "${projection}");
          </g:each>

          YAHOO.util.Event.onDOMReady(hideColumns);


          var managerTableFormatter = function(elLiner, oRecord, oColumn, oData) {
              var val = oData
              if(typeof(val) != "object")
                elLiner.innerHTML = val;
          };


          YAHOO.widget.DataTable.Formatter.managerTableFormatter = managerTableFormatter;

      </script>

    </body>
</html>
