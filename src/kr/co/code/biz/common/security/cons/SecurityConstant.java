/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.security.cons;

/**
* 업무명 : 인증 공통
* 설  명 : 인증모듈 상수 관리
*
*
* 작성일 : 2014.02
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
public class SecurityConstant {

	// 로그아웃 요청 채널을 넘기는 파라미터 ( 일반 로그아웃 / 회원탈퇴 후 로그아웃 )
	public static final String LOGOUT_TYPE = "logoutType";

	// Ajax 호출 URL이 세션만료되어 강제 로그아웃 후 이동하는 페이지
	//   비밀번호 변경안내 페이지에서 취소 버튼 클릭시 이동하는 페이지 (로그인 후 이동해야 하는 URL)
	public static final String RETURN_URL = "returnUrl";
	
	//Spring Security가 세션을 생성하는 URL (Controller에서 ID/PWD에 대한 선처리 후 Spring Sercurity에 세션 생성을 요청하는 URL)
	public static final String SPRING_SESSION_PROCESS_PAGE = "/login/loginSuccess.do";
	
	// 앱으로 최초 로그인 후 js 함수 호출 페이지 
	public static final String SAVE_BIRTHDAY_URL = "/login/firstLoginSuccess.do";

}