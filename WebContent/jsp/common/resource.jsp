	<meta charset="utf-8">
	<meta name="format-detection" content="telephone=no">
	<!-- 
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no, target-densitydpi=medium-dpi">
	 -->
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	
	<meta name="description" content="">
	<meta name="keywords" content="">
	<meta name="title" content="">
	<title>1Q MTCenter - QC</title>
	
	<link rel="shortcut icon" type="image/x-icon" href="/images/favicon.ico"/>
	
	<script type="text/javascript" src="/js/jquery.min.1.11.0.js"></script> 
	<script type="text/javascript" src="/js/jquery.spinner.min.js"></script>

	<script type="text/javascript" src="/js/qc_common.js"></script>
	<script type="text/javascript" src="/js/locate.js"></script>
	<script type="text/javascript" src="/js/deployJava.js"></script>
	<script type="text/javascript" src="/js/callJnlp.js"></script>	
	
	<!-- qc  css-->
	<link href="/css/qc/fonts.css" rel="stylesheet" type="text/css">
	<link href="/css/qc/reset.css" rel="stylesheet" type="text/css">
	
	<link href="/css/qc/style.css" rel="stylesheet" type="text/css">
	<link href="/css/qc/pop.css" rel="stylesheet" type="text/css">
	<link href="/css/qc/qc_common.css" rel="stylesheet" type="text/css">
	
	 <script>
	 	var SEPA_COL = "|*|";
	 	var SEPA_ROW = "|^|";

		$(function(){
			$.ajaxSetup({
				beforeSend: function( xhr ){
					xhr.setRequestHeader( "AJAX", true );

					loadingIn();
				}
				, complete: function(){
					loadingOut();
				}
				, error: function( xhr, status, err ){
					loadingOut();
				}
			});
		});

		function loadingIn() {
			if( $( ".qc-wrap-loading" ).length === 0 ) {
				$( "body" ).append( "<div><div class=\"qc-wrap-loading qc-wrap-loading-display-none\"><div><img src=\"/images/loading_bar.gif\"/></div></div></div>" );
			}

			$( ".qc-wrap-loading" ).removeClass( "qc-wrap-loading-display-none" );
			$( ".qc-wrap-loading" ).css( "z-index", "999" );
		}

		function loadingOut() {
	        $( ".qc-wrap-loading" ).addClass( "qc-wrap-loading-display-none" );
			$( ".qc-wrap-loading" ).css( "z-index", "" );
		}
	</script>

<%@page import="kr.co.code.biz.common.security.util.CodeUserDetailsUtil"%>
<%@page import="kr.co.code.common.util.DataMap"%>
<%@page import="kr.co.code.biz.common.vo.SessionUserVO"%>
<%@page import="kr.co.code.common.util.StringUtil"%>
<%!
private SessionUserVO mUser = null;
private DataMap mFuncInfo = null;
private boolean fIsPageFunc( String psPageFuncName ) {
	if( mFuncInfo == null ) {
		return false;
	} else {
		String sResult = mFuncInfo.getString( psPageFuncName );
		if( sResult != null && sResult.equals("Y") ) {
			return true;
		} else {
			return false;
		}
	}
}
%>
<%
mUser = CodeUserDetailsUtil.getUser( request );
if( mUser != null ) {
	mFuncInfo = mUser.getDataMap();
	if( mFuncInfo != null && !mFuncInfo.isEmpty() ) {
		;
	} else {
		mFuncInfo = null;
	}
}

%>
