
<ul>
	<li>App version: <g:meta name="app.version"></g:meta></li>
	<li>Grails version: <g:meta name="app.grails.version"></g:meta></li>
	<li>JVM version: ${System.getProperty('java.version')}</li>
</ul>
		
<table>
	<thead>
		<tr>
			<th>Plugin</th>
			<th>Version</th>
		</tr>
	</thead>
	<tbody>
		
		<g:set var="pluginManager"
						       value="${applicationContext.getBean('pluginManager')}"></g:set>
		
		<g:each var="plugin" status="i" in="${pluginManager.allPlugins}">
			<tr class="${(i % 2) == 0 ? 'djDebugOdd' : 'djDebugEven'}">
				<td>${plugin.name}</td>
				<td>${plugin.version}</td>
			</tr>
		</g:each>
					
	</tbody>
</table>
