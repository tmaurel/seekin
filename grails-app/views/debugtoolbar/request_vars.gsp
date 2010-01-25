
<h4>COOKIES Variables</h4>
<g:if test="${cookies} != null">
	<table>
		<colgroup>
			<col style="width:20%"/>
			<col/>
		</colgroup>
		<thead>
			<tr>
				<th>Variable</th>
				<th>Value</th>
			</tr>
		</thead>
		<tbody>
			<g:each status="i" in="${cookies}" var="item">	    		
	    		<tr class="${ (i % 2) == 0 ? 'djDebugOdd' : 'djDebugEven'}">
	      			<td>${item.name?.encodeAsHTML()}</td>
	      			<td>${item.value?.encodeAsHTML()}</td>      			
	    		</tr>
	  		</g:each>
	  		
		</tbody>
	</table>
</g:if>
<g:else>
	<p>No COOKIE data</p>
</g:else>


<h4>SESSION Variables</h4>
<g:if test="${sess} != null">
	<table>
		<colgroup>
			<col style="width:20%"/>
			<col/>
		</colgroup>
		<thead>
			<tr>
				<th>Variable</th>
				<th>Value</th>
			</tr>
		</thead>
		<tbody>
			<g:each status="i" in="${sess}" var="item">	    		
	    		<tr class="${ (i % 2) == 0 ? 'djDebugOdd' : 'djDebugEven'}">
	      			<td>${item.key?.encodeAsHTML()}</td>
	      			<td>${item.value?.encodeAsHTML()}</td>      			
	    		</tr>
	  		</g:each>
	  		
		</tbody>
	</table>
</g:if>
<g:else>
	<p>No SESSION data</p>
</g:else>

<h4>GET Variables</h4>
<g:if test="${get} != null">
	<table>
		<thead>
			<tr>
				<th>Variable</th>
				<th>Value</th>
			</tr>
		</thead>
		<tbody>
			<g:each status="i" in="${get}" var="item">	    		
	    		<tr class="${ (i % 2) == 0 ? 'djDebugOdd' : 'djDebugEven'}">
	      			<td>${item.key?.encodeAsHTML()}</td>
	      			<td>${item.value?.encodeAsHTML()}</td>      			
	    		</tr>
	  		</g:each>  		
	  		
		</tbody>
	</table>
</g:if>
<g:else>
	<p>No GET data</p>
</g:else>

<h4>POST Variables</h4>
<g:if test="${post} != null">
	<table>
		<thead>
			<tr>
				<th>Variable</th>
				<th>Value</th>
			</tr>
		</thead>
		<tbody>
			 <g:each status="i" in="${post}" var="item">	    		
	    		<tr class="${ (i % 2) == 0 ? 'row1' : 'row2'}">
	      			<td>${item.key?.encodeAsHTML()}</td>
	      			<td>${item.value?.encodeAsHTML()}</td>      			
	    		</tr>
	  		</g:each>  					
		</tbody>
	</table>
</g:if>
<g:else>
	<p>No POST data</p>
</g:else>
