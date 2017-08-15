<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%-- <% --%>
// boolean isDemoId = false;

// if( mUser != null ) {
// 	pageContext.setAttribute("h_custId", mUser.getCustId());
// 	pageContext.setAttribute("h_loginId", mUser.getLoginId());
// }
<%-- %> --%>
<!-- <script> -->
// $(document).ready(function(){
// 	$('.gnb > ul > li').mouseenter(function(e) {
// 		$(this).addClass('on');
// 		$(this).find('.sub-menu').css('display','block');
// 	}).mouseleave(function(e) {
// 		$(this).removeClass('on');
// 		$(this).find('.sub-menu').css('display','none');
// 	});
	
// });
// var startSubmitted = false;

// function manual_test(){
<%-- 	<% if( mUser != null ) {%> --%>
// 	if(startSubmitted){
// 		return;
// 	}
	
// 	$.ajax({
// 		url: location.protocol + "//" + location.host + "/login/appTestProc.do",
// 		type: "POST",
// 		dataType : "json",
// 		data :  {email: "${h_loginId}"} ,
// 		success: function(data) {
// 			if(data.code == "00"){
// 				callJnlp("${h_custId}", "", "", "ko", "KR");
// 			}else if(data.code == "01"){
// 				alert("승인 대기중입니다.");
// 			}else if(data.code == "02"){
// 				alert("승인 거절되었습니다.\n관리자에게 문의하십시오.");
// 			}else if(data.code == "03"){
// 				alert("사용 중지된 계정입니다.\n관리자에게 문의하십시오.");
// 			}else if(data.code == "04"){
// 				if(confirm("이미 실행 중인 1Q MTCenter 서비스가 있습니다. \n재 실행 하시겠습니까?")){
// 					callJnlp("${h_custId}", "", "", "ko", "KR");	
// 				}
// 			}else if(data.code == "06"){
// 				alert("1Q MTCenter 최초 로그인시 비밀번호를 변경하셔야 테스트 시작 등 서비스를 정상적으로 이용하실 수 있습니다.\nMy page > 회원정보 메뉴에서 비밀번호를 꼭 변경해 주시기 바랍니다.");
// 				$(location).attr('href','/myInfo/modMyInfo.do');
// 			}else{
// 				alert("테스트를 시작하실 수 없습니다.\n 관리자에게 문의하시기 바랍니다.");
// 			}
// 		},
// 		error: function(err){
// 			alert("장시간 사용하지 않아 자동 로그아웃 되었습니다.\n 로그인 후 다시 이용해주세요.");
// 			$(location).attr('href','/logout/logout.do');
// 		}
// 	});
	
// 	startSubmitted = true;
// 	setTimeout('resetStartSubmitted()',5000);
<%-- 	<% } else {%> --%>
// 	alert("로그인 후 이용가능합니다.");
// 	location.href = "/login/loginForm.do";
<%-- 	<% }%> --%>
// }

// function resetStartSubmitted(){
// 	startSubmitted = false;
// }
<!-- </script> -->

<!-- <div id="head" class="header-wrap"> -->
<!-- 			<div class="header" > -->
				
<!-- 				top -->
<!-- 				<div class="top-ui"> -->
<%-- 					<% if( mUser != null ) {  --%>
// 						isDemoId = "D".equals(mUser.getDemoCustTp());
<%-- 					%> --%>
<!-- 						<ul class="login-on"> -->
<%-- 							<li class="user-name"><span class="str"><% out.println(mUser.getCustNm()); %></span> 님</li> --%>
<%-- 							<% if(isDemoId == false) { %> --%>
<%-- 							<%} %> --%>
<!-- 							<li class="logoff"><a href="/logout/logout.do">로그아웃</a></li> -->
<!-- 						</ul> -->
<%-- 					<% }else{ %> --%>
<!-- 						<ul> -->
<!-- 							<li><a href="/login/loginForm.do">로그인</a></li> -->
<%-- 							<% if(isDemoId == false) { %> --%>
<!-- 							<li><a href="/joinForm.do?member_cd=P">회원가입</a></li> -->
<%-- 							<%} %> --%>
<!-- 						</ul> -->
<%-- 					<% } %> --%>
<!-- 				</div> -->
<!-- 				top end -->
				
