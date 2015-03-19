<div id="le_menu">
	<ul>
		<c:forEach items="${menu}" var="item">
				<li><a href=${ item.value }>${item.key}</a></li>
				
		</c:forEach>
	</ul>
</div>