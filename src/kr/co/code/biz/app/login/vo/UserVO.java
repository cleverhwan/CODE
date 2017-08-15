/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.app.login.vo;

import kr.co.code.biz.common.base.BaseVO;


/**
* 업무명 : 회원가입, 개인정보 조회/ 수정, 로그인
* 설  명 : 회원 업무를 담당하는 VO 클래스
*
* 작성일 : 2014.02
* 작성자 :
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
/**
 * @author P050214
 *
 */
public class UserVO extends BaseVO{
	private static final long serialVersionUID = 4475926863530100233L;

	private String loginSucYn = ""; //로그인 성공 여부 (로그인 History 생성시 필요)
	private String	loginId; //로그인ID
	private String	custNm; //고객명 : DB에서 조회한 고객명
	private String	custTp; //고객유형
	private String	password; //비밀번호
	private String	custId; //고객ID
	private String	nation; //국가
	private String	region; //지역
	private String domainCd; //도메인
	private String postCd; //소속
	private String job; //직업
	private String custGr; //고객등급
	private String custSt; //고객상태
	private String returnUrl; //인증 후 이동할 URL
	private String resultFlag; //인증 처리 결과 유형 : LoginConstant에 정의된 상수
	private String resultCd; //로그인 처리 상태 코드
	private boolean loginByJoin; //회원가입 후 로그인 호출 여부
	private String isolYn; //차단 이용자 여부
	private String salt;
	private String newPwd;
	private String note;
	private String company;
	private String forwardUrl;
	private String authCd;
	private String totalTime;
	private String loginIp;
	private int	failCnt;
	private int	lastPwdGap;
	private int	nextPwdGap;
	private double lastPwdTerm;
	private double nextPwdTerm;
	private double mustPwdTerm;
	private String	testType;
	private String autoVersion;
	private String	hpNum; //휴대폰 번호
	private String businessNum; //사업자 등록번호
	private String companyNm; //기업명
	private String repNm; //대표자명
	private String memberCd; //회원구분(P:개인, C:법인, H:임직원)
	private String licenseFileId;
	private String dailyCheck;
	private String agreeDate;
	private String authFirst;
	private String demoCustTp;
	private String langTp;
	private String sessionLang;
	private String used;
	private String companyCd;
	private String roleCode;
	
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	private String email;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStartDt() {
		return startDt;
	}
	public void setStartDt(String startDt) {
		this.startDt = startDt;
	}
	public String getEndDt() {
		return endDt;
	}
	public void setEndDt(String endDt) {
		this.endDt = endDt;
	}
	private String position;
	private String comment;
	private String startDt;
	private String endDt;

	
	
