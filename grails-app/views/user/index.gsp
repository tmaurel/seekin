
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="home" /></title>
    </head>
    <body>
      <h2><g:message code="home" /></h2>
      <br />
      <div id="home_panel">
		<%-- STUDENTS HOME VIEW --%>
		<g:ifAnyGranted role="ROLE_STUDENT">
		<%-- STUDENTS LAST OFFERS BLOCK --%>
		<div class="home_block yui-skin-sam">
			<gui:expandablePanel title="${message(code:'offers.lasts')}" expanded="true" bounce="false">
			  <g:if test="${totalLastOffers > 0}">
			  <ul>
				<g:each var="offer" in="${lastOffers}">
				  <li><g:link controller="offer" action="show" id="${offer?.id}">${offer}</g:link></li>
				</g:each>
			  </ul>
			  </g:if>
			  <g:else>
				<span><g:message code="offers.no" /></span>
			  </g:else>
			</gui:expandablePanel>
		</div>
		<%-- STUDENTS MY INTERNSHIPS BLOCK --%>
		<div class="home_block yui-skin-sam">
			<gui:expandablePanel title="${message(code:'internships.my')}" expanded="true" bounce="false">
			  <g:if test="${totalStudentInternships > 0}">
			  <ul>
				<g:each var="internship" in="${studentInternships}">
				  <li><g:link controller="internship" action="show" id="${internship?.id}">${internship}</g:link></li>
				</g:each>
			  </ul>
			  </g:if>
			  <g:else>
				<span><g:message code="internships.no" /></span>
			  </g:else>
			</gui:expandablePanel>
		</div>
		<%-- STUDENTS LAST REPORTS BLOCK --%>
		<div class="home_block yui-skin-sam">
			<gui:expandablePanel title="${message(code:'reports.last')}" expanded="true" bounce="false">
			  <g:if test="${totalLastInternshipReports > 0}">
			  <ul>
				<g:each var="report" in="${lastInternshipReports}">
				  <li><g:link controller="report" action="show" id="${report?.id}">${report}</g:link></li>
				</g:each>
			  </ul>
			  </g:if>
			  <g:else>
				<span><g:message code="reports.no" /></span>
			  </g:else>
			</gui:expandablePanel>
		</div>
		<%-- STUDENTS STATS BLOCK --%>
		<div class="home_block yui-skin-sam">
			<gui:expandablePanel title="${message(code:'stats.my')}" expanded="true" bounce="false">
			  <ul>
				<li><g:message code="report.stats" /> : ${totalInternshipReports}</li>
				<li><g:message code="link.stats" /> : ${totalLinks}</li>
			  </ul>
			</gui:expandablePanel>
		</div>
		</g:ifAnyGranted>
		<%-- FORMATION MANAGER HOME VIEW --%>
		<g:ifAnyGranted role="ROLE_FORMATIONMANAGER">
		<%-- FORMATION MANAGER STUDENTS WITHOUT INTERNSHIP BLOCK --%>
		<div class="home_block yui-skin-sam">
			<gui:expandablePanel title="${message(code:'students.withoutInternship')}" expanded="true" bounce="false">
			  <g:if test="${totalStudentsWithoutInternship > 0}">
			  <ul>
				<g:each var="student" in="${studentsWithoutInternship}">
				  <li><g:link controller="student" action="show" id="${student?.id}">${student}</g:link></li>
				</g:each>
			  </ul>
			  </g:if>
			  <g:else>
				<span><g:message code="students.no" /></span>
			  </g:else>
			</gui:expandablePanel>
		</div>
		<%-- FORMATION MANAGER STUDENTS WITHOUT ACADEMIC TUTOR BLOCK --%>
		<div class="home_block yui-skin-sam">
			<gui:expandablePanel title="${message(code:'students.withoutAcademicTutor')}" expanded="true" bounce="false">
			  <g:if test="${totalStudentsWithoutAcademicTutor > 0}">
			  <ul>
				<g:each var="student" in="${studentsWithoutAcademicTutor}">
				  <li><g:link controller="student" action="show" id="${student?.id}">${student}</g:link></li>
				</g:each>
			  </ul>
			  </g:if>
			  <g:else>
				<span><g:message code="students.no" /></span>
			  </g:else>
			</gui:expandablePanel>
		</div>
		<%-- FORMATION MANAGER INTERNSHIPS WAITING FOR VALIDATION BLOCK --%>
		<div class="home_block yui-skin-sam">
			<gui:expandablePanel title="${message(code:'internships.waitingForValidation')}" expanded="true" bounce="false">
			  <g:if test="${totalInternshipsWaitingForValidation > 0}">
			  <ul>
				<g:each var="internship" in="${internshipsWaitingForValidation}">
				  <li><g:link controller="internship" action="show" id="${internship?.id}">${internship}</g:link></li>
				</g:each>
			  </ul>
			  </g:if>
			  <g:else>
				<span><g:message code="internships.no" /></span>
			  </g:else>
			</gui:expandablePanel>
		</div>
		<%-- FORMATION MANAGER OFFERS WAITING FOR VALIDATION BLOCK --%>
		<div class="home_block yui-skin-sam">
			<gui:expandablePanel title="${message(code:'offers.waitingForValidation')}" expanded="true" bounce="false">
			  <g:if test="${totalOffersWaitingForValidation > 0}">
			  <ul>
				<g:each var="offer" in="${offersWaitingForValidation}">
				  <li><g:link controller="offer" action="show" id="${offer?.id}">${offer?.subject}</g:link></li>
				</g:each>
			  </ul>
			  </g:if>
			  <g:else>
				<span><g:message code="offers.no" /></span>
			  </g:else>
			</gui:expandablePanel>
		</div>
		</g:ifAnyGranted>
	  </div>
    </body>
</html>
