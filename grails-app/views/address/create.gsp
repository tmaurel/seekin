
<%@ page import="me.hcl.seekin.Util.Address" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="address.create" default="Create Address" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="address.list" default="Address List" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="address.create" default="Create Address" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hasErrors bean="${addressInstance}">
            <div class="errors">
                <g:renderErrors bean="${addressInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" method="post" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="street"><g:message code="address.street" default="Street" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'street', 'errors')}">
                                    <g:textField name="street" value="${fieldValue(bean: addressInstance, field: 'street')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="zipCode"><g:message code="address.zipCode" default="Zip Code" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'zipCode', 'errors')}">
                                    <g:textField name="zipCode" value="${fieldValue(bean: addressInstance, field: 'zipCode')}" />

                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="town"><g:message code="address.town" default="Town" />:</label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: addressInstance, field: 'town', 'errors')}">
                                    <g:textField name="town" value="${fieldValue(bean: addressInstance, field: 'town')}" />

                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'create', 'default': 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