<!-- 				logo -->
<!-- 				<div class="logo-ui"> -->
<!-- 					<a href="/"> -->
<!-- 						<img src="/images/qc/logo.png" alt="하나금융그룹 품질관리"/> -->
<!-- 					</a> -->
<!-- 				</div> -->
<!-- 				logo end -->
				
<!-- 				gnb menu -->
<!-- 				<div class="gnb"> -->
<!-- 					<ul> -->
<%-- <% --%>
// 	if( fIsPageFunc( "QC_MENU_PLAN" ) ) {
// 		out.write( "						<li>\n" );
// 		out.write( "							<a href=\"javascript:void(0)\">\n" );
// 		out.write( "								테스트 계획\n" );
// 		out.write( "							</a>\n" );
// 		out.write( "							<div class=\"sub-menu\" style=\"display:none\">\n" );
// 		out.write( "								<ul>\n" );
// 		if( fIsPageFunc( "QC_Tp_s_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/plan/ScenarioStateList.do\">시나리오 POOL</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_P_l_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/plan/ProjectInfoList.do\">프로젝트 관리</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_Tn_l_PAGE_READ" ) ) {
// 			out.write( "										<li><a href=\"/qc/plan/TestNumberInfoList.do\">테스트 차수 관리</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_Dt_l_PAGE_READ" ) ) {
// 			out.write( "										<li><a href=\"/qc/plan/TestNumberAssignTesterList.do\">차수별 테스터 관리</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_Ds_s_PAGE_READ" ) ) {
// 			out.write( "										<li><a href=\"/qc/plan/TestNumberScenarioStateList.do\">차수별 시나리오 관리</a></li>\n" );
// 		}
// 		out.write( "								</ul>\n" );
// 		out.write( "							</div>\n" );
// 		out.write( "						</li>\n" );
// 	}
// 	if( fIsPageFunc( "QC_MENU_DESIGN" ) ) {
// 		out.write( "						<li><a href=\"javascript:void(0)\">테스트 설계</a>\n" );
// 		out.write( "							<div class=\"sub-menu\" style=\"display:none\">\n" );
// 		out.write( "								<ul>\n" );
// 		if( fIsPageFunc( "QC_Ts_ts_c_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/design/TesterAssignScenarioStatusList.do\">테스터별 시나리오 배정</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_St_ts_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/design/ScenarioAssignTesterStatusList.do\">시나리오별 테스터 배정</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_Sd_sd_s_PAGE_READ" ) ) {
// //			out.write( "									<li><a href=\"/qc/design/ScenarioDeviceAssignList.do\">시나리오별 단말기 배정</a></li>\n" );
// 			out.write( "									<li><a href=\"/qc/design/ScenarioTesterDeviceAssignList.do\">시나리오별 단말기 배정</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_Sch_l_PAGE_READ" ) ) {
// 			out.write( "										<li><a href=\"/qc/plan/TesterPlanStateList.do\">상세일정 관리</a></li>\n" );
// 		}
// 		if( true ) {		// 모든 사용자 이용가능
// 			out.write( "									<li><a href=\"/qc/design/DesignTestList.do\">테스트 데이터</a></li>\n" );
// 		}
// 		out.write( "								</ul>\n" );
// 		out.write( "							</div>\n" );
// 		out.write( "						</li>\n" );
// 	}
// 	if( fIsPageFunc( "QC_MENU_EXECUTE" ) ) {
// 		out.write( "						<li><a href=\"javascript:void(0)\">테스트 실행</a>\n" );
// 		out.write( "							<div class=\"sub-menu\" style=\"display:none\">\n" );
// 		out.write( "								<ul>\n" );
// 		if( fIsPageFunc( "QC_Pd_l_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/execute/ProjectAndTestNumberList.do\">프로젝트 및 테스트 차수</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_Ssh_she_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/execute/SchedulePlanStateList.do\">시나리오별 일정계획</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_Ex_l_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/execute/ExecuteScenarioList.do\">실행내역</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_Sex_s_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/execute/SequenceExecuteStatus.do\">시퀀스 실행 현황</a></li>\n" );
// 		}
// 		out.write( "								</ul>\n" );
// 		out.write( "							</div>\n" );
// 		out.write( "						</li>\n" );
// 	}
// 	if( fIsPageFunc( "QC_MENU_DEFECT" ) ) {
// 		out.write( "						<li><a href=\"javascript:void(0)\">결함 관리</a>\n" );
// 		out.write( "							<div class=\"sub-menu\" style=\"display:none\">\n" );
// 		out.write( "								<ul>\n" );
// 		if( fIsPageFunc( "QC_D_l_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/defect/DefectList.do\">결함내역</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_Dd_s_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/defect/DefectTestNumberStatus.do\">결함현황</a></li>\n" );
// 		}
// /* 		if( fIsPageFunc( "QC_Td_s_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/defect/DefectTesterStatus.do\">테스터별 결함현황</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_Pd_s_PAGE_READ" ) ) {	
// 			out.write( "									<li><a href=\"/qc/defect/DefectDeveloperStatus.do\">조치자별 결함현황</a></li>\n" );
// 		} 
// */
		
