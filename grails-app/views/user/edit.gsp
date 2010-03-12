
<%@ page import="me.hcl.seekin.Auth.User" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="user.edit"/></title>
        <g:YUIButtonRessource />
        <yui:javascript dir="animation" file="animation-min.js" />
        <g:javascript library="toggle" />
        <tm:resources />
    </head>
    <body>
      <h2><g:message code="user.edit"/></h2>
      <g:if test="${flash.message}">
      <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}"/></div>
      </g:if>
      <g:hasErrors bean="${userInstance}">
      <div class="flash_message"><g:renderErrors bean="${userInstance}" as="list" /></div>
      </g:hasErrors>
      <g:form class="boxed_form" name="crud_panel" method="post" >
          <g:hiddenField name="id" value="${userInstance?.id}" />
          <g:hiddenField name="version" value="${userInstance?.version}" />
          
                <p>

                          <label for="email"><g:message code="user.email" default="Email" /></label>
                          <g:textField name="email" value="${userInstance.email}" class="field${hasErrors(bean:userInstance,field:'email','error')}"/>

                </p>
                    
                <p>

                          <label for="enabled"><g:message code="user.enabled" default="Enabled" /></label>
                          <g:checkBox name="enabled" value="${userInstance?.enabled}" />

                </p>
          
                <p>

                          <label for="firstName"><g:message code="user.firstName" default="First Name" /></label>
                          <g:textField name="firstName" value="${userInstance.firstName}" class="field${hasErrors(bean:userInstance,field:'firstName','error')}"/>

                </p>
          
                <p>

                          <label for="lastName"><g:message code="user.lastName" default="Last Name" /></label>
                          <g:textField name="lastName" value="${userInstance.lastName}" class="field${hasErrors(bean:userInstance,field:'lastName','error')}"/>

                </p>
          
                <p>
                          <label for="address.street"><g:message code="address.street" default="Street" /></label>
                          <g:textField name="address.street" value="${userInstance?.address.street}" class="field${hasErrors(bean:userInstance,field:'address.street','error')}"/>
                </p>

                <p>
                          <label for="address.town"><g:message code="address.town" default="Town" /></label>
                          <g:textField name="address.town" value="${userInstance?.address.town}" class="field${hasErrors(bean:userInstance,field:'address.town','error')}"/>
                </p>

                <p>
                          <label for="address.zipCode"><g:message code="address.zipCode" default="Zip Code" /></label>
                          <g:textField name="address.zipCode" value="${userInstance?.address.zipCode}" class="field${hasErrors(bean:userInstance,field:'address.zipCode','error')}"/>
                </p>

                <p>

                          <label for="phone"><g:message code="user.phone" default="Phone" /></label>
                          <g:textField name="phone" maxlength="10" value="${userInstance.phone}" class="field${hasErrors(bean:userInstance,field:'phone','error')}"/>

                </p>
          
                <p>

                          <label for="showEmail"><g:message code="user.showEmail" default="Show Email" /></label>
                          <g:checkBox name="showEmail" value="${userInstance?.showEmail}" />

                </p>
                <div class="properties_list">
                          <label><g:message code="user.authorities" default="Authorities" /></label>
                          <ul>
                          <g:each var="role" in="${roles}">
                                  <li>
                                          <g:set var="current" value="${role.name.toUpperCase() == 'STUDENT' || role.name.toUpperCase() == 'STAFF' || role.name.toUpperCase() == 'EXTERNAL'}"/>
                                          <g:if test="${role.value && current}">
                                              <g:hiddenField name="ROLE_${role.name.toUpperCase()}" value="${role.value}" />
                                              <g:checkBox name="ROLE2_${role.name.toUpperCase()}" value="${role.value}" disabled="${true}"/>
                                          </g:if>
                                          <g:else>
                                              <g:checkBox name="ROLE_${role.name.toUpperCase()}" value="${role.value}"/>
                                          </g:else>

                                          ${role.name.encodeAsHTML()}
                                          <g:if test="${role.name.toUpperCase() == 'STUDENT' || role.name.toUpperCase() == 'FORMATIONMANAGER'}">
                                              <g:select name="FORMATION_${role.name}" value='${params."FORMATION_$role.name"?:role.formation}' from="${formations}"  optionKey="id" optionValue="value" style="float:right; ${if(role.value!='on' && role.value !=true) {'visibility:hidden;width:0px;'} else {'width:200px;'} }"/>
                                              <script type="text/javascript">
                                                formationField('ROLE_${role.name.toUpperCase()}', 'FORMATION_${role.name}')
                                              </script>
                                          </g:if>
                                  </li>
                          </g:each>
                          </ul>
                </div>

          
          <div class="actionpad yui-skin-sam">
            <g:buildEditButtons />
          </div>
      </g:form>
    </body>
</html>
