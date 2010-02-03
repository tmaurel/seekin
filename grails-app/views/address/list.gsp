
<%@ page import="me.hcl.seekin.Util.Address" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="address.list" default="Address List" /></title>
        <gui:resources components="dataTable"/>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="address.new" default="New Address" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="address.list" default="Address List" /></h1>
            <g:if test="${flash.message}">
            <div class="message"><g:message code="${flash.message}" args="${flash.args}" default="${flash.defaultMessage}" /></div>
            </g:if>
            <div class="yui-skin-sam">
                <gui:dataTable
                    id="dt_2"
                    draggableColumns="true"
                    columnDefs="[
                        
                                  [key:'id', sortable:true, resizeable: true, label:'Id']
                           
                                  ,
                           
                                  [key:'street', sortable:true, resizeable: true, label:'Street']
                           
                                  ,
                           
                                  [key:'zipCode', sortable:true, resizeable: true, label:'Zip Code']
                           
                                  ,
                           
                                  [key:'town', sortable:true, resizeable: true, label:'Town']
                           
                    ]"
                    controller="address"
                    action="dataTableDataAsJSON"
                    paginatorConfig="[
                        template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                        pageReportTemplate:'{totalRecords} total records'
                    ]"
                    rowExpansion="true"
                    rowsPerPage="10"
                    
                />

            </div>
            <div class="paginateButtons">
                <g:paginate total="${addressInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