// 		out.write( "								</ul>\n" );
// 		out.write( "							</div>\n" );
// 		out.write( "						</li>\n" );
// 	}
// 	if( fIsPageFunc( "QC_MENU_STATE" ) ) {
// 		out.write( "						<li><a href=\"javascript:void(0)\">테스트 현황</a>\n" );
// 		out.write( "							<div class=\"sub-menu\" style=\"display:none\">\n" );
// 		out.write( "								<ul>\n" );
// 		if( fIsPageFunc( "QC_S1_S_l_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/status/TestStatusDashboard.do\">테스트 진척/결함 현황</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_S1_S_l_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/status/DeviceStatusDashboard.do\">단말기 현황</a></li>\n" );
// 		}
// 		if( fIsPageFunc( "QC_S1_S_l_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/status/TestNumberByStatusList.do\">테스트 수행결과</a></li>\n" );
// 		}
// 		out.write( "								</ul>\n" );
// 		out.write( "							</div>\n" );
// 		out.write( "						</li>\n" );
// 	}
// 	if( fIsPageFunc( "QC_MENU_ADMIN" ) ) {
// 		out.write( "						<li><a href=\"javascript:void(0)\">운영관리</a>\n" );
// 		out.write( "							<div class=\"sub-menu\" style=\"display:none\">\n" );
// 		out.write( "								<ul>\n" );
// 		if( fIsPageFunc( "QC_App_l_PAGE_READ" ) ) {
// 			out.write( "									<li><a href=\"/qc/manage/AppInfoList.do\">앱 관리</a></li>\n" );
// 		}
// 		out.write( "								</ul>\n" );
// 		out.write( "							</div>\n" );
// 		out.write( "						</li>\n" );
// 	}
<%-- %> --%>
<!-- 					</ul> -->
<!-- 				</div> -->
<!-- 				gnb menu end -->
				
			
<!-- 				home-btn -->
<!-- 				<div class="home-btn"> -->
<!-- 					<a href="/"> -->
<!-- 						<img src="/images/qc/home_icon.png" alt="홈으로 가기"/> -->
<!-- 					</a> -->
<!-- 				</div> -->
<!-- 				home-btn end -->
				
<!-- 				test-start -->
<!-- 				<div class="test-start-btn" onclick="manual_test()">테스트 시작</div> -->
<!-- 				test-start end -->
				
<!-- 			</div> -->
<!-- 		</div> -->