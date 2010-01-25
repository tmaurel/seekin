<table>
	<thead>
		<tr>
			<th>Config</th>
			<th>Value</th>
		</tr>
	</thead>
	<tbody>
		<g:each status="i" in="${grailsApplication.config.flatten()}" var="item">	    		
    		<tr class="${ (i % 2) == 0 ? 'djDebugOdd' : 'djDebugEven'}">
      			<td>${item.key?.encodeAsHTML()}</td>
      			<td>${item.value?.encodeAsHTML()}</td>      			
    		</tr>
  		</g:each>
	</tbody>
</table>
