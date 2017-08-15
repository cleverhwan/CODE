/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.vo;

import kr.co.code.biz.common.base.BaseVO;
import kr.co.code.common.util.DataMap;

/**
* 업무명 : 인증 공통
* 설  명 : Session 객체 자체를 가지고 다니지 않고 고객정보를 모아 놓은 SessionUserVO 객체를 조회
*         SBnfUserDetailUtil.java 를 통해 참조
*
* 작성일 : 2014.02
* 작성자 :
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
public class SessionUserVO extends BaseVO{
	private static final long serialVersionUID = -5949523291645518490L;

	private String sesstionId; //세션 ID
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
	private String company; //고객회사
	private String note; //고객노트
	private String returnUrl; //인증 후 이동할 URL
	private String resultFlag; //인증 처리 결과 유형 : LoginConstant에 정의된 상수
	private String resultCd; //로그인 처리 상태 코드
	private boolean loginByJoin; //회원가입 후 로그인 호출 여부
	private String testType; //테스트 유형
	private String[] testTypes;
	private String forwardUrl;
	private String autoVersion;
	private String businessNum; //사업자 등록번호
	private String companyNm; //기업명
	private String repNm; //대표자명
	private String memberCd; //회원구분(P:개인, C:법인, H:임직원)
	private String hpNum; //휴대전화
	private String licenseFileId; //사업자등록증
	private String dailyCheck;
	private String agreeDate;
	private String DemoCustTp;
	private String langTp;
	private DataMap dataMap;

	public String getSesstionId() {
		return sesstionId;
	}
	public void setSesstionId(String sesstionId) {
		this.sesstionId = sesstionId;
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
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
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
	public String getTestType() {
		return testType;
	}
	public void setTestType(String testType){
		this.testType = testType;
		if (testType != null) {
			this.testTypes = testType.split(",");
		}
		else {
			this.testTypes = null;
		}
	}
	public String[] getTestTypes() {
		return testTypes;
	}
	public void setTestTypes(String[] testTypes) {
		this.testTypes = testTypes;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	public String getAutoVersion() {
		return autoVersion;
	}
	public void setAutoVersion(String autoVersion) {
		this.autoVersion = autoVersion;
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
	public String getHpNum() {
		return hpNum;
	}
	public void setHpNum(String hpNum) {
		this.hpNum = hpNum;
	}
	@Override
	public String getLicenseFileId() {
		return licenseFileId;
	}
	@Override
	public void setLicenseFileId(String licenseFileId) {
		this.licenseFileId = licenseFileId;
	}

	public boolean hasTestType(String testType) {
		if (testType == null || this.testType == null) return false;
		for (String type : this.testTypes) {
			if (type == null) continue;
			if (testType.equals(type)) return true;
		}
		return false;
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
	public String getLangTp() {
		return langTp;
	}
	public void setLangTp(String langTp) {
		this.langTp = langTp;
	}
	public String getDemoCustTp() {
		return DemoCustTp;
	}
	public void setDemoCustTp(String demoCustTp) {
		DemoCustTp = demoCustTp;
	}
	public DataMap getDataMap() {
		return dataMap;
	}
	public void setDataMap(DataMap dataMap) {
		this.dataMap = dataMap;
	}
}
