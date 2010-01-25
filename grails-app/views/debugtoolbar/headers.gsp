<table>
	<thead>
		<tr>
			<th>Key</th>
			<th>Value</th>
		</tr>
	</thead>
	<tbody>
	
		<g:each status="i" in="${headers}" var="item">	    		
    		<tr class="${ (i % 2) == 0 ? 'djDebugOdd' : 'djDebugEven'}">
      			<td>${item.key?.encodeAsHTML()}</td>
      			<td>${item.value?.encodeAsHTML()}</td>      			
    		</tr>
  		</g:each>
	
	</tbody>
</table>
