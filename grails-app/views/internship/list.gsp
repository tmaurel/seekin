
<%@ page import="me.hcl.seekin.Internship.Internship" %>
<gui:resources components="dataTable"/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="internship.list" default="Internship List" /></title>
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
            <div class="list">
                        <tr>
                        
                   	    <g:sortableColumn property="id" title="Id" titleKey="internship.id" />
                        
                   	    <g:sortableColumn property="subject" title="Subject" titleKey="internship.subject" />
                        
                   	    <g:sortableColumn property="beginAt" title="Begin At" titleKey="internship.beginAt" />
                        
                   	    <g:sortableColumn property="isApproval" title="Is Approval" titleKey="internship.isApproval" />
                        
                   	    <th><g:message code="internship.report" default="Report" /></th>
                   	    
                   	    <th><g:message code="internship.student" default="Student" /></th>
                   	    
                        </tr>
                   <gui:dataTable
                        id="dt_2"
                        draggableColumns="true"
                        columnDefs="[
                            [key:'[id, subject, beginAt, isApproval, report, student, academicTutor, companyTutor, convocation]', sortable:true, resizeable: true, label:'ID' ]
                        ]"

                        paginatorConfig="[
                            template:'{PreviousPageLink} {PageLinks} {NextPageLink} {CurrentPageReport}',
                        ]"
                        controller="internship" action="dataTableDataAsJSON"
                        resultsList="results"
                        rowExpansion="true"
                        rowsPerPage="3"
                    />
            </div>
            <div class="paginateButtons">
                <g:paginate total="${internshipInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
