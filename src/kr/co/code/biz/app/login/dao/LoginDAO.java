/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.app.login.dao;

import java.util.List;
import java.util.Map;

import kr.co.code.biz.app.login.vo.UserVO;
import kr.co.code.biz.common.base.BaseDAO;
import kr.co.code.biz.common.vo.SessionUserVO;
import kr.co.code.common.util.DataMap;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;


/**
* 업무명 : 로그인
* 설  명 : 로그인 업무를 담당하는 DAO 클래스
* 작성일 : 2013.04.18
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
@Repository("kr/co/code/biz/app/login/dao/LoginDAO")
public class LoginDAO extends BaseDAO {
	public UserVO getUserInfo(String email) throws DataAccessException {
		return (UserVO) selectByPk("Login.getUserInfo", email);
	}
	
	public UserVO getUserInfo(UserVO userVO) throws DataAccessException {
		return (UserVO) selectByPk("Login.getUserInfoNew", userVO);
	}
	
	public String getDemoApprSt(String email) throws DataAccessException {
		return (String) selectByPk("Login.getDemoApprSt", email);
	}	
	
	
	
	public UserVO getUserInfoByCustId(String custId) throws DataAccessException {
		return (UserVO) selectByPk("Login.getUserInfoByCustId", custId);
	}

	public List<DataMap> getPageFuncByRoleList(String custId) {
		return dataMapList("Login.getPageFuncByRoleList", custId);
	}
	
	public UserVO insertHistory (UserVO userVO) throws DataAccessException {
		insert("Login.insertHistory", userVO);
		return userVO;
	}
	
	public SessionUserVO getUserDetails(String custId) throws DataAccessException {
		return (SessionUserVO) selectByPk("Login.getUserDetails", custId);
	}
	
	public int updateCustNm (UserVO userVO) throws DataAccessException {
		return update("Login.updateCustNm", userVO);
	}	
	
	public int updatePw(UserVO userVO) throws DataAccessException {
		return update("Login.updatePw", userVO);
	}
	
	public int updateCustSt(UserVO userVO) throws DataAccessException {
		return update("Login.updateCustSt", userVO);
	}
	
	public void insertEmailReCert (Map map) throws DataAccessException {
		insert("Login.insertEmailReCert", map);
	}
	
	public int updateCustLoginDate(UserVO userVO) throws DataAccessException {
		return update("Login.updateCustLoginDate", userVO);
	}
	
	public int updateCustPwdDate(UserVO userVO) throws DataAccessException {
		return update("Login.updateCustPwdDate", userVO);
	}
	
	public int updateCustLoginFailCount(UserVO userVO) throws DataAccessException {
		return update("Login.updateCustLoginFailCount", userVO);
	}
	
	public int updateCustLoginFailCountZero(UserVO userVO) throws DataAccessException {
		return update("Login.updateCustLoginFailCountZero", userVO);
	}
	
	public int deleteCustLogin(UserVO userVO) throws DataAccessException {
		return delete("Login.deleteCustLogin", userVO);
	}
	
	public int updateNextPwdDate(UserVO userVO) throws DataAccessException {
		return update("Login.updateNextPwdDate", userVO);
	}
	
	public int updateAuthCd(UserVO userVO) throws DataAccessException {
		return update("Login.updateAuthCd", userVO);
	}
	
	public int updateNouseSendYn(UserVO userVO) throws DataAccessException {
		return update("Login.updateNouseSendYn", userVO);
	}
}