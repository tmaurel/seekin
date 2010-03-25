
<%@ page import="me.hcl.seekin.Internship.Offer" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="offer.show" /></title>
        
        <tm:resources />
    </head>
    <body>

        <h2><g:message code="offer.show" /></h2>
        <g:form class="boxed_form" name="crud_panel">
            <g:if test ="${offerInstance?.reason}">
              <div class="flash_message"> ${fieldValue(bean: offerInstance, field: "reason")}</div>
            </g:if>
            <g:if test="${flash.message}">
            <div class="flash_message"><g:message code="${flash.message}" transparent="true" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <g:hiddenField name="id" value="${offerInstance?.id}" />
                    
                       <p>
                            <label><g:message code="offer.id" default="Id" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: offerInstance, field: "id")}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="offer.subject" default="Subject" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: offerInstance, field: "subject")}
                            
                            </span>
                      </p>
                        
                       <div class="textarea_field">
                            <label><g:message code="offer.description" default="Description" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: offerInstance, field: "description").replaceAll('\n', '<br />')}
                            
                            </span>
                      </div>
                        
                       <p>
                            <label><g:message code="offer.beginAt" default="Begin At" /></label>
                            <span class="field_value">
                            
                            ${offerInstance?.beginAt?.formatDate()}
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="offer.length" default="Length" /></label>
                            <span class="field_value">
                            
                            ${fieldValue(bean: offerInstance, field: "length")}
                            
                            </span>
                      </p>

                      <p>
                            <label><g:message code="offer.file" default="File" /></label>
                            <span class="field_value">
                            
                            <g:link controller="document" action="show" id="${offerInstance?.file?.id}">${offerInstance?.file?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>
                        
                       <p>
                            <label><g:message code="offer.author" default="Author" /></label>
                            <span class="field_value">
                            
                            <g:link controller="user" action="show" id="${offerInstance?.author?.id}">${offerInstance?.author?.encodeAsHTML()}</g:link>
                            
                            </span>
                      </p>

                       <p>
                            <label><g:message code="offer.company" default="Company" /></label>
                            <span class="field_value">

                            <g:link controller="company" action="show" id="${offerInstance?.company?.id}">${offerInstance?.company?.name?.encodeAsHTML()}</g:link>

                            </span>
                      </p>
                        
                       <div class="properties_list">
                            <label><g:message code="offer.promotions" default="Promotions" /></label>
                            <ul>
                            <g:each in="${offerInstance?.promotions}" var="promotionInstance">
                                <li><g:link controller="promotion" action="show" id="${promotionInstance.id}">${promotionInstance.encodeAsHTML()}</g:link></li>
                            </g:each>
                            </ul>
                      </div>
                        
                      
                      <div class="actionpad yui-skin-sam">

                          <g:listButton />

                          <g:ifAnyGranted role="ROLE_ADMIN,ROLE_FORMATIONMANAGER,ROLE_EXTERNAL">
                              <g:if test="${offerInstance.validated == false}">
                                <g:YUISubmitbutton value="edit" action="edit" />
                                <g:deleteButton />
                              </g:if>
                              <g:else>
                                <g:deleteButton />
                              </g:else>
                          </g:ifAnyGranted>
                          <g:ifNotGranted role="ROLE_ADMIN,ROLE_FORMATIONMANAGER,ROLE_EXTERNAL">
                            <g:ifAnyGranted role="ROLE_STAFF">
                              <g:if test="${editable}">
                                <g:if test="${offerInstance.validated == false}">
                                  <g:YUISubmitbutton value="edit" action="edit" />
                                  <g:deleteButton />
                                </g:if>
                                <g:else>
                                  <g:deleteButton />
                                </g:else>
                              </g:if>
                            </g:ifAnyGranted>
                          </g:ifNotGranted>

                      </div>
            </g:form>
       </body>
</html>
