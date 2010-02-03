
<%@ page import="me.hcl.seekin.Internship.Internship" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="internship.list" default="Internship List" /></title>
        <gui:resources components="dataTable"/>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir: '')}"><g:message code="home" default="Home" /></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="internship.new" default="New Internship" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="internship.list" default="Internship List" /></h1>
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
                           
                                  [key:'subject', sortable:true, resizeable: true, label:'Subject']
                           
                                  ,
                           
                                  [key:'beginAt', sortable:true, resizeable: true, label:'Begin At']
                           
                                  ,
                           
                                  [key:'isApproval', sortable:true, resizeable: true, label:'Is Approval']
                           
                                  ,
                           
                                  [key:'internship.report', sortable:true, resizeable: true, label:'Report']
                           
                                  ,
                           
                                  [key:'internship.student', sortable:true, resizeable: true, label:'Student']
                           
                                  ,
                           
                    ]"
                    controller="internship"
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
                <g:paginate total="${internshipInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
