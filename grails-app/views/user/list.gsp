
<%@ page import="me.hcl.seekin.Auth.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="user.list" default="User List" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="navigation.menu.home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="user.new" default="New User" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="user.list" default="User List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	    <g:sortableColumn property="id" title="Id" titleKey="user.id" />
                        
                   	    <g:sortableColumn property="email" title="Email" titleKey="user.email" />
                        
                   	    <g:sortableColumn property="password" title="Password" titleKey="user.password" />
                        
                   	    <g:sortableColumn property="enabled" title="Enabled" titleKey="user.enabled" />
                        
                   	    <g:sortableColumn property="showEmail" title="Show Email" titleKey="user.showEmail" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${personList}" status="i" var="person">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${person.id}">${fieldValue(bean: person, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: person, field: "email")}</td>
                        
                            <td>${fieldValue(bean: person, field: "password")}</td>
                        
                            <td><g:formatBoolean boolean="${person.enabled}" /></td>
                        
                            <td><g:formatBoolean boolean="${person.showEmail}" /></td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${personTotal}" />
            </div>
        </div>
    </body>
</html>
