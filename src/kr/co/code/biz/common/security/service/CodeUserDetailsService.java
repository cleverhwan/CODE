/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.security.service;

import java.util.ArrayList;
import java.util.Collection;

import kr.co.code.biz.app.login.dao.LoginDAO;
import kr.co.code.biz.common.security.userdetails.CodeUser;
import kr.co.code.biz.common.vo.SessionUserVO;
import kr.co.code.common.logging.CodeLogger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 업무명 : 인증 공통
* 설  명 : security-context.xml의 다음 속성에서 참조 <br>
*         usernamePasswordAuthenticationFilter / authenticationManager / CodeUserDetailsService <br>
*         로그인 프로세스 성공시 로그인한 이용자 정보를 담은 SessionUserVO를 생성한 뒤 MtcUser에 셋팅하여 리턴
*
*         auto-login은 비밀번호를 주지 못하므로 spring security에서는 비번을 항상 null String 으로 셋팅하여 처리
*
* 작성일 : 2014.02
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
@Service(value="userDetailsService")
public class CodeUserDetailsService  implements UserDetailsService {

	private CodeLogger log = CodeLogger.getLogger("kr.co.code.biz.app.login");

	@Autowired
	private LoginDAO loginDAO;


	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String custId) throws UsernameNotFoundException, DataAccessException {
		
		log.error("loadUserByCustId : " + custId);		
		SessionUserVO sesUserVO;

		sesUserVO = loginDAO.getUserDetails(custId);

		if(sesUserVO == null) {
			// 조회된 사용자 없는 경우
			log.debug("[UserDetailsServiceImpl.java - loadUserByUsername] sesUserVO == null : user not found");
			throw new UsernameNotFoundException("user not found");

		}else{

			log.debug("[UserDetailsServiceImpl.java - loadUserByUsername] sesUserVO == user  found");
			// SESSION 생성
			String dbLoginId = sesUserVO.getLoginId();
			//String dbPwd = sesUserVO.getPwd();
			String dbPwd = "";

			boolean enabled = true;
			boolean accountNonExpired = true;
			boolean credentialsNonExpired = true;
			boolean accountNonLocked = true;

			//security-context.xml에서 hasRole()로 지정한 권한이 담기는 부분
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			//authorities.add(new GrantedAuthorityImpl(sesUserVO.getUserGrade()));

			CodeUser user = new CodeUser(dbLoginId, dbPwd, enabled,
			accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, sesUserVO);

			return user;
		}
	}
}
