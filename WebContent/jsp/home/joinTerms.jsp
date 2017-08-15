<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="/jsp/common/resource.jsp"%>
	<script>
		$(function(){
			var $termsForm = $("#terms_form");
			$termsForm.validate($V.preset(["terms1", "terms2"]));

			$("#next_step").click(function(e){ //다음 버튼 클릭
				if($termsForm.valid()){
					$termsForm.submit();
				}
				return false;
			});
				
			$("#privacy").click(function(e){
//				window.open("/html/privacy.html", "", "scrollbars=yes, width=650, height=750");
				window.open("", "", "scrollbars=yes, width=650, height=750");
			});
		});
	</script>
</head>
<body>
<!-- #wrap// -->
	<div id="wrap">
		<%@include file="/jsp/common/header.jsp"%>

		<section id="container">
			<article class="title-2depth type-c">
				<div class="inner">
					<h2>회원가입</h2>
				</div>
			</article>
			<!-- //title-2depth End -->

			<article class="title-stepby">
				<div class="inner">
					<ol class="step-triple">
						<li class="on"><span>01. 회원가입 약관동의</span></li>
						<li><span>02. 회원정보 입력</span></li>
						<li><span>03. 회원정보 입력 완료</span></li>
					</ol>
				</div>
			</article>
			<!-- //title-stepby End -->

			<article class="contents member">
				<div class="inner">
					<div class="round-wrap">
<%-- 					<% if(h_loginChk){ %> --%>
<!-- 						<form id="terms_form" action="/agreeAll.do" method="post"> -->
<%-- 					<% }else{ %> --%>
<%-- 						<form id="terms_form" action="<%=h_ssl %>/joinForm.do?member_cd=${member_cd}" method="post"> --%>
<%-- 					<% } %> --%>
<!-- 							<div class="join-term"> -->
<!-- 								<section> -->
<!-- 									<h3>이용약관 확인</h3> -->
<!-- 									<div class="terms"> -->
<!-- 										iframe src="/html/member_join_terms_detail.html" style="overflow-x:hidden;width:100%;height:505px;"></iframe -->
<!-- 									</div> -->

<!-- 									<span class="check"> -->
<!-- 										<input type="checkbox" id="terms1" name="terms1"/> -->
<!-- 										<label for="terms1">이용약관 내용을 숙지하였으며, 이용약관에 동의합니다. <span>(약관에 동의하셔야만 회원가입을 하실 수 있습니다.)</span></label> -->
<!-- 									</span> -->
<!-- 								</section> -->

<!-- 								<section> -->
<!-- 									<h3>개인정보 수집 및 이용에 대한 동의</h3> -->
<!-- 									<a href="#none" id="privacy" class="btn-gray">개인정보 처리방침 전문보기</a> -->
<!-- 									<div class="terms"> -->
<!-- 										iframe src="/html/member_join_privacy_detail.html" style="overflow-x:hidden;width:100%;height:505px;"></iframe -->
<!-- 									</div> -->

<!-- 									<span class="check"> -->
<!-- 										<input type="checkbox" id="terms2" name="terms2"/> -->
<!-- 										<label for="terms2">개인정보 수집 및 이용에 대한 내용을 숙지하였으며, <br />개인정보 수집 및 이용에 동의합니다. <span>(약관에 동의하셔야만 회원가입을 하실 수 있습니다.)	</span></label> -->
<!-- 									</span> -->
<!-- 								</section> -->
<!-- 							</div> -->
<!-- 						</form> -->
<!-- 						<div class="btn-area"> -->
<%-- 							<a href="#none" id="next_step" class="btn-bold">${h_loginChk eq 'true' ? "확인" : "다음단계로 이동"}</a> --%>
<%-- 							<% if(!h_loginChk){ %> --%>
<!-- 							<a href="/" class="btn-normal" title="취소">취소</a> -->
<%-- 							<% } %> --%>
<!-- 						</div> -->
					</div>
				</div>
			</article>
			<!-- //contents End -->
		</section>
		<!-- //#container End -->
	</div>
	<!-- //#wrap End -->

	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>