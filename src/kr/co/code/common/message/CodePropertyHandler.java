/*
* Copyright(c) 2013 by SKTelecom co.ltd all right reserved.
*/
package kr.co.code.common.message;

import java.text.MessageFormat;

import kr.co.code.common.util.StringUtil;

import org.springframework.context.support.MessageSourceAccessor;


/**
* 업무명 : 프로퍼티 관리
* 설  명 : system.properties 에 등록된 시스템 프로퍼티 조회
* 작성일 : 2013.04.18
* 작성자 : salc93
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
public class CodePropertyHandler
{

	private static MessageSourceAccessor propertySourceAccessor;

	/**
	 * ECG 연동 ID
	 */
	private static final String ECG_ID = "ecg.id";


	@SuppressWarnings("static-access")
	public void setMessageSourceAccessor(MessageSourceAccessor propertySourceAccessor) {
		this.propertySourceAccessor = propertySourceAccessor;
	}

//	/**
//	 * 서버 구분 조회
//	 * @return
//	 */
//    public static String getServerGubun() {
//    	return getMessage(SlttPropertyHandler.SERVER_GUBUN);
//    }
//
//	/**
//	 * 서버 ID 조회
//	 * @return
//	 */
//    public static String[] getServerIds() {
//    	String key = SlttPropertyHandler.SERVER_ID + "_" + getServerGubun();
//    	String ids = getMessage(key);
//    	return ids.split(",");
//    }
//
//    /**
//     * 서버 URL 조회
//     * @return
//     */
//    public static String[] getServerUrls() {
//    	String urls = getMessage("server.url");
//    	return urls.split(",");
//    }

	/**
	 * ECG 연동 ID 조회
	 *
	 * @return
	 */
	public static String getEcgId() {
		return getMessage(ECG_ID);
	}

	/**
	 * 상용서버인지 여부
	 * @return
	 */
	public static boolean isRealSever() {
		if(getMessage("server.gubun").equals("real")){
			return true;
		}else{
			return false;
		}
	}

	/**
	 * 개발서버인지 여부
	 * @return
	 */
	public static boolean isDevSever() {
		if(getMessage("server.gubun").equals("dev")){
			return true;
		}else{
			return false;
		}
	}

    /**
     * properties에서 메세지 조회
     * @param code
     * @return
     */
    public static String getMessage(String code) {
        String message = propertySourceAccessor.getMessage(code);
        if(message == null || StringUtil.isEmpty(message))   	message = "";
        return message;
    }

    /**
     * properties에서 메세지 조회 (Integer 유형)
     * @param code
     * @return
     */
    public static int getIntMessage(String code) {
        String message = getMessage(code);
        if(message == ""){
        	return 0;
        }else{
        	return Integer.parseInt(message);
        }
    }

    /**
     * properties에서 메세지 조회 (boolean 유형)
     * @param code
     * @return
     */
    public static boolean getBooleanMessage(String code) {
        String message = getMessage(code);
        if(message.equalsIgnoreCase("true")){
        	return true;
        }else{
        	return false;
        }
    }

    /**
     * properties에서 메세지 조회
     * 파라미터 셋팅
     * @param code
     * @param param
     * @return
     */
    public static String getMessage(String code, String[] param) {
    	Object[] obj = (Object[])param;
    	String message = MessageFormat.format(getMessage(code), obj);
        return message;
    }
    
    /**
     * properties에서 메세지 조회
     * @param code
     * @return
     */
    public static String[] getMessageArray(String code) {
        String message = propertySourceAccessor.getMessage(code);
        if(message == null || StringUtil.isEmpty(message))   	message = "";
        return message.split( ",");
    }


    public static void main(String[] args){
    	String str = "";
    	String[] arr = str.split(" ");
    	for(int i=0; i<arr.length; i++){
    	}
    }
    
    /**
	 * 인증번호 생성 후 유효시간(분)
	 * 
	 * @return
	 */
	public static int getSmsExpireTime() {
		return getIntMessage("sms.expire.time");
	}
	
	/**
	 * 인증 실패 허용 횟수(0이면 무제한 허용)
	 * 
	 * @return
	 */
	public static int getSmsFailCnt() {
		return getIntMessage("sms.fail.cnt");
	}
	
    /**
	 * 인증 필수 페이지에 미인증 상태로 진입시 로그인 안내 페이지
	 * @return
	 */
	public static String getLoginInfoUrl() {
		return getMessage("login.info.url");
	}
	
	/**
	 * 타사 이용자 메인 화면 : 서비스 안내
	 * @return
	 */
	public static String getOtherMainUrl() {
		return getMessage("other.main.url");
	}
	
	/**
	 * 로그아웃 후 이동할 페이지
	 * @return
	 */
	public static String getLogoutResultUrl() {
		return getMessage("logout.result.url");
	}
	
	/**
	 * 회원 탈퇴로 인한 로그아웃 후 이동할 페이지
	 * @return
	 */
	public static String getWithdrawLogoutResultUrl() {
		return getMessage("withdraw.logout.result.url");
	}
	/**
	 * 차단 메뉴 진입시 default 안내 페이지 
	 * @return
	 */
	public static String getDefaultItIsolInfoUrl() {
		return getMessage("it.isol.info.url");
	}
	
}
