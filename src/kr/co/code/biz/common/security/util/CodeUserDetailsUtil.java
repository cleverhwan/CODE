/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.security.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.savedrequest.SavedRequest;

import kr.co.code.biz.common.security.userdetails.CodeUser;
import kr.co.code.biz.common.vo.SessionUserVO;
import kr.co.code.common.logging.CodeLogger;
import kr.co.code.common.message.CodePropertyHandler;
import kr.co.code.common.util.StringUtil;

/**
* 업무명 : 인증 공통
* 설  명 : 세션정보 조회/수정을 위한 유틸리티
*
* 작성일 : 2014.02
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
public class CodeUserDetailsUtil {

	private static CodeLogger log = CodeLogger.getLogger( CodeUserDetailsUtil.class.getName() );

	//session 객체에 저장되는 SessionUserVO(이용자 정보)의 key

	/**
	 * 인증된 사용자객체를 VO형식으로 가져온다.
	 * @return Object - 사용자 ValueObject
	 */
	public static CodeUser getSecurityUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication == null) {
			return null;
		}

		//로그인 하지 않은 경우는 다음 구문에서 ClassCastException이 발생함
		try{
			return (CodeUser)authentication.getPrincipal();

		}catch(Exception e){
	
			//log.error("CodeUserDetailsUtil.getSecurityUser() : " + e.toString());
			//e.printStackTrace();
			return null;
		}
	}

	/**
	 * 세션정보중 이용자 정보 : SessionUserVO
	 * 
	 * @param request
	 * @return
	 */
	public static SessionUserVO getUser(HttpServletRequest request) {

		//로그인 하지 않은 경우는 다음 구문에서 ClassCastException이 발생함
		try{
			if (getSecurityUser() == null) {				
				 return null;

			}else{
				SessionUserVO sessionUserVO = (SessionUserVO)request.getSession().getAttribute("SESSION_USER");	
				return sessionUserVO;
			}

		}catch(Exception e){
			//log.error("CodeUserDetailsUtil.getUser() : " + e.toString());
			//e.printStackTrace();			
			return null;
		}
	}
	
	/**
	 * 로그인 성공 후 세션에서 관리되는 정보 셋팅
	 *
	 * @param request
	 * @param sessionUserVO
	 */
	public static void makeSession(HttpServletRequest request, SessionUserVO sessionUserVO) {
		request.getSession().setAttribute("SESSION_USER", sessionUserVO);
	}
	
