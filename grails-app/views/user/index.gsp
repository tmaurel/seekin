
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <title><g:message code="home"/></title>
    </head>
    <body>
      <h2><g:message code="home"/></h2>
      <br />
      <div id="home_panel">
		<g:ifAnyGranted role="ROLE_STUDENT">
		<div class="home_block yui-skin-sam">
			<gui:expandablePanel title="${message(code:'offers.lasts')}" expanded="true" bounce="false">
			  <g:if test="${totalLastOffers > 0}">
			  <ul>
				<g:each var="offer" in="${lastOffers}">
				  <li><g:link controller="offer" action="show" id="${offer?.id}">${offer?.subject}</g:link></li>
				</g:each>
			  </ul>
			  </g:if>
			  <g:else>
				<span><g:message code="offer.noLastOffer" /></span>
			  </g:else>
			</gui:expandablePanel>
		</div>
		<div class="home_block yui-skin-sam">
			<gui:expandablePanel title="${message(code:'internships.my')}" expanded="true" bounce="false">
			  <g:if test="${totalStudentInternships > 0}">
			  <ul>
				<g:each var="internship" in="${studentInternships}">
				  <li><g:link controller="internship" action="show" id="${internship?.id}">${internship?.subject}</g:link></li>
				</g:each>
			  </ul>
			  </g:if>
			  <g:else>
				<span><g:message code="internship.noInternship" /></span>
			  </g:else>
			</gui:expandablePanel>
		</div>
		<div class="home_block yui-skin-sam">
			<gui:expandablePanel title="${message(code:'stats.my')}" expanded="true" bounce="false">
			  <ul>
				<li><g:message code="report.stats" /> : ${totalInternshipsReports}</li>
				<li><g:message code="link.stats" /> : ${totalLinks}</li>
			  </ul>
			</gui:expandablePanel>
		</div>
		</g:ifAnyGranted>
	  </div>
    </body>
</html>
