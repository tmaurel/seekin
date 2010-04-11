<%! import me.hcl.seekin.Util.Settings %>
<%! import me.hcl.seekin.Auth.User %>
<%! import me.hcl.seekin.Message.* %>
<% def settingsName = (Settings.get(1)?.applicationName)?: "seekin'" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
    <head>
        <title>${settingsName} - <g:layoutTitle /></title>
        <g:layoutHead />
        <gui:resources components="accordion, dataTable, tabView"/>
        <link rel="stylesheet" href="${resource(dir:'css',file:'layout.css')}" type="text/css" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:javascript library="application" />
        <g:javascript library="prototype" />
        <g:javascript library="datatable" />
        <yui:javascript dir="json" file="json-min.js" />
        <g:YUIButtonRessource />
        <nav:resources override="true"/>
    </head>
    <body>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="Spinner" />
        </div>	
        <div id="header">
        	<div id="banner">
        		<div id="banner_cont">
        			<h1>seekin'</h1>
        			<div id="langage_selection">
	        			<g:link controller="${controllerName}" action="${actionName}" id="${params.id}" params="[lang:'fr']">
							<img src="${resource(dir:'images/icons/flags',file:'fr.png')}" alt="Français" />
						</g:link>
						<g:link controller="${controllerName}" action="${actionName}" id="${params.id}" params="[lang:'en']">
                                                        <img src="${resource(dir:'images/icons/flags',file:'en.png')}" alt="Français" />
						</g:link>
                                        
	        		</div>
                                <g:isLoggedIn>
                                <ul id="logout_block">
                                        <li><g:link controller="user" action="profile"><g:message code="user.profile" /></g:link></li>
                                        <li><g:link controller="user" action="logout"><g:message code="user.logout" /></g:link></li>
                                </ul>
                                </g:isLoggedIn>
        		</div>
       		</div>
          
       		<div id="menu">
                    <div id="menu_cont">
                        <nav:render group="menu"/>
                        <div id="search_bar">
                          <g:isLoggedIn>
                            <g:set var="user" value="${User.get(loggedInUserInfo(field:'id').toString().toInteger())}" />
                            <g:set var="boxes" value="${user.boxes}" />
                            <script type="text/javascript">
                              
                              function toggleMessagePanel() {
                                var link = $('message_link');
                                var cont = link.getOffsetParent();
                                var block = $('message_popup');

                                if(block.getStyle('display') == 'none')
                                {
                                    link.setStyle({backgroundPosition: 'top right' });
                                    cont.addClassName('message_opened');
                                    block.setStyle({display:'block'});
                                }
                                else
                                {
                                   link.setStyle({backgroundPosition: 'top left' });
                                   cont.removeClassName('message_opened');
                                   block.setStyle({display:'none'});
                                }
                              }

                              var messagePanelFormatter = function(elLiner, oRecord, oColumn, oData) {
                                var id = oData.id;
                                var unread = oData.unread;
                                var aclass = "message_read";
                                if(unread)
                                  aclass = "message_unread";

                                var link = "<a class=\"" + aclass + "\" href=\"${createLink(controller:'message', action:'show')}/" + id + "\"></a>";
                                elLiner.innerHTML = link;
                              };

                              var messageFormatter = function(elLiner, oRecord, oColumn, oData) {
                                var value = oData.value;
                                var unread = oData.unread;
                                if(unread)
                                  elLiner.innerHTML = "<strong>" + value + "</strong>";
                                else
                                  elLiner.innerHTML = value;
                              }

                              var messageDelete = function(elLiner, oRecord, oColumn, oData) {
                                var id = oData;
                                elLiner.innerHTML = "<input type=\"checkbox\" name=\"delete_" + id + "\" /> ";
                              };

                              YAHOO.widget.DataTable.Formatter.messagePanelFormatter = messagePanelFormatter;
                              YAHOO.widget.DataTable.Formatter.messageFormatter = messageFormatter;
                              YAHOO.widget.DataTable.Formatter.messageDelete = messageDelete;

                              function refreshDataSource(dataTable)
                              {
                                  var state = dataTable.getState();
                                  var paginator = state.pagination;
                                  var max = paginator.rowsPerPage;
                                  var offset = paginator.recordOffset;
                                  var dir;
                                  var custom = dataTable.customQueryString;
                                  
                                  if(state.sortedBy.dir == YAHOO.widget.DataTable.CLASS_ASC)
                                    dir = "asc"
                                  else
                                    dir = "desc"

                                  var oCallback = {
                                      success : dataTable.onDataReturnInitializeTable,
                                      failure : dataTable.onDataReturnInitializeTable,
                                      scope : dataTable,
                                      argument: state // data payload that will be returned to the callback function
                                  };

                                  dataTable.getDataSource().sendRequest(
                                    custom + "&sort=" + state.sortedBy.key + "&order=" + dir + "&max=" + max + "&offset=" + offset,
                                    oCallback
                                  );

                              }

                              function refreshDataTables(o)
                              {
                                  <g:each in="${boxes}" var="box" status="i">
                                  refreshDataSource(GRAILSUI.dt_message${box.id});
                                  </g:each>

                                  var messages = [];

                                  // Use the JSON Utility to parse the data returned from the server
                                  try {
                                    messages = YAHOO.lang.JSON.parse(o.responseText);
                                  }
                                  catch (x) {
                                    alert("JSON Parse failed!");
                                  return;
                                  }

                                  for (var i = 0, len = messages.length; i < len; ++i) {
                                        var m = messages[i];
                                        var input = YAHOO.util.Dom.get("label_box" + m.key);
                                        var inner = input.innerHTML;
                                        var pattern =/<strong>(\S+)\s\((\d+)\)<\/strong>/;
                                        var match = inner.match(pattern);
                                        if(match)
                                        {
                                            if(m.value > 0)
                                                input.innerHTML = "<strong>" + RegExp.$1 + " (" + m.value + ")</strong>"
                                            else
                                                input.innerHTML = RegExp.$1
                                        }
                                        else
                                        {
                                            if(m.value > 0)
                                                input.innerHTML = "<strong>" + inner + " (" + m.value + ")</strong>"
                                        }

                                  }
                              }

                          </script>
                          <g:link controller="message" action="list" onClick="toggleMessagePanel(); return false;" >
                              <div id="message_link">
                                  <g:set var="total_unread" value="${MessageBox.getUnreadMessagesForUser(user).size()}" />
                                  <g:if test="${total_unread > 0}">
                                    <div id="message_link_unread">${(total_unread > 9)?"+":total_unread}</div>
                                  </g:if>
                              </div>
                          </g:link>
                          </g:isLoggedIn>
                          <g:form controller="search">
                                        <g:textField name="q" value="${params?.q}"/>
                          </g:form>
                        </div>
                    </div>
                </div>
        </div>
        <div id="main">
                <nav:renderSubItems group="menu" actionMatch="true"/>
                <g:isLoggedIn>
                    <div id="content">
                        <div id="message_popup" class="yui-skin-sam">
                            <g:set var="subjectInternationalized" value="${message(code:'message.subject')}" />
                            <g:set var="authorInternationalized" value="${message(code:'message.author')}" />
                            <g:set var="dateInternationalized" value="${message(code:'message.date')}" />

                            <g:formRemote name="messageRemote" url="[controller:'message',action:'delete']" onComplete="refreshDataTables(e);">
                            <gui:tabView id="messageTabView" >
                              <g:each in="${boxes}" var="box" status="i">


                                  <g:if test="${box.label != MessageBox.MESSAGEBOX_TRASH}">
                                  <g:set var="columnDefs" value="[
                                                  [key: 'delete', sortable: true, resizeable: false, label: '', width: 12, formatter: 'messageDelete'],
                                                  [key: 'subject', sortable: true, resizeable: true, label: subjectInternationalized, width: 470, formatter: 'messageFormatter'],
                                                  [key: 'author', sortable: true, resizeable: true, label: authorInternationalized, width: 140, formatter: 'messageFormatter'],
                                                  [key: 'dateCreated', sortable: true, resizeable: true, label: dateInternationalized, width: 110, formatter: 'messageFormatter'],
                                                  [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'messagePanelFormatter']
                                              ]" />
                                  </g:if>
                                  <g:else>
                                  <g:set var="columnDefs" value="[
                                                  [key: 'subject', sortable: true, resizeable: true, label: subjectInternationalized, width: 470, formatter: 'messageFormatter'],
                                                  [key: 'author', sortable: true, resizeable: true, label: authorInternationalized, width: 140, formatter: 'messageFormatter'],
                                                  [key: 'dateCreated', sortable: true, resizeable: true, label: dateInternationalized, width: 110, formatter: 'messageFormatter'],
                                                  [key: 'urlID', sortable: false, resizeable: false, label:'Actions', formatter: 'messagePanelFormatter']
                                              ]" />
                                  </g:else>

                                  <g:set var="idBox" value="${box.id}" />
                                  <g:set var="count" value="${box.getUnreadMessages().size()}" />
                                  <g:set var="label" value="${(count > 0)? '<strong>' + message(code:box.label) + ' (' + count + ')</strong>' : message(code:box.label)}" />
                                  <gui:tab id="box${box.id}" label="${label}" active="${(box.label==MessageBox.MESSAGEBOX_INBOX)? 1:0}">
                                    <div id="dt-paginator${box.id}" class="yui-skin-sam yui-pg-container"></div>
                                    <gui:dataTable
                                        id="dt_message${box.id}"
                                        draggableColumns="true"
                                        columnDefs="${columnDefs}"
                                        controller="message"
                                        action="dataTableDataAsJSON"
                                        paginatorConfig='[
                                                template:"{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}",
                                                pageReportTemplate:"{totalRecords} " + message(code:"messages"),
                                                containers: "dt-paginator${box.id}"
                                        ]'
                                        rowExpansion="false"
                                        rowsPerPage="6"
                                        sortedBy="dateCreated"
                                        sortOrder="desc"
                                        params="[idBox:idBox]"
                                      />
                                  </gui:tab>
                                  
                            </g:each>
                            </gui:tabView>
                            <input id="messageDelete" type="submit" value="${message(code:'delete')}"/>
                              <script type="text/javascript">
                                  yuiButtonMessageDelete = new YAHOO.widget.Button("messageDelete");
                              </script>
                            </g:formRemote>
                        </div>
                </g:isLoggedIn>
                    <g:layoutBody />
                <g:isLoggedIn>
                    </div>
                    <div class="yui-skin-sam" id="submenu">
                        <g:renderSubMenu />
                    </div>
                </g:isLoggedIn>
        </div>	
        <div id="bar_powered">
            <a href="http://seekin.hcl.me"><img src="${resource(dir:'images/icons',file:'powered.png')}" alt="Powered by seekin'" /></a>
        </div>
    </body>	
</html>