//	/**
//	 * 혜택정보 최초 조회 후 세션에 저장
//	 *
//	 * @param request
//	 * @param sessionUserVO
//	 */
//	public static void makeBenefitSession(HttpServletRequest request, UserBenefitVO sessionBenefitVO) {
//		request.getSession().setAttribute(SESSION_BENEFIT, sessionBenefitVO);
//	}	
	
	/**
	 * 로그아웃 성공후 세션정보 삭제
	 *
	 * @param request
	 * @param sessionUserVO
	 */
	public static void removeSession(HttpServletRequest request) {
		
		HttpSession session = request.getSession(false);
		log.debug("removeSession start---------------------------");
		
		if(session != null){
			
			session.invalidate();
			log.debug("removeSession session.invalidate()---------------------------");
		}
		
		log.debug("removeSession end---------------------------");
	}


	/**
	 * 로그인 되어 있는지 체크한다.
	 * @return Boolean - 로그인 여부(TRUE / FALSE)
	 */
	public static boolean isLogin(HttpServletRequest request) {

		SessionUserVO sessionUserVO = getUser(request);

		if(sessionUserVO == null) {
			return false;

		}else {
			return true;
		}

	}	
	
	/**
	 * 사용자의 고객번호를 가져온다.
	 * @return 사용자 고객번호 (cust_num)
	 */
	public static String getCustId() {

		CodeUser CodeUser = (CodeUser)getSecurityUser();
		if(CodeUser == null){
			return null;
		}else{
			return  CodeUser.getCustId();
		}
	}

	public static String getLoginId() {

		CodeUser CodeUser = (CodeUser)getSecurityUser();
		if(CodeUser == null){
			return null;
		}else{
			return  CodeUser.getLoginId();
		}
	}

	public static String getCustNm() {

		CodeUser CodeUser = (CodeUser)getSecurityUser();
		if(CodeUser == null){
			return null;
		}else{
			return  CodeUser.getCustNm();
		}
	}
	
	/*
	 * 인증된 사용자의 권한 정보를 가져온다.
	 * 예) [ROLE_ADMIN, ROLE_USER, ROLE_A, ROLE_B, ROLE_RESTRICTED, IS_AUTHENTICATED_FULLY, IS_AUTHENTICATED_REMEMBERED, IS_AUTHENTICATED_ANONYMOUSLY]
	 * @return List - 사용자 권한정보 목록

	public static List<String> getAuthorities() {
		List<String> listAuth = new ArrayList<String>();

		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication == null) {
			return null;
		}

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		Iterator<? extends GrantedAuthority> iter = authorities.iterator();

		while(iter.hasNext()) {
			listAuth.add(iter.next().getAuthority());
		}

		return listAuth;
	}
	*/


	/*
	public static boolean isAuth(String auth) {

		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication == null) {
			return Boolean.FALSE;
		}

		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		Iterator<? extends GrantedAuthority> iter = authorities.iterator();

		while(iter.hasNext()) {
			if(auth.equals(iter.next().getAuthority()))
				return Boolean.TRUE;
		}

		return Boolean.FALSE;
	}
	*/


	/*
	 * 인증된 사용자 여부를 체크한다.
	 * @return Boolean - 인증된 사용자 여부(TRUE / FALSE)

	public static Boolean isAuthenticated() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();

		if (authentication == null) {
			return Boolean.FALSE;
		}

		String username = authentication.getName();
		if (username.equals("roleAnonymous")) {
			return Boolean.FALSE;
		}

		Object principal = authentication.getPrincipal();

		return Boolean.valueOf(!(principal == null));
	}
	*/

	/**
	 * 비밀번호 체크
	 *	<br/>(?=.*[a-zA-Z]) 		a lower case, an upper case letter must occur at least once
	 * 	<br/>(?=.*[0-9]) 			a digit must occur at least once (?=.*[0-9])
	 *	<br/>(?=.*[!@$%^&*]) 	a special character must occur at least once
	 *	<br/>.{8, 20} 				at least 8 to 20 characters
	 * @param userId
	 * @param password
	 * @return
	 */
	public static boolean isValidPassword(String loginId, String password) {

		String regex = "(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@$%^&*]).{8,20}";

		if(StringUtil.isEmpty(password)) {
			return false;
		}

		// 사용자 ID와 같은지 체크
		if(password.equals(loginId)) {
			return false;
		}

		// 사용자 ID에 비밀번호가 속해 있는지 여부 체크
		if(password.contains(loginId)) {
			return false;
		}

		// 영문, 숫자, 특수문자 체크
		if(!password.matches(regex)) {
			return false;
		}

		// 영문, 숫자, 특수문자 체크
		for(int i = 0; i < password.length(); i++) {

			char value 	= password.charAt(i);
			String symbol 	= "!@$%^&*";

			if((value>='0' && value<='9') || (value>='a' && value<='z') || (value>='A' && value<='Z')) {
				continue;
			}else if(symbol.indexOf(value) == -1) {
				return false;
		    }
		}

		return true;
	}

	/**
	 * 사용자의 IP 값을 가져온다.
	 * @return String - 사용자 Reqest IP
	 */
	public static String getRemoteAddress() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		WebAuthenticationDetails details = (WebAuthenticationDetails) authentication.getDetails();

		return details.getRemoteAddress();
	}
	
	/**
	 * 로그인 성공 후 이동 페이지 
	 * 
	 * @param request
	 * @return
	 */
	public static String getLoginSuccessForwardURL(HttpServletRequest request){

		log.debug("getLoginSuccessForwardURL!!!!!!!!!!!!!!!!!!!!!!! ");
		String forwardUrl = "";

		SavedRequest savedReq = (SavedRequest) request.getSession().getAttribute(WebAttributes.SAVED_REQUEST);
		log.debug( (savedReq == null)? "savedReq is null" : "savedReq is not null -> " + savedReq.getRedirectUrl() );
		
		SessionUserVO sesUserVO = (SessionUserVO)CodeUserDetailsUtil.getUser(request);
		log.debug("sesUserVO!!!!!!!!!!!!!!!!!!!!!!! "+sesUserVO);
		if(sesUserVO != null){
			
				
				String returnUrl = StringUtil.defaultString(sesUserVO.getReturnUrl(), "").trim();

				log.debug("CodeUserDetailsUtil.getLoginSuccessForwardURL()  returnUrl : " + returnUrl); 
				
				if(!"".equals(returnUrl)){
					forwardUrl = returnUrl;
					log.debug("CodeUserDetailsUtil.getLoginSuccessForwardURL()  forwardUrl 111 : " + forwardUrl); 
					
				//일반 로그인인 경우	
				}else{
					//타사 이용자 로그인	
						
					//회원가입 후 로그인 호출한 케이스
					if(sesUserVO.isLoginByJoin()){							
						forwardUrl = CodePropertyHandler.getOtherMainUrl();
						log.debug("CodeUserDetailsUtil.getLoginSuccessForwardURL()  forwardUrl 222 : " + forwardUrl); 
					
					//일반 로그인 케이스
					}else{
						log.debug("CodeUserDetailsUtil.getLoginSuccessForwardURL()  forwardUrl 333 : " + CodePropertyHandler.getOtherMainUrl()); 
						forwardUrl = (savedReq == null)? CodePropertyHandler.getOtherMainUrl() : savedReq.getRedirectUrl();
						log.debug("CodeUserDetailsUtil.getLoginSuccessForwardURL()  forwardUrl 444 : " + forwardUrl); 
					}
				}
		}				
		
		log.debug("원래 진입하려던 페이지로 포워딩(getLoginSuccessForwardURL): " + forwardUrl);	

		return CodePropertyHandler.getMessage("http.domain")+forwardUrl;
	}
	
	
	/*
	private static String escape(String url){
    	if(url.equals("")){
    		return url;
	    }else{
    		try{
	    		url = StringUtil.replaceAll(url, "&amp;", "&");
    		}catch(Exception e){
    		}
    		return url;
	    }
	}
	*/	
	
	/**
	 * ssl 여부에 따른 redirection이 발생할 경우 브라우져에서 '#'이 유실되어 혜택 인트로 화면 이동에 오류가 발생함
	 * 그래서 redirection이 발생하지 않도록 full url을 만들어 호출
	 * 
	 * @param path
	 * @return
	 */
