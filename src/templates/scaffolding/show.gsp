<% import org.codehaus.groovy.grails.orm.hibernate.support.ClosureEventTriggeringInterceptor as Events %>
<%=packageName%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="${domainClass.propertyName}.show" /></title>
        <g:YUIButtonRessource />
    </head>
    <body>

        <h2><g:message code="${domainClass.propertyName}.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test="\${flash.message}">
            <div class="flash_message"><g:message code="\${flash.message}" args="\${flash.args}" default="\${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="\${${propertyName}?.id}" />
                    <%  excludedProps = ["version",
                                         Events.ONLOAD_EVENT,
                                         Events.BEFORE_INSERT_EVENT,
                                         Events.BEFORE_UPDATE_EVENT,
                                         Events.BEFORE_DELETE_EVENT]
                        props = domainClass.properties.findAll { !excludedProps.contains(it.name) }
                        Collections.sort(props, comparator.constructors[0].newInstance([domainClass] as Object[]))
                        props.each { p -> %>
                       <p>
                            <label><g:message code="${domainClass.propertyName}.${p.name}" default="${p.naturalName}" /></label>
                            <span class="field_value">
                            <%  if (p.isEnum()) { %>
                            \${${propertyName}?.${p.name}?.encodeAsHTML()}
                            <%  } else if (p.oneToMany || p.manyToMany) { %>
                            <ul>
                            <g:each in="\${${propertyName}?.${p.name}}" var="${p.referencedDomainClass?.propertyName}Instance">
                                <li><g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${p.referencedDomainClass?.propertyName}Instance.id}">\${${p.referencedDomainClass?.propertyName}Instance.encodeAsHTML()}</g:link></li>
                            </g:each>
                            </ul>
                            <%  } else if (p.manyToOne || p.oneToOne) { %>
                            <g:link controller="${p.referencedDomainClass?.propertyName}" action="show" id="\${${propertyName}?.${p.name}?.id}">\${${propertyName}?.${p.name}?.encodeAsHTML()}</g:link>
                            <%  } else if (p.type == Boolean.class || p.type == boolean.class) { %>
                            <g:formatBoolean boolean="\${${propertyName}?.${p.name}}" />
                            <%  } else if (p.type == Date.class || p.type == java.sql.Date.class || p.type == java.sql.Time.class || p.type == Calendar.class) { %>
                            <g:formatDate date="\${${propertyName}?.${p.name}}" />
                            <%  } else if (BigDecimal.class.isAssignableFrom(p.type)) { %>
                            <g:formatNumber number="\${${propertyName}?.${p.name}}" />
                            <%  } else { %>
                            \${fieldValue(bean: ${propertyName}, field: "${p.name}")}
                            <%  } %>
                            </span>
                        <%  } %>
                      </p>
                      <div class="submit yui-skin-sam">
                        <g:buildShowButtons />
                      </div>
            </g:form>
       </body>
</html>
