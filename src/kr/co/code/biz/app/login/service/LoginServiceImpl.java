/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.app.login.service;

import java.util.List;

import java.util.Map;

import kr.co.code.biz.app.login.dao.LoginDAO;
import kr.co.code.biz.app.login.vo.UserVO;
import kr.co.code.common.exception.CodeException;
import kr.co.code.common.logging.CodeLogger;
import kr.co.code.common.util.DataMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 업무명 : 로그인
* 설  명 : 로그인 업무를 담당하는 Service 클래스
* 작성일 : 2013.04.18
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
@Service("kr/co/code/biz/app/login/service/LoginService")
public class LoginServiceImpl implements LoginService{
	private static CodeLogger log = CodeLogger.getLogger("kr.co.code.biz.app");
	
	@Autowired
	private LoginDAO loginDAO;

	
	public UserVO getUserInfo(String email) throws CodeException {
		return loginDAO.getUserInfo(email);
	}
	

	public UserVO getUserInfo(UserVO userVO) throws CodeException {
		return loginDAO.getUserInfo(userVO);
	}
	
	
	public String getDemoApprSt(String email) throws CodeException {
		return loginDAO.getDemoApprSt(email);
	}
	

	
	public UserVO getUserInfoByCustId(String custId) throws CodeException {
		return loginDAO.getUserInfoByCustId(custId);
	}

	
	public List<DataMap> getPageFuncByRoleList(String custId) {
		return loginDAO.getPageFuncByRoleList(custId);
	}
	
	
	public UserVO insertHistory(UserVO userVO)  throws CodeException {
		return loginDAO.insertHistory(userVO);
	}

	
	public int updateCustNm(UserVO userVO, String chgItm, String chgRsnCd)  throws CodeException {
		
		loginDAO.updateCustNm(userVO);

		return 1;
	}
	

	public int updatePw(UserVO userVO) {
		loginDAO.updateCustPwdDate(userVO);
		return loginDAO.updatePw(userVO);
	}
	
	
	public int updateCustSt(UserVO userVO) {
		return loginDAO.updateCustSt(userVO);
	}
	
	
	@Transactional
	public void insertEmailReCert(Map map)  throws CodeException {
		loginDAO.insertEmailReCert(map);
		
		UserVO userVO = new UserVO();
		
		userVO.setLoginId((String)map.get("emailAddr"));
		userVO.setCustSt((String)map.get("custSt"));
		loginDAO.updateCustSt(userVO);
	}

	
	public int updateCustLoginDate(UserVO userVO) throws CodeException {
		try {
			if( log.isDebugEnabled() )
				log.debug("### PARAMETER :: updateCustLoginDate [" + userVO + "]");

			return loginDAO.updateCustLoginDate(userVO);
		} catch( DataAccessException dae ) {
			throw new CodeException( dae );
		} catch( Exception e ) {
			throw new CodeException( e );
		}
	}
	
	public int updateCustPwdDate(UserVO userVO) throws CodeException {
		try {
			if( log.isDebugEnabled() )
				log.debug("### PARAMETER :: updateCustPwdDate [" + userVO + "]");

			return loginDAO.updateCustPwdDate(userVO);
		} catch( DataAccessException dae ) {
			throw new CodeException( dae );
		} catch( Exception e ) {
			throw new CodeException( e );
		}
	}
	
	public int updateCustLoginFailCount(UserVO userVO) throws CodeException {
		try {
			if( log.isDebugEnabled() )
				log.debug("### PARAMETER :: updateCustLoginFailCount [" + userVO + "]");

			return loginDAO.updateCustLoginFailCount(userVO);
		} catch( DataAccessException dae ) {
			throw new CodeException( dae );
		} catch( Exception e ) {
			throw new CodeException( e );
		}
	}
	
	public int updateCustLoginFailCountZero(UserVO userVO) throws CodeException {
		try {
			if( log.isDebugEnabled() )
				log.debug("### PARAMETER :: updateCustLoginFailCountZero [" + userVO + "]");

			return loginDAO.updateCustLoginFailCountZero(userVO);
		} catch( DataAccessException dae ) {
			throw new CodeException( dae );
		} catch( Exception e ) {
			throw new CodeException( e );
		}
	}

	public int deleteCustLogin(UserVO userVO) throws CodeException {
		try {
			if( log.isDebugEnabled() )
				log.debug("### PARAMETER :: deleteCustLogin [" + userVO + "]");

			return loginDAO.deleteCustLogin(userVO);
		} catch( DataAccessException dae ) {
			throw new CodeException( dae );
		} catch( Exception e ) {
			throw new CodeException( e );
		}
	}
	
	public int updateNextPwdDate(UserVO userVO) throws CodeException {
		try {
			if( log.isDebugEnabled() )
				log.debug("### PARAMETER :: updateNextPwdDate [" + userVO + "]");

			return loginDAO.updateNextPwdDate(userVO);
		} catch( DataAccessException dae ) {
			throw new CodeException( dae );
		} catch( Exception e ) {
			throw new CodeException( e );
		}
	}

	public int updateAuthCd(UserVO userVO) throws CodeException {
		try {
			if( log.isDebugEnabled() )
				log.debug("### PARAMETER :: updateAuthCd [" + userVO + "]");

			return loginDAO.updateAuthCd(userVO);
		} catch( DataAccessException dae ) {
			throw new CodeException( dae );
		} catch( Exception e ) {
			throw new CodeException( e );
		}
	}

	public int updateNouseSendYn(UserVO userVO) throws CodeException {
		try {
			if( log.isDebugEnabled() )
				log.debug("### PARAMETER :: updateNouseSendYn [" + userVO + "]");

			return loginDAO.updateNouseSendYn(userVO);
		} catch( DataAccessException dae ) {
			throw new CodeException( dae );
		} catch( Exception e ) {
			throw new CodeException( e );
		}
	}
}
