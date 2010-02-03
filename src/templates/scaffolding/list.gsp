<% import org.codehaus.groovy.grails.orm.hibernate.support.ClosureEventTriggeringInterceptor as Events %>
<%=packageName%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="${domainClass.propertyName}.list" default="${className} List" /></title>
        <gui:resources components="dataTable"/>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="\${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="${domainClass.propertyName}.new" default="New ${className}" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="${domainClass.propertyName}.list" default="${className} List" /></h1>
            <g:if test="\${flash.message}">
            <div class="message"><g:message code="\${flash.message}" args="\${flash.args}" default="\${flash.defaultMessage}" /></div>
            </g:if>
            <div class="yui-skin-sam">
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
                                  [key:'${domainClass.propertyName}.${p.name}', label:'${p.naturalName}']
                           <% }
                              else
                              { %>
                                  [key:'${p.name}', label:'${p.naturalName}']
                           <% }
                              if(i < size - 1)
                              { %>
                                  ,
                           <% }
                          }
                     }  %>
                    ]"
                    controller="blabla"
                    action="blabla"
                    paginatorConfig="[
                        template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                        pageReportTemplate:'{totalRecords} total records'
                    ]"
                    
                />

            </div>
            <div class="paginateButtons">
                <g:paginate total="\${${propertyName}Total}" />
            </div>
        </div>
    </body>
</html>
