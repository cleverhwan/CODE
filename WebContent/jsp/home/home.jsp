<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%
	List<Map>	bbsList = (List<Map>)request.getAttribute("bbs_list");
	List<Map>	forumList = (List<Map>)request.getAttribute("forum_list");
	String title="";
	String category="";
	
	//String now = DateUtil.getCurrDate("");
	String		popYn = (String)request.getAttribute("pop_yn");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="/jsp/common/resource.jsp"%>
	<link rel="stylesheet" type="text/css" href="/css/main.css"/>
	
	<script type="text/javascript">
	<c:if test="${!empty mod_yn}">
	//alert("1Q MTCenter 최초 로그인시 비밀번호를 변경하셔야 테스트 시작 등 서비스를 정상적으로 이용하실 수 있습니다.\nMy page > 회원정보 메뉴에서 비밀번호를 꼭 변경해 주시기 바랍니다.");
	$(location).attr('href','/myInfo/modMyInfo.do');
	</c:if>
	
	
	$(document).ready(function() {													
		openWin('pop_notice');										
	});
	
	// 창열기  
	function openWin( winName ) {  
	   var pop_notice    = getCookie( winName );  
	   //var obj = eval( "window." + winName );  
	//	   alert(blnCookie);
// 		if( pop_notice != "done") {
// 	       //obj.style.display = "block";
<%-- 	       if ("Y" == "<%=popYn%>") { --%>
<%-- 	    	   var url = "<%=h_http%>/noticePopup.do"; --%>
// 	   			//var opt = "left=0,top=0,width=390,height=480"
// 	   			var opt = "width=440,height=540"
// 	   				+ ",toolbar=no,menubar=no,location=no,scrollbars=no,status=no,resizable=no,fullscreen=no";
// 	   			var winObj = window.open(url, "win_noticePopup", opt);
// 	   		}
// 	   }  
	}
	
	// 쿠키 가져오기  
	function getCookie( name ) {  
	   var nameOfCookie = name + "=";  
	   var x = 0;  
	   while ( x <= document.cookie.length )  
	   {  
	       var y = (x+nameOfCookie.length);  
	       if ( document.cookie.substring( x, y ) == nameOfCookie ) {  
	           if ( (endOfCookie=document.cookie.indexOf( ";", y )) == -1 )  
	               endOfCookie = document.cookie.length;  
	           return unescape( document.cookie.substring( y, endOfCookie ) );  
	       }  
	       x = document.cookie.indexOf( " ", x ) + 1;  
	       if ( x == 0 )  
	           break;  
	   }  
	   return "";  
	}  
	
	</script>
</head>
<body>
<!-- #wrap// -->
	<div id="wrap">
		<%@include file="/jsp/common/header.jsp"%>

		<section id="container">

			<article class="contents">

				<div class="main-contbox01">
					<div class="inbox01">
						<dl class="main-text">
							<!-- dt>당신이 바라는 모바일 테스트<br />1Q MTCenter와 함께 하세요</dt>
							<dd>다양한 단말기에서 모바일 서비스를 쉽고 효과적으로 테스트할 수 있습니다.</dd -->
						</dl>
					</div>
				</div>
				<!--  
				<div class="main-contbox02">
					<div class="inbox01">
						<dl class="main-text02">
							<dt>언제 어디서든<br />인터넷을 통해 단말기에 접속하는<br />새로운 차원의 모바일 테스트 서비스</dt>
							<dd>인터넷을 통해 실제 단말기에 접속하여 테스트를 할 수 있으며,<br />자동화 툴을 활용하여 업무 생산성을 높일 수 있습니다.</dd>
						</dl>
						<div class="contbox2-btnwrap">
							<a href="/intro/introduce.do" class="main-btn">1Q MTCenter 소개</a>
						</div>
					</div>
				</div>

				<ul class="main-contbox03">
					<li>
						<div class="inbox01">
							<p class="contbox3-stxt">업무의 신속성과 서비스 품질 확보 및 비용 절감의 기회 제공</p>
							<dl class="main-text03">
								<dt>실제 단말기와<br />동일한 테스트 환경</dt>
								<dd>OS(Android/iOS), 버전, 해상도, 제조사 별 단말기를 제공하여<br />실제와 동일한 환경에서 테스트를 수행할 수 있습니다.</dd>
							</dl>
						</div>
					</li>
					<li>
						<div class="inbox02">
							<dl class="main-text03">
								<dt>여러 대의 단말기를<br />동시에 사용 가능</dt>
								<dd>매뉴얼 테스트, 자동화 테스트를 사용하여 테스트 시간을 단축하고<br />보다 효과적인 테스트가 가능합니다.</dd>
							</dl>
						</div>
					</li>
					<li>
						<div class="inbox03">
							<dl class="main-text03">
								<dt>OS 별, 하나의 스크립트로<br />다양한 단말기 테스트</dt>
								<dd>동일 OS 내 하나의 스크립트로<br />서로 다른 버전, 해상도, 제조사 등에 상관없이 테스트가 가능합니다. </dd>
							</dl>
						</div>
					</li>
					<li>
						<div class="inbox04">
							<dl class="main-text03">
								<dt>자동화 테스트로<br />업무 생산성 향상</dt>
								<dd>사용하기 쉬운 자동화 전용 툴을 제공하여 업무 생산성을 높일 수 있으며<Br />일관된 높은 품질관리가 가능합니다.</dd>
							</dl>
						</div>
					</li>
				</ul>
				-->
				<div class="main-contbox04">
					<div class="main-cont04">
						<div class="notice-wrap">
							<p class="tit">공지사항</p>
							<ul class="list">
<%-- 							<% if (bbsList != null && bbsList.size() > 0) { --%>
// 								for (Map map : bbsList) {
// 									title = (String)map.get("TITLE");
									
// 									String regDt = (map.get("REG_DT") + "").replace("-", "/");
									
// 									String newClass = "";
// 									if(Integer.parseInt(now) <= Integer.parseInt(regDt.replace("/",""))){
// 										newClass = "class='new'"; 
// 									}
<%-- 							%> --%>
<%-- 								<li <%=newClass %> > --%>
<%-- 									<a href="/bbs/view.do?bbs_typ=NOTICE&bbs_id=<%=map.get("BBS_ID")%>" > --%>
<%-- 										<p class="list-tit"><%=title%></p> --%>
<!-- 										<div class="list-datenew"> -->
<%-- 											<p class="date-wrap"><%=regDt%></p> --%>
<!-- 											<p class="new-wrap">NEW</p> -->
<!-- 										</div> -->
<!-- 									</a> -->
<!-- 								</li> -->
<%-- 							<%	} --%>
<%-- 								} %> --%>

								<li>
									<a href="/bbs/list.do?bbs_typ=NOTICE">
										<span class="more">더보기</span>
									</a>
								</li>
							</ul>
						</div>
						<div class="talk-wrap">
							<p class="tit">지식톡톡</p>
							<ul class="list">
