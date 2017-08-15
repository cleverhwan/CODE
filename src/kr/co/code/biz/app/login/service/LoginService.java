/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.app.login.service;

import java.util.List;
import java.util.Map;

import kr.co.code.biz.app.login.vo.UserVO;
import kr.co.code.common.exception.CodeException;
import kr.co.code.common.util.DataMap;


/**
* 업무명 : 로그인
* 설  명 : 로그인 업무를 담당하는 Service interface
* 작성일 : 2013.04.18
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
public interface LoginService {

	/**
	 * 로그인을 위해 이용자 정보 조회
	 * @param svcNum
	 * @return
	 * @throws CodeException
	 */
	public UserVO getUserInfo(String email) throws CodeException;

	public UserVO getUserInfo(UserVO userVO) throws CodeException;
	
	/**
	 * 로그인을 위해 데모 이용자 상태 정보 조회
	 * @param svcNum
	 * @return
	 * @throws CodeException
	 */
	public String getDemoApprSt(String email) throws CodeException;
	
	
	/**
	 * 로그인을 위해 이용자 정보 조회 (세션 생성을 위한 정보)
	 * @param custId
	 * @return
	 * @throws CodeException
	 */
	public UserVO getUserInfoByCustId(String custId) throws CodeException;

	/**
	 * 페이지별 권한 가져오기
	 * @param custId
	 * @return
	 * @throws CodeException
	 */
	public List<DataMap> getPageFuncByRoleList(String custId) throws CodeException;

	/**
	 * 로그인 이력 생성
	 * 
	 * @param userVO
	 * @return
	 * @throws CodeException
	 */
	public UserVO insertHistory(UserVO userVO) throws CodeException;	
	
	/**
	 * 회원 이름 변경
	 * @param userVO
	 * @return
	 * @throws CodeException
	 */
	public int updateCustNm(UserVO userVO, String chgItm, String chgRsnCd) throws CodeException;
	
	/**
	 * PASSWORD 업데이트
	 * @param userVO
	 * @return
	 * @throws CodeException
	 */
	public int updatePw(UserVO userVO) throws CodeException;
	
	/**
	 * PASSWORD 업데이트
	 * @param userVO
	 * @return
	 * @throws CodeException
	 */
	public int updateCustSt(UserVO userVO) throws CodeException;
	
	
	public void insertEmailReCert(Map map) throws CodeException;
	
	public int updateCustLoginDate(UserVO userVO) throws CodeException;

	public int updateCustPwdDate(UserVO userVO) throws CodeException;
	
	public int updateCustLoginFailCount(UserVO userVO) throws CodeException;
	
	public int updateCustLoginFailCountZero(UserVO userVO) throws CodeException;
	
	public int deleteCustLogin(UserVO userVO) throws CodeException;
	
	public int updateNextPwdDate(UserVO userVO) throws CodeException;

	public int updateAuthCd(UserVO userVO) throws CodeException;
	
	public int updateNouseSendYn(UserVO userVO) throws CodeException;
}
