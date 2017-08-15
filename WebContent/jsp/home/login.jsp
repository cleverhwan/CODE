<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%
	String resultFlag = StringUtil.defaultString((String)request.getAttribute("resultFlag"), "");
	String resultCd = StringUtil.defaultString((String)request.getAttribute("resultCd"), "");
	String resultMsg = "";
// 	if(!"".equals(resultCd)){
// 		resultMsg = MtcMessageHandler.getUserMessage(resultCd);	
// 	}
	pageContext.setAttribute("resultMsg", resultMsg);
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="/jsp/common/resource.jsp"%>
	<script>
		$(function(){
		<c:if test="${resultMsg != ''}">
			if("FAIL" === "${resultFlag}"){ //로그인 실패면
				alert("${resultMsg}");
			}
		</c:if>
			var $pid = $("#pid"); //이메일
			var $login = $("#login"); //로그인 버튼
			var $login_form = $("#login_form"); //로그인 폼
			var $checkMail = $("#check_mail"); //메일 저장 체크
			var $password = $("#password");
			var $company = $("#company"); //소속사
			
			if($.cookie("localSavePid") === ""){
				$pid.val("");
				$("#company option:eq(0)").attr("selected","selected");
				$checkMail.removeAttr("checked");
			}else{
				$pid.val($.cookie("localSavePid"));
				$("#company option[value="+$.cookie("localSaveCompany")+"]").attr("selected","selected");
				$checkMail.attr("checked", "");
			}
			
			$login.click(function(){ //로그인 버튼 클릭
				if($company.val().length === 0){
					alert("소속사 선택은 필수 선택 항목입니다.");
					return false;
				}

				if($pid.val().length === 0){
					alert("하나포탈 ID는 필수 입력 항목입니다.");
					$pid.focus();
					return false;
				}
				
				if($password.val().length === 0){
					alert("비밀번호는 필수 입력 항목입니다.");
					$password.focus();
					return false;
				}
				
				if($login_form.valid()){
					if($checkMail.prop("checked") && $.cookie("localSavePid") == "" && $pid.val() !== ""){
						$.cookie("localSavePid", $pid.val(), {expires: 365, path: "/"});
					}
					
					if ($checkMail.prop("checked") && $.cookie("localSavePid") !== "" && $.cookie("localSavePid") !== $pid.val()){
						$.cookie("localSavePid", $pid.val(), {expires: 365, path: "/"});
					}
					
					if($checkMail.prop("checked") && $.cookie("localSaveCompany") == "" && $company.val() !== ""){
						$.cookie("localSaveCompany", $company.val(), {expires: 365, path: "/"});
					}
					
					if ($checkMail.prop("checked") && $.cookie("localSaveCompany") !== "" && $.cookie("localSaveCompany") !== $company.val()){
						$.cookie("localSaveCompany", $company.val(), {expires: 365, path: "/"});
					}

					$login_form.submit();
				}
				return false;
			});
			
			$login_form.keypress(function(e){
				if($pid.val().length === 0 && e.keyCode === 13){
					$pid.focus();
				}
				
				if($password.val().length === 0 && e.keyCode === 13){
					$password.focus();
				}
				
				if($pid.val().length > 0 && $password.val().length > 0 && e.keyCode === 13){
					$login.trigger("click");
				}
			});
			
			$checkMail.click(function(){ //메일 저장 클릭시
				checkSavePid($(this), $pid);
			});
			
			function checkSavePid($checkbox, $pid){
				if($checkbox.prop("checked")){
					$.cookie("localSavePid", $pid.val(), {expires: 365, path: "/"});
					$.cookie("localSaveCompany", $company.val(), {expires: 365, path: "/"});
				}else{
					$.cookie("localSavePid", "", {expires: 365, path: "/"});
					$.cookie("localSaveCompany", "", {expires: 365, path: "/"});
				}
			}
		}); //end: $(function(){
			
		function pwAlert(){
			alert("비밀번호 초기화 문의는 담당자에게 연락바랍니다. \n\n연락처 : 02-2151-6450");
		}
	</script>
</head>
<body>
<!-- #wrap// -->
	<div id="wrap">
		<%@include file="/jsp/common/header.jsp"%>
		
		<section id="container">
			<article class="title-2depth type-d">
				<div class="inner">
					<h2>로그인</h2>
				</div>
			</article>
			<!-- //title-2depth End -->

			<article class="contents login">
				<div class="inner">
					<div class="login_form">
						<fieldset>
							<form id="login_form" action="/login/loginWeb.do" method="post">
							<div>
							<select name="company" id="company" title="소속사" class="select login-select">
								<option value="">소속사를 선택해주세요.</option>
								<c:forEach items="${companyGroupList}" var="corps">
									<option value="${corps.CODE}">${corps.VALUE}</option>
								</c:forEach>
							</select>
							</div>
							<input type="text" id="pid" name="pid" class="input-text mt8" title="사용자 ID" maxlength="7" placeholder="하나포탈 ID(사번)를 입력해 주세요" />
							<input type="password" id="password" name="password" class="input-text mb5" title="비밀번호" maxlength="20" placeholder="비밀번호를 입력해 주세요" />
							<span class="point">초기 비밀번호는 ${defaultPw}입니다</span>
							<span class="check">
								<input type="checkbox" id="check_mail" name="check_mail" />
								<label for="check_mail">사용자 ID 기억</label>
							</span>
							</form>
							<button type="submit" id="login" class="btn-red">로그인</button>
							<div class="btn-area">
								<a href="/joinForm.do?member_cd=H" class="btn-gray">회원가입</a>
								<a href="javascript:pwAlert()" class="btn-gray">비밀번호 초기화</a>
							</div>
						</fieldset>
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