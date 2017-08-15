/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.security.userdetails;

import java.util.Collection;

import kr.co.code.biz.common.vo.SessionUserVO;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

/**
* 업무명 : 인증 공통
* 설  명 : Spring Security가 생성하는 Session Context에서 제공하는 세션정보 접근을 위한 클래스
*
* 작성일 : 2014.02
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
public class CodeUser extends User {

	private static final long serialVersionUID = 1675206938260760724L;

	private String custId;
	private String loginId;
	private String custNm;
	
	public String getCustId() {
		return custId;
	}


	public void setCustId(String custId) {
		this.custId = custId;
	}


	public String getLoginId() {
		return loginId;
	}


	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}


	public String getCustNm() {
		return custNm;
	}


	public void setCustNm(String custNm) {
		this.custNm = custNm;
	}

	public CodeUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities, SessionUserVO sessionUserVO) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired,
				accountNonLocked, authorities);

		this.custId = sessionUserVO.getCustId();
		this.loginId = sessionUserVO.getLoginId();
		this.custNm = sessionUserVO.getCustNm();
	}
}