	public String getSessionLang() {
		return sessionLang;
	}
	public void setSessionLang(String sessionLang) {
		this.sessionLang = sessionLang;
	}
	public String getLoginSucYn() {
		return loginSucYn;
	}
	public void setLoginSucYn(String loginSucYn) {
		this.loginSucYn = loginSucYn;
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
	public String getCustTp() {
		return custTp;
	}
	public void setCustTp(String custTp) {
		this.custTp = custTp;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustId() {
		return custId;
	}
	public void setCustId(String custId) {
		this.custId = custId;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getDomainCd() {
		return domainCd;
	}
	public void setDomainCd(String domainCd) {
		this.domainCd = domainCd;
	}
	public String getPostCd() {
		return postCd;
	}
	public void setPostCd(String postCd) {
		this.postCd = postCd;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getCustGr() {
		return custGr;
	}
	public void setCustGr(String custGr) {
		this.custGr = custGr;
	}
	public String getCustSt() {
		return custSt;
	}
	public void setCustSt(String custSt) {
		this.custSt = custSt;
	}
	public String getReturnUrl() {
		return returnUrl;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}
	public String getResultFlag() {
		return resultFlag;
	}
	public void setResultFlag(String resultFlag) {
		this.resultFlag = resultFlag;
	}
	public String getResultCd() {
		return resultCd;
	}
	public void setResultCd(String resultCd) {
		this.resultCd = resultCd;
	}
	public boolean isLoginByJoin() {
		return loginByJoin;
	}
	public void setLoginByJoin(boolean loginByJoin) {
		this.loginByJoin = loginByJoin;
	}
	public String getIsolYn() {
		return isolYn;
	}
	public void setIsolYn(String isolYn) {
		this.isolYn = isolYn;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	public String getAuthCd() {
		return authCd;
	}
	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}
	public String getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(String totalTime) {
		this.totalTime = totalTime;
	}
	public String getLoginIp() {
		return loginIp;
	}
	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}
	public int getFailCnt() {
		return failCnt;
	}
	public void setFailCnt(int failCnt) {
		this.failCnt = failCnt;
	}
	public int getLastPwdGap() {
		return lastPwdGap;
	}
	public void setLastPwdGap(int lastPwdGap) {
		this.lastPwdGap = lastPwdGap;
	}
	public int getNextPwdGap() {
		return nextPwdGap;
	}
	public void setNextPwdGap(int nextPwdGap) {
		this.nextPwdGap = nextPwdGap;
	}
	public double getLastPwdTerm() {
		return lastPwdTerm;
	}
	public void setLastPwdTerm(double lastPwdTerm) {
		this.lastPwdTerm = lastPwdTerm;
	}
	public double getNextPwdTerm() {
		return nextPwdTerm;
	}
	public void setNextPwdTerm(double nextPwdTerm) {
		this.nextPwdTerm = nextPwdTerm;
	}
	public double getMustPwdTerm() {
		return mustPwdTerm;
	}
	public void setMustPwdTerm(double mustPwdTerm) {
		this.mustPwdTerm = mustPwdTerm;
	}
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType) {
		this.testType = testType;
	}
	public String getAutoVersion() {
		return autoVersion;
	}
	public void setAutoVersion(String autoVersion) {
		this.autoVersion = autoVersion;
	}
	public String getHpNum() {
		return hpNum;
	}
	public void setHpNum(String hpNum) {
		this.hpNum = hpNum;
	}
	public String getBusinessNum() {
		return businessNum;
	}
	public void setBusinessNum(String businessNum) {
		this.businessNum = businessNum;
	}
	public String getCompanyNm() {
		return companyNm;
	}
	public void setCompanyNm(String companyNm) {
		this.companyNm = companyNm;
	}
	public String getRepNm() {
		return repNm;
	}
	public void setRepNm(String repNm) {
		this.repNm = repNm;
	}
	public String getMemberCd() {
		return memberCd;
	}
	public void setMemberCd(String memberCd) {
		this.memberCd = memberCd;
	}
	public String getLicenseFileId() {
		return licenseFileId;
	}
	public void setLicenseFileId(String licenseFileId) {
		this.licenseFileId = licenseFileId;
	}
	public String getDailyCheck() {
		return dailyCheck;
	}
	public void setDailyCheck(String dailyCheck) {
		this.dailyCheck = dailyCheck;
	}
	public String getAgreeDate() {
		return agreeDate;
	}
	public void setAgreeDate(String agreeDate) {
		this.agreeDate = agreeDate;
	}
	public String getAuthFirst() {
		return authFirst;
	}
	public void setAuthFirst(String authFirst) {
		this.authFirst = authFirst;
	}
	public String getLangTp() {
		return langTp;
	}
	public void setLangTp(String langTp) {
		this.langTp = langTp;
	}
	public String getDemoCustTp() {
		return demoCustTp;
	}
	public void setDemoCustTp(String demoCustTp) {
		this.demoCustTp = demoCustTp;
	}
	public String getUsed() {
		return used;
	}
	public void setUsed(String used) {
		this.used = used;
	}
	public String getCompanyCd() {
		return companyCd;
	}
	public void setCompanyCd(String companyCd) {
		this.companyCd = companyCd;
	}
}