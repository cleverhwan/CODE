<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="/jsp/common/resource.jsp"%>
	<link href="/css/error.css" rel="stylesheet" type="text/css">
	<script type="text/javascript">
	$(document).ready(function(){ });
	</script>
	
</head>
<body>
<!-- #wrap// -->
	<div id="wrap">
		<%@include file="/jsp/common/header.jsp"%>

		<section id="container">
			<!-- //title-2depth End -->
			<article class="contents error">
			
	        	<div class="error_box e505"> 
	            	<div class="text">
	                	오류가 발생하여 처리할 수 없습니다.
	                </div>
	                <div class="img">
	                	<img src="/images/505_icon.png" alt=""/>
	                </div>
	            </div>
	
			
			</article>
			<!-- //contents End -->
		</section>
		<!-- //#container End -->
	</div>
	<!-- //#wrap End -->
	<%--<iframe name="HiddenIFrame"  width="0" height="0"></iframe> --%>
	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>
