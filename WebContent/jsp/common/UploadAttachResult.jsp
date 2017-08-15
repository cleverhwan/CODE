<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<%@page import="kr.co.code.common.util.StringUtil"%>
<%
String sResult = StringUtil.cleanWeekness( (String) request.getAttribute( "RESULT" ) );
String sMessage = StringUtil.cleanWeekness( (String) request.getAttribute( "MESSAGE" ) );

String sPopupMessage = "";
if( sResult.equals( "true" ) ) {
	sPopupMessage = "업로드에 성공하였습니다.";
} else {
	sPopupMessage = sMessage;
}
%>
	<head>
	</head>

	<body>
		<script>
			parent.Extern_refresh( '<%=sPopupMessage%>' );
		</script>
	</body>
</html>