<%-- 							<% if (forumList != null && forumList.size() > 0) { --%>
// 								for (Map map : forumList) {
// 									title = (String)map.get("TITLE");
									
// 									if(map.get("CATEGORY") != null && !"".equals(map.get("CATEGORY"))){
// 										if(map.get("CATEGORY2") != null && !"".equals(map.get("CATEGORY2"))){
// 											category = "["+map.get("CATEGORY")+"] ["+map.get("CATEGORY2")+"]<br />";
// 										}else{
// 											category = "["+map.get("CATEGORY")+"]<br />";
// 										}
// 									}else{
// 										if(map.get("CATEGORY2") != null && !"".equals(map.get("CATEGORY2"))){
// 											category = "["+map.get("CATEGORY2")+"]<br /> ";
// 										}
// 									}
									
// 									String regDt = (map.get("REG_DT") + "").replace("-", "/");

// 									String newClass = "";
// 									if(Integer.parseInt(now) <= Integer.parseInt(regDt.replace("/",""))){
// 										newClass = "class='new'"; 
// 									}
<%-- 							%> --%>
<%-- 								<li <%=newClass %> > --%>
<%-- 									<a href="/bbs/view.do?bbs_typ=FORUM&bbs_id=<%=map.get("BBS_ID")%>" > --%>
<%-- 										<p class="list-tit"><%=category%> <%=title%></p> --%>
<!-- 										<div class="list-datenew"> -->
<%-- 											<p class="date-wrap"><%=regDt%></p> --%>
<!-- 											<p class="new-wrap">NEW</p> -->
<!-- 										</div> -->
<!-- 									</a> -->
<!-- 								</li> -->
<%-- 							<%	} --%>
<%-- 								} %> --%>
								<li>
									<a href="/bbs/list.do?bbs_typ=FORUM">
										<span class="more">더보기</span>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>

				<!--ul class="fixedlist-wrap">
					<li><span class="fixex01">선택</span></li>
					<li><span class="fixex02">선택</span></li>
					<li><span class="fixex03">선택</span></li>
					<li><span class="fixex04">선택</span></li>
				</ul -->
				
				
				
