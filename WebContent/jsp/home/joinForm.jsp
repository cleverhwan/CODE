<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="/jsp/common/resource.jsp"%>
	<script type="text/javascript" src="/js/jquery.ezfile.js"></script>
	<script type="text/javascript" src="/js/jquery-ui.min.js"></script>
	<script>
		$(function(){
			$( "#start_dt, #end_dt" ).datepicker({
				showOn: 'button',
				buttonText:"일자선택",
				dateFormat: 'yy-mm-dd',
				prevText: '이전달',
				nextText: '다음달',
				monthNames: ['01','02','03','04','05','06','07','08','09','10','11','12'],
				monthNamesShort: ['01','02','03','04','05','06','07','08','09','10','11','12'],
				dayNames: ['일','월','화','수','목','금','토'],
				dayNamesShort: ['일','월','화','수','목','금','토'],
				dayNamesMin: ['SUN','MON','TUE','WED','THU','FRI','SAT'],
				showOtherMonths: true,
				showMonthAfterYear: true,
				yearSuffix: '.',
				minDate:'-0d'
			});
			
			$( "#join_company" ).change(function(){
				if($('#join_company').val() === "99"){

					var roleHtml = $( "<span class=\"check _span_role_06\"><input type=\"checkbox\" id=\"role_06\" name=\"check_role\" value=\"06\" class=\"check_role\"><label for=\"role_06\">운영자</label></span>" );
					$( "._li_role" ).append( roleHtml );
				} else {
					$( "._span_role_06" ).remove();
				}
			});

			var $join_form = $("#join_form");
			var $join = $("#join");
			var $btnCheckEmail = $("#btnCheckEmail");
			var $passDuplicateCheck = $("#passDuplicateCheck");
			var $smsAuthId = $("#smsAuthId");
			var $smsAuthName = $("#smsAuthName");
			var presetNames = ["join_id", "join_nm", "join_position", "join_company", "join_team", "start_dt", "end_dt", "role", "start_dt", "end_dt"];
			
			/*
			//이메일 중복체크
			$btnCheckEmail.click(function(){
				if($("#new_email").val()==""){
					alert("이메일을 입력해주시기 바랍니다.");
					return;
				}
			
				"/join/checkDuplicatedLoginId.do".locate({
					param: {loginId: $("#new_email").val()}
					, done: function(data){
						if(data.isDuplicatedId){
							alert("이미 가입된 이메일 입니다.");
							$("#new_email").val("");
							$passDuplicateCheck.val("false"); //이메일 중복 체크 안됨 
						}else{
							alert("이메일 사용이 가능합니다.");
							$passDuplicateCheck.val("true"); //이메일 중복 체크 됨
						} 
					}
					, fail: function(){
						alert("알 수 없는 오류로 인해 이메일 중복체크가 실패하였습니다.\n다시 한번 더 시도해 주세요.");
					}
				});
			});
			//이메일 중복체크
			*/
			
			$passDuplicateCheck.val("true");

			var preset = $V.preset(presetNames);
			$join_form.validate(preset);

			$join.click(function(){ //다음 단계 버튼 클릭
				if($join_form.valid()){
					var valid = true;
					
					/*
					if($passDuplicateCheck.val() === "false"){
						valid = false;
						alert("이메일 중복확인을 하셔야 회원가입이 가능합니다.");
					}
				
					if($smsAuthId.val() !== "F"){
						valid = false;
						alert("휴대폰인증을 받으셔야 합니다.");
					}else{
						if($smsAuthName.val() !== $("#join_name").val()){
							valid = false;
							alert("휴대폰 명의자가 아닙니다. 휴대폰 명의자의 이름을 입력해주세요.");
						}
					}
					*/

					var start_date_arr = $("#start_dt").val().split("-");
					var end_date_arr = $("#end_dt").val().split("-");
					var start_date = start_date_arr[0] + start_date_arr[1] + start_date_arr[2];
					var end_date = end_date_arr[0] + end_date_arr[1] + end_date_arr[2];
					
					if(end_date-start_date < 0){
						valid = false;
						alert("종료일이 시작일 보다 빠를 수 없습니다.");
					}
					
// 					if(valid){
// 						"/join/joinProc.do".locate({ //비동기 가입 처리
// 							param: $join_form.serialize()
// 							, done: function(data){
<%-- 								if(data.resultCd === "<%=JoinUtil.RESULT_CD_CMP%>"){												 --%>
// //									alert("정상가입완료되었습니다.");
// 									$join_form.submit(); //페이지 전환
// 									return false;
<%-- 								}else if(data.resultCd === "<%=JoinUtil.RESULT_CD_EXT%>"){ --%>
// 									alert("이미 가입되어 있습니다.");
// 								}else{
// 									alert(data.resultMsg || "회원가입이 실패하였습니다.\n관리자에게 문의 바랍니다.");
// 								}
// 							}
// 							, fail: function(){
// 								alert("회원가입이 실패하였습니다.\n관리자에게 문의 바랍니다.");
// 							}
// 						});
// 					}
// 				}else{
// 					return false;
// 				}
// 			});
			
			$(document).off("click",".check_role").on("click",".check_role", function(){ 
				var chkedVal = "";
				
				$(":checkbox[name='check_role']:checked").each(function(pi,po){
					chkedVal += ","+po.value;
				});
				
				if(chkedVal!="") chkedVal = chkedVal.substring(1);
				
				$("#role").val(chkedVal);
			});
			
			$("#join_id").focusout(function(){
				replaceId();
			});
		});
		
		function inputKeyCode(event){
			event = event || window.event;
			var keyID = (event.which) ? event.which : event.keyCode;
			if((keyID >= 48 && keyID <= 57) || keyID == 8 || keyID == 9 || keyID == 37 || keyID == 39 || keyID == 46) {
				// 8:백스패이스, 9:탭, 37:좌방향키, 39:우방향키, 46:딜리트
				return true;
			} else{
				event.returnValue=false;
				
				var objValue = $("#join_id").val().replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g,'');
				$("#join_id").val(objValue)
				
				return false;
			}
		}
		
		function keyPress(event){
			if(event.keyCode < 48 || event.keyCode > 57) {
				return false;
			}
		}
		
		function keyDown(event){
			replaceId();
			if(event.keyCode == 8 || event.keyCode == 9 || event.keyCode == 37 || event.keyCode == 39 || event.keyCode == 46) {
				// 8:백스패이스, 9:탭, 37:좌방향키, 39:우방향키, 46:딜리트
				return true;
			}
		}
		
		function replaceId(){
			var objValue = $("#join_id").val().replace(/[\ㄱ-ㅎㅏ-ㅣ가-힣]/g,'');
			$("#join_id").val(objValue);
			
		}
		
		function checklength(){
		 	var content = document.getElementById('comment');
		 	var content_value = document.getElementById('comment').value;
		 	
		 	if(content_value.length>1000){
		 		alert("내용은 1000자 이내여야 합니다.");
		 		content.value = fnCut(content.value,1000);
		 		return false;
			}
			return true;
		}
		
		function fnCut(str,lengths) // str은 inputbox에 입력된 문자열이고,lengths는 제한할 문자수 이다.
		{
		      var len = 0;
		      var newStr = '';
		  
		      for (var i=0;i<str.length; i++) 
		      {
		        var n = str.charCodeAt(i); // charCodeAt : String개체에서 지정한 인덱스에 있는 문자의 unicode값을 나타내는 수를 리턴한다.
		        // 값의 범위는 0과 65535사이이여 첫 128 unicode값은 ascii문자set과 일치한다.지정한 인덱스에 문자가 없다면 NaN을 리턴한다.
		        
		       var nv = str.charAt(i); // charAt : string 개체로부터 지정한 위치에 있는 문자를 꺼낸다.
		       
		        if ((n>= 0)&&(n<256)) len ++; // ASCII 문자코드 set.
		        else len += 1; // 한글이면 2byte로 계산한다.
		        
		        if (len>lengths) break; // 제한 문자수를 넘길경우.
		        else newStr = newStr + nv;
		      }
		      return newStr;
		}
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
						<!--li><span>01. 회원가입 약관동의</span></li-->
						<li class="on"><span>01. 회원정보 입력</span></li>
						<li><span>02. 회원정보 입력 완료</span></li>
					</ol>
				</div>
			</article>
			<!-- //title-stepby End -->

			<article class="contents member">
				<div class="inner">
					<div class="round-wrap">
						<div class="input-form">
							<form name="form_sms_chk" method="post">
								<input type="hidden" name="m" value="checkplusSerivce"> <!-- 필수 데이타로, 누락하시면 안됩니다. -->
								<input type="hidden" name="EncodeData" value=""> <!-- 위에서 업체정보를 암호화 한 데이타입니다. -->
							</form>
						
<%-- 							<form id="join_form" action="/member_state.do?member_cd=${member_cd}" method="post"> --%>
<%-- 								<input type="hidden" id="paramReturnUrl" name="returnUrl" value="<%=MtcPropertyHandler.getMessage("email.returnUrl")%>"/> --%>
<%-- 								<input type="hidden" name="member_cd" id="member_cd" value="${member_cd}"/> --%>
<!-- 								<input type="hidden" name="smsAuthId" id="smsAuthId" value=""/> -->
<!-- 								<input type="hidden" name="smsAuthName" id="smsAuthName" value=""/> -->
<!-- 								<input type="hidden" name="passDuplicateCheck" id="passDuplicateCheck" value="false"/> -->
							

								<ul class="info_input">
									<li>
										<label class="tit" for="join_company">소속사 <span class="compulsory">필수</span></label>
										<select name="join_company" id="join_company" title="소속사" class="select w442">
											<option value="">선택해주세요.</option>
											<c:forEach items="${companyGroupList}" var="corps">
												<option value="${corps.CODE}">${corps.VALUE}</option>
											</c:forEach>
										</select>
									</li>
									<li>
										<label class="tit" for="join_id">사용자 ID(사번) <span class="compulsory">필수</span></label>
										<input type="text" name="join_id" id="join_id" class="input-text w442" placeholder="사용자 ID(사번)를 입력해 주세요" maxlength="7" onkeypress="return keyPress(event)" onkeydown="return keyDown(event)" style="ime-mode:disabled;"/>
									</li>
									<li>
										<label class="tit" for="join_nm">이름 <span class="compulsory">필수</span></label>
										<input type="text" name="join_nm" id="join_nm" class="input-text w442" placeholder="이름을 입력해 주세요" maxlength="20"/>
									</li>
									<li>
										<label class="tit" for="join_position">직위 <span class="compulsory">필수</span></label>
										<input type="text" name="join_position" id="join_position" class="input-text w442" placeholder="직위를 입력해 주세요" maxlength="10"/>
									</li>
									<li>
										<label class="tit" for="join_team">부서/팀명 <span class="compulsory">필수</span></label>
										<input type="text" name="join_team" id="join_team" class="input-text w442" title="부서/팀명" placeholder="부서/팀명을 입력해 주세요" maxlength="15"/>
									</li>
									<!--li>
										<label class="tit" for="join_phonenum_cert">휴대폰 번호 <span class="compulsory">필수</span></label>
										<input type="text" name="join_phonenum_cert" id="join_phonenum_cert" class="input-text" placeholder="휴대폰 번호를 입력해 주세요" />
									</li>
									<li>
										<label class="tit" for="new_email">이메일 주소 <span class="compulsory">필수</span></label>
										<input type="text" name="new_email" id="new_email" class="input-text" placeholder="이메일 주소를 입력해 주세요" />
										<a href="#none" id="btnCheckEmail" class="btn-gray">중복확인</a>
									</li-->
									<li class="purpose _li_role">
										<p class="tit">권한 <span class="compulsory">필수</span></p>
										<c:forEach items="${custRoleList}" var="corps">
											<c:if test="${corps.VALUE ne '운영자'}">
											<span class="check">
												<input type="checkbox" id="role_${corps.CODE}" name="check_role" value="${corps.CODE}" class="check_role"/>
												<label for="role_${corps.CODE}">${corps.VALUE}</label>
											</span>
											</c:if>
										</c:forEach>
									</li>
									<li>
										<label class="tit">사용기간 <span class="compulsory">필수</span></label>
										<div>
											<span class="datepicker w202"><input type="text" name="start_dt" id="start_dt" title="시작일" value="${today}" readonly="readonly"/></span>
											<span class="unit">~</span>
											<span class="datepicker w202"><input type="text" name="end_dt" id="end_dt" title="종료일" value="${today}" readonly="readonly"/></span>
										</div>
									</li>
									<li>
										<label class="tit">요청사항</label>
										<textarea id="comment" class="textarea" rows="17" cols="30" name="comment" placeholder="내용을 입력하세요" onkeyup='javascript:checklength();'>${detail.CONTENT}</textarea>
									</li>
								</ul>
								<input type="hidden" name="role" id="role" value=""/>
							</form>
						</div>
						<div class="btn-area">
							<a id="join" href="#none" class="btn-bold">다음단계로 이동</a>
							<a href="/" class="btn-normal" title="취소">취소</a>
						</div>
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