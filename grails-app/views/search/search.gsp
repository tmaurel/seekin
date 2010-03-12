<%@ page import="org.springframework.util.ClassUtils" %>
<%@ page import="org.codehaus.groovy.grails.plugins.searchable.SearchableUtils" %>
<%@ page import="org.codehaus.groovy.grails.plugins.searchable.lucene.LuceneUtils" %>
<%@ page import="org.codehaus.groovy.grails.plugins.searchable.util.StringQueryUtils" %>
<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta name="layout" content="main" />
    <title><g:if test="${params.q && params.q?.trim() != ''}">${params.q} - </g:if>Grails Searchable Plugin</title>
	<g:YUIButtonRessource />
  </head>
  <body>
	<div id="search">
	  <h2><g:message code="results" /></h2>
	  <div style="clear: both; display: none;" class="hint">See <a href="http://lucene.apache.org/java/docs/queryparsersyntax.html">Lucene query syntax</a> for advanced queries</div>
	  <div>
		<div>
		  <g:form name="search-form" action="search" class="yui-skin-sam">
			<g:textField name="q" value="${params?.q}"/>
			<g:select name="domain"
					  from="${searchableDomain}"
					  value="${params?.domain}"
			/>
			<g:submitButton name="search" value="Search" />
		  </g:form>
		</div>
		<g:set var="haveQuery" value="${params.q?.trim()}" />
		<g:set var="haveResults" value="${searchResult?.results}" />
		<div class="title">
		  <span>
			<g:if test="${haveQuery && haveResults}">
			  Showing <strong>${searchResult.offset + 1}</strong> - <strong>${searchResult.results.size() + searchResult.offset}</strong> of <strong>${searchResult.total}</strong>
			  results for <strong>${params.q}</strong>
			</g:if>
			<g:else>
			&nbsp;
			</g:else>
		  </span>
		</div>

		<g:if test="${haveQuery && !haveResults && !parseException}">
		  <p>Nothing matched your query - <strong>${params.q}</strong></p>
		</g:if>

		<g:if test="${parseException}">
		  <p>Your query - <strong>${params.q}</strong> - is not valid.</p>
		  <p>Suggestions:</p>
		  <ul>
			<li>Fix the query: see <a href="http://lucene.apache.org/java/docs/queryparsersyntax.html">Lucene query syntax</a> for examples</li>
			<g:if test="${LuceneUtils.queryHasSpecialCharacters(params.q)}">
			  <li>Remove special characters like <strong>" - [ ]</strong>, before searching, eg, <em><strong>${LuceneUtils.cleanQuery(params.q)}</strong></em><br />
				  <em>Use the Searchable Plugin's <strong>LuceneUtils#cleanQuery</strong> helper method for this: <g:link controller="search" action="search" params="[q: LuceneUtils.cleanQuery(params.q)]">Search again with special characters removed</g:link></em>
			  </li>
			  <li>Escape special characters like <strong>" - [ ]</strong> with <strong>\</strong>, eg, <em><strong>${LuceneUtils.escapeQuery(params.q)}</strong></em><br />
				  <em>Use the Searchable Plugin's <strong>LuceneUtils#escapeQuery</strong> helper method for this: <g:link controller="search" action="search" params="[q: LuceneUtils.escapeQuery(params.q)]">Search again with special characters escaped</g:link></em><br />
				  <em>Or use the Searchable Plugin's <strong>escape</strong> option: <g:link controller="searchable" action="search" params="[q: params.q, escape: true]">Search again with the <strong>escape</strong> option enabled</g:link></em>
			  </li>
			</g:if>
		  </ul>
		</g:if>

		<g:if test="${haveResults}">
		  <div class="results">
			<g:each var="result" in="${searchResult.results}" status="index">
			  <g:set var="className" value="${ClassUtils.getShortName(result.getClass())}" />
			  <g:if test="${className == 'Student'}">
			  <div class="result">
				<g:set var="link" value="${createLink(controller: className[0].toLowerCase() + className[1..-1], action: 'show', id: result.id)}" />
				<div class="name"><span class="class_type">[<g:message code="${className}" />]</span> <a href="${link}">${result.toString()}</a></div>
				<div class="desc">
				  ${result?.user?.address?.encodeAsHTML()} <br />
				  ${result?.user?.phone?.encodeAsHTML()} <br />
				</div>
				<div class="displayLink">${link}</div>
			  </div>
			  </g:if>
			  <g:if test="${className == 'Internship'}">
			  <div class="result">
				<g:set var="link" value="${createLink(controller: className[0].toLowerCase() + className[1..-1], action: 'show', id: result.id)}" />
				<div class="name"><span class="class_type">[<g:message code="${className}" />]</span> <a href="${link}">${result.toString()}</a></div>
				<div class="desc">
				  ${result?.company?.encodeAsHTML()} - ${result?.student?.encodeAsHTML()}
				</div>
				<div class="displayLink">${link}</div>
			  </div>
			  </g:if>
			  <g:if test="${className == 'Offer'}">
			  <div class="result">
				<g:set var="link" value="${createLink(controller: className[0].toLowerCase() + className[1..-1], action: 'show', id: result.id)}" />
				<div class="name"><span class="class_type">[<g:message code="${className}" />]</span> <a href="${link}">${result.toString()}</a></div>
				<div class="desc">
				  ${result?.company?.encodeAsHTML()}
				</div>
				<div class="displayLink">${link}</div>
			  </div>
			  </g:if>
			  <g:if test="${className == 'Company'}">
			  <div class="result">
				<g:set var="link" value="${createLink(controller: className[0].toLowerCase() + className[1..-1], action: 'show', id: result.id)}" />
				<div class="name"><span class="class_type">[<g:message code="${className}" />]</span> <a href="${link}">${result}</a></div>
				<div class="desc">
				  ${result?.address} <br />
				  ${result?.phone}
				</div>
				<div class="displayLink">${link}</div>
			  </div>
			  </g:if>
			  <g:if test="${className == 'Formation'}">
			  <div class="result">
				<g:set var="link" value="${createLink(controller: className[0].toLowerCase() + className[1..-1], action: 'show', id: result.id)}" />
				<div class="name"><span class="class_type">[<g:message code="${className}" />]</span> <a href="${link}">${result}</a></div>
				<div class="desc">
				  ${result?.description} <br />
				</div>
				<div class="displayLink">${link}</div>
			  </div>
			  </g:if>
			  <g:if test="${className == 'Link'}">
			  <div class="result">
				<g:set var="link" value="${createLink(controller: className[0].toLowerCase() + className[1..-1], action: 'show', id: result.id)}" />
				<div class="name"><span class="class_type">[<g:message code="${className}" />]</span> <a href="${link}">${result}</a></div>
				<div class="desc">
				  ${result?.description} <br />
				  ${result?.url}
				</div>
				<div class="displayLink">${link}</div>
			  </div>
			  </g:if>
			</g:each>
		  </div>

		  <div>
			<div class="paging">
			  <g:if test="${haveResults}">
				  Page:
				  <g:set var="totalPages" value="${Math.ceil(searchResult.total / searchResult.max)}" />
				  <g:if test="${totalPages == 1}"><span class="currentStep">1</span></g:if>
				  <g:else><g:paginate controller="search" action="search" params="[q: params.q]" total="${searchResult.total}" prev="&lt; previous" next="next &gt;"/></g:else>
			  </g:if>
			</div>
		  </div>
		</g:if>
	  </div>
	</div>
  </body>
</html>