<script type="text/javascript">
$(document).ready(function(){
	var $removeC =  $('.fixedlist-wrap li span');
	var $popIn =  $('.popup-inwrap');
	$(document).scrollTop('0');
	$('.fixex01').addClass('current');
	$(".fixex01").on("click", function() {
		$popIn.removeClass('position-type01');
		$popIn.removeClass('position-type02');
		$removeC.removeClass('current');
		$('.fixex01').addClass('current');
		$(document).scrollTop('128');
	});
	$(".fixex02").on("click", function() {
		$removeC.removeClass('current');
		$('.fixex02').addClass('current');
		$(document).scrollTop('873');
	});
	$(".fixex03").on("click", function() {
		$popIn.removeClass('position-type01');
		$popIn.removeClass('position-type02');
		$removeC.removeClass('current');
		$('.fixex03').addClass('current');
		$(document).scrollTop('1833');
	});
	$(".fixex04").on("click", function() {
		$popIn.removeClass('position-type01');
		$popIn.removeClass('position-type02');
		$removeC.removeClass('current');
		$('.fixex04').addClass('current');
		$(document).scrollTop('5458');
	});
});
$(window).scroll(function () {
	var $dscrollTop  = $(document).scrollTop();
	var $removeC =  $('.fixedlist-wrap li span');
	var $popIn =  $('.popup-inwrap');
	if ($dscrollTop > 0 && $dscrollTop < 873 ) {
		$removeC.removeClass('current');
		$('.fixex01').addClass('current');
		$popIn.removeClass('position-type01');
		$popIn.removeClass('position-type02');
	} else if ($dscrollTop > 874 && $dscrollTop < 1307 ) {
		$removeC.removeClass('current');
		$('.fixex02').addClass('current');
		$popIn.removeClass('position-type02');
		$popIn.addClass('position-type01');
	} else if ($dscrollTop > 1308 && $dscrollTop < 1833 ) {
		$removeC.removeClass('current');
		$('.fixex02').addClass('current');
		$popIn.removeClass('position-type01');
		$popIn.addClass('position-type02');
	} else if ($dscrollTop > 1834 && $dscrollTop < 5458 ) {
		$popIn.removeClass('position-type01');
		$popIn.removeClass('position-type02');
		$removeC.removeClass('current');
		$('.fixex03').addClass('current');
	} else if ($dscrollTop > 5459 && $dscrollTop < 6418 ) {
		$popIn.removeClass('position-type01');
		$popIn.removeClass('position-type02');
		$removeC.removeClass('current');
		$('.fixex04').addClass('current');
	}
});
</script>


			</article>
			<!-- //contents End -->

		</section>
		<!-- //#container End -->
	</div>
	<!-- //#wrap End -->

	<%@include file="/jsp/common/footer.jsp"%>
</body>
</html>