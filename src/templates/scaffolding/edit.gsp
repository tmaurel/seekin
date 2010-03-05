<% import org.codehaus.groovy.grails.orm.hibernate.support.ClosureEventTriggeringInterceptor as Events %>
<%=packageName%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="${domainClass.propertyName}.edit"/></title>
        <g:YUIButtonRessource />
    </head>
    <body>
      <h2><g:message code="${domainClass.propertyName}.edit"/></h2>
      <g:if test="\${flash.message}">
      <div class="flash_message"><g:message code="\${flash.message}" args="\${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="\${${propertyName}}">
      <div class="flash_message"><g:renderErrors bean="\${${propertyName}}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" <%= multiPart ? " enctype=\"multipart/form-data\"" : "" %>>
          <g:hiddenField name="id" value="\${${propertyName}?.id}" />
          <g:hiddenField name="version" value="\${${propertyName}?.version}" />
          <%  excludedProps = ["version",
                               "id",
                               Events.ONLOAD_EVENT,
                               Events.BEFORE_INSERT_EVENT,
                               Events.BEFORE_UPDATE_EVENT,
                               Events.BEFORE_DELETE_EVENT]
              props = domainClass.properties.findAll { !excludedProps.contains(it.name) }
              Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
              props.each { p ->
                  cp = domainClass.constrainedProperties[p.name]
                  display = (cp ? cp.display : true)
                  if (display) { %>
                <p>

                          <label for="${p.name}"><g:message code="${domainClass.propertyName}.${p.name}" default="${p.naturalName}" /></label>
                          ${renderEditor(p)}
                </p>
          <%  }   } %>
          <div class="actionpad yui-skin-sam">
            <g:buildEditButtons />
          </div>
      </g:form>
    </body>
</html>
