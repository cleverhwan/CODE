<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<% response.setStatus(200); %>
<!DOCTYPE html>
<html lang="ko">
<head>
<title> T benefit > 에러안내 페이지(accessDenied))</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css" href="/common/css/sub.css" />
<script type="text/javascript" src="/common/js/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="/common/js/jquery.easing.1.3.js"></script>
<script type="text/javascript" src="/common/js/greensock/TweenMax.min.js"></script>
<script type="text/javascript" src="/common/js/greensock/plugins/CSSPlugin.min.js"></script>
<script type="text/javascript" src="/common/js/greensock/easing/EasePack.min.js"></script>
<script type="text/javascript" src="/common/js/modernizr.js"></script>
<script type="text/javascript" src="/common/js/common.js"></script>
<script type="text/javascript" src="/common/js/scrollBar.js"></script>
<script type="text/javascript" src="/common/js/slider.js"></script>
<script type="text/javascript" src="/common/js/ui.js"></script>
<script type="text/javascript" src="/common/js/spin.min.js"></script><!--#2014-03-31 추가-->
<script type="text/javascript" src="/common/js/common_bnf.js"></script>
<script type="text/javascript">
	$(function ()
	{
		//슬라이딩 이벤트  아래방향:true, 위방향 , false
		Utils.SlideContent($(".srch_lst_box"), null, true);		

		//#2014-03-28 로딩바 세팅 common.js
		setLoadingBar();
	});
</script>
</head>

<body>
	<!-- wrap -->
	<div id="wrap">
		<!-- lnb -->
		<!-- //lnb -->
		<!-- container -->
		<div id="container" style="background:#fff">
			<!-- content_set -->
			<div class="content_set">
				<!-- header -->
				<!-- //header -->
				<!-- content -->
				<section class="content">
					<!-- error -->
					<article class="error">
						<img src="/common/images/common/img_deny.gif" alt="access denied" class="img100" />
						<h1>요청하신 페이지의<br />접근 권한이 없습니다.</h1>
					</article>
					<!-- //error -->
				</section>
				<!-- //content -->
			</div>
			<!-- //content_set -->
			<!-- enabled_area -->
			<div class="enabled_area"></div>
			<!-- //enabled_area -->
		</div>
		<!-- //container -->
		<!-- #2014-03-28 all_benefit_menu -->
		<div class="all_benefit_menu"></div>
		<!-- //all_benefit_menu -->
	</div>
	<!-- //wrap -->
	<!-- modalBg -->
	<div class="modalBg"></div>
	<div class="modalPopupContainer">
		<div class="popupCon"></div>
	</div>
	<!-- //modalBg -->
	<!--#2014-03-28 loading -->
	<div id="loadingCon">
		<div class="loadingSet"></div>
	</div>
	<!-- //loading -->
</body>
</html>