//	private static String getFullUrl(String path){
//
//		//https로 호출해야하는 URL인지 여부
//		boolean https = ( (path.indexOf("/sec/") >= 0) || (path.indexOf("/login/") >= 0) )? true:false;
//		
//		//  https://m.tbenefit.co.kr/notice/list.do
//		boolean pathWithHttps = false;
//		//  http://m.tbenefit.co.kr/notice/list.do
//		boolean pathWithHttp = false;
//		//  /notice/list.do
//		boolean pathWithNo = false;
//		
//		if( path.indexOf("https") >= 0 ){
//			pathWithHttps = true;
//			
//		}else if( path.indexOf("http") >= 0 ){
//			pathWithHttp = true;
//			
//		}else{
//			pathWithNo = true;
//		}
//		
//		if(https){
//			if(pathWithNo){
//				return CodePropertyHandler.getHttpsUrl() + path;
//			}else if(pathWithHttp){
//				return StringUtil.replaceAll(path, "http", "https");
//			}else{
//				return path;
//			}
//			
//		}else{
//			if(pathWithNo){
//				return CodePropertyHandler.getHttpUrl() + path;
//			}else if(pathWithHttps){
//				return StringUtil.replaceAll(path, "https", "http");
//			}else{
//				return path;
//			}
//		}
//	}
	
	/**
	 * SKT 전체 혜택보기 클릭시 링크
	 * 
	 * @param request
	 * @return
	 */
//	public static String getBenefitAllScript(HttpServletRequest request) {
//		SessionUserVO sesUserVO = getUser(request);
//		
//		//SKT 가입자 : 나의 혜택
//		if(sesUserVO != null && sesUserVO.isSKT()) 
//			return CodePropertyHandler.getSktBenenfitAllUrl();
//		
//		//타사 가입자, 미로그인 : SKT 전체 혜택
//		else 
//			return CodePropertyHandler.getOtherBenenfitAllUrl();
//	}	
}
