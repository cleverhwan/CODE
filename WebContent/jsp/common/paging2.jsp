<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!--페이징:start-->
<div class="paging">
    <ul>         
        <c:choose>
			<c:when test="${currentpage<=1}">				
				<li><a href="#"><</a></li>
			</c:when>
			<c:otherwise>				
				<li><a href="javascript:goPage(${currentpage-1});"><</a></li>
			</c:otherwise>	
		</c:choose>
			
			    <c:forEach var="i" begin="${firstpage}" end="${lastpage}" step="1">
			    <c:choose>
			    	<c:when test="${currentpage eq i}">						
						<li class="active"><a href="javascript:goPage(${i});">${i}</a></li>
					</c:when>
					<c:otherwise>						
						<li><a href="javascript:goPage(${i});">${i}</a></li>
					</c:otherwise>
				</c:choose>
				</c:forEach>
			
		<c:choose>
			<c:when test="${currentpage>=totalpage}">				
				<li><a href="javascript:goPage(${currentpage});">></a></li>
			</c:when>
			<c:otherwise>				
				<li><a href="javascript:goPage(${currentpage+1});">></a></li>
			</c:otherwise>
		</c:choose>
        
    </ul>
</div>
<!--페이징:end-->