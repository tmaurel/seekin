<% import org.codehaus.groovy.grails.orm.hibernate.support.ClosureEventTriggeringInterceptor as Events %>
<%=packageName%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="${domainClass.propertyName}.list" /></title>
        <gui:resources components="dataTable"/>
        <g:javascript src="datatable.js" />
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="${domainClass.propertyName}.list" /></h2>
      <g:if test="\${flash.message}">
      <div class="flash_message"><g:message code="\${flash.message}" args="\${flash.args}" default="\${flash.defaultMessage}" /></div>
      </g:if>
      <div class="yui-skin-sam" id="crud_panel">
          <g:buildListButtons />
          <gui:dataTable
              id="dt_2"
              draggableColumns="true"
              columnDefs="[
                  <%  excludedProps = ["version",
                                 Events.ONLOAD_EVENT,
                                 Events.BEFORE_INSERT_EVENT,
                                 Events.BEFORE_UPDATE_EVENT,
                                 Events.BEFORE_DELETE_EVENT]
                props = domainClass.properties.findAll { !excludedProps.contains(it.name) && it.type != Set.class }
                Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                size = props.size()
                props.eachWithIndex
                { p, i ->
                    if (i < 6)
                    {
                        if (p.isAssociation())
                        { %>
                            [key: '${p.name}', sortable: true, resizeable: true, label: '${p.naturalName}', formatter: 'customLinkFormatter'],
                     <% }
                        else
                        { %>
                            [key: '${p.name}', sortable: true, resizeable: true, label: '${p.naturalName}'],
                     <% }
                    }

               }  %>
                  [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'adminPanelFormatter']
              ]"
              controller="${domainClass.propertyName}"
              action="dataTableDataAsJSON"
              paginatorConfig="[
                  template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                  pageReportTemplate:'{totalRecords} total records'
              ]"
              rowExpansion="false"
              rowsPerPage="10"
          />

        </div>
    </body>
</html>
