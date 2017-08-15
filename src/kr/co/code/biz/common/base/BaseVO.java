/*
* Copyright(c) 2014 by SK Telecom co.ltd all right reserved.
 */
package kr.co.code.biz.common.base;

import kr.co.code.biz.common.security.userdetails.CodeUser;
import kr.co.code.biz.common.security.util.CodeUserDetailsUtil;
import kr.co.code.common.exception.CodeException;
import kr.co.code.common.util.DateUtil;
import kr.co.code.common.util.PageIndex;
import kr.co.code.common.util.StringUtil;

/**
* 업무명 : 공통
* 설  명 : VO의 상위 클래스
* 작성일 : 2014.02
* 작성자 : 
* 변경자/ 변경일 :
* 변경 사유/내역 :
*/
public class BaseVO extends BaseObject{
	private static final long serialVersionUID = 9203893454781173051L;

	public static final Integer	INT_NULL	= -99999;
	public static final Long		LONG_NULL	= -9999999999L;
	public static final String	STR_NULL	= "";
	public static final String	YES = "Y";
	public static final String	NO = "N";

	private String regDt; //등록일
	private String regId; //등록자 ID
	private String regNm; //등록자명
	private String modifyDt; //수정일
	private String modifyId; //수정자 ID
	private String modifyNm; //수정자명
	private int pageNo; //현재 페이지 번호
	private int listSize; //페이지당 출력될 리스트 개수
	private long totalCount; //전체 개수
	private int subCount; //소계 등에 사용
	private int subOrdNum;
	private String	orderByVarName = ""; //ORDER BY 절
	private boolean orderByAsc;
	private String	srchStartDate = ""; //검색시 시작/종료 일자
	private String	srchEndDate = "";
	private String	licenseFileId = "";

	protected BaseVO(){
		//Security Context에서 꺼내오는 정보이므로 Clustrering 환경에서 안전한 정보만 조회
		//(세션정보 생성 후 수정이 발생하지 않을 데이터)
		CodeUser CodeUser = (CodeUser)CodeUserDetailsUtil.getSecurityUser();

		if(CodeUser != null) {
			this.regId = CodeUser.getCustId();
			this.regNm = CodeUser.getCustNm();
			this.modifyId = CodeUser.getCustId();
			this.modifyNm = CodeUser.getCustNm();
		}

		this.regDt = null;
		this.modifyDt = null;
		this.pageNo = 1;
		this.listSize = PageIndex.DEFAULT_PAGE_LIST_SIZE;
		this.totalCount = 0;
		this.orderByVarName = "";
		this.orderByAsc = true;
		this.srchStartDate = "";
		this.srchEndDate = "";
	}

	/**
	 * @return the regDt (YYYYMMDD)
	 */
	public String getRegDt() {
		return getDate(regDt);
	}


	/**
	 * @return the regDt (HHMISS)
	 */
	public String getRegTime() {
		return getTime(regDt);
	}


	/**
	 * @param regDt the regDt to set
	 */
	public void setRegDt(String regDt) {
		this.regDt = regDt;
	}


	/**
	 * @return the regId
	 */
	public String getRegId() {
		return regId;
	}


	/**
	 * @param regId the regId to set
	 */
	public void setRegId(String regId) {
		this.regId = regId;
	}


	/**
	 * @return the regNm
	 */
	public String getRegNm() {
		return regNm;
	}


	/**
	 * @param regNm the regNm to set
	 */
	public void setRegNm(String regNm) {
		this.regNm = regNm;
	}


	/**
	 * @return the modifyDt (YYYYMMDD)
	 */
	public String getModifyDt() {
		return getDate(modifyDt);
	}


	/**
	 * @return the modifyDt (HHMISS)
	 */
	public String getModifyTime() {
		return getTime(modifyDt);
	}


	/**
	 * @param modifyDt the modifyDt to set
	 */
	public void setModifyDt(String modifyDt) {
		this.modifyDt = modifyDt;
	}


	/**
	 * @return the modifyId
	 */
	public String getModifyId() {
		return modifyId;
	}


	/**
	 * @param modifyId the modifyId to set
	 */
	public void setModifyId(String modifyId) {
		this.modifyId = modifyId;
	}


	/**
	 * @return the modifyNm
	 */
	public String getModifyNm() {
		return modifyNm;
	}


	/**
	 * @param modifyNm the modifyNm to set
	 */
	public void setModifyNm(String modifyNm) {
		this.modifyNm = modifyNm;
	}


	/**
	 * @return the pageNo
	 */
	public int getPageNo() {
		return pageNo;
	}


	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}


	/**
	 * @return the listSize
	 */
	public int getListSize() {
		return listSize;
	}


	/**
	 * @param listSize the listSize to set
	 */
	public void setListSize(int listSize) {
		this.listSize = listSize;
	}


	/**
	 * @return the totalCount
	 */
	public long getTotalCount() {
		return totalCount;
	}


	/**
	 * @param totalCount the totalCount to set
	 */
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}


	/**
	 * @return the subCount
	 */
	public int getSubCount() {
		return subCount;
	}


	/**
	 * @param subCount the subCount to set
	 */
	public void setSubCount(int subCount) {
		this.subCount = subCount;
	}


	/**
	 * @return the subOrdNum
	 */
	public int getSubOrdNum() {
		return subOrdNum;
	}


	/**
	 * @param subOrdNum the subOrdNum to set
	 */
	public void setSubOrdNum(int subOrdNum) {
		this.subOrdNum = subOrdNum;
	}


	/**
	 * @return the orderByVarName
	 */
	public String getOrderByVarName() {
		return orderByVarName;
	}


	/**
	 * @param orderByVarName the orderByVarName to set
	 */
	public void setOrderByVarName(String orderByVarName) {
		this.orderByVarName = orderByVarName;
	}


	/**
	 * @return the orderByAsc
	 */
	public boolean isOrderByAsc() {
		return orderByAsc;
	}


	/**
	 * @param orderByAsc the orderByAsc to set
	 */
	public void setOrderByAsc(boolean orderByAsc) {
		this.orderByAsc = orderByAsc;
	}


	/**
	 * @return the srchStartDate
	 */
	public String getSrchStartDate() {
		return srchStartDate;
	}


	/**
	 * @param srchStartDate the srchStartDate to set
	 */
	public void setSrchStartDate(String srchStartDate) {
		this.srchStartDate = srchStartDate;
	}


	/**
	 * @return the srchEndDate
	 */
	public String getSrchEndDate() {
		return srchEndDate;
	}


	/**
	 * @param srchEndDate the srchEndDate to set
	 */
	public void setSrchEndDate(String srchEndDate) {
		this.srchEndDate = srchEndDate;
	}


	private String getDate(String dt) {
		if (dt != null && dt.length() >= 8)
			return dt.substring(0, 8);
		return "";
	}

	private String getTime(String dt) {
		if (dt != null && dt.length() >= 14)
			return dt.substring(8, 14);
		return "";
	}

	protected String getFormatDate(String date) throws CodeException {
		String	formatDate = DateUtil.formatDate(date);
		if (! StringUtil.isEmpty(formatDate) &&
				formatDate.length() != 8)
			throw new CodeException(CodeException.PARAM_LENGTH_MISMATCH);
		return formatDate;
	}
	protected String getFormatTime(String time) throws CodeException {
		String	formatTime = DateUtil.formatTime(time);
		if (! StringUtil.isEmpty(formatTime) &&
				formatTime.length() != 6)
			throw new CodeException(CodeException.PARAM_LENGTH_MISMATCH);
		return formatTime;
	}
	protected String getFormatYn(String yn) throws CodeException {
		if (StringUtil.isEmpty(yn)) {
			return yn;
		}
		else if (YES.equals(yn) ||
				NO.equals(yn)) {
			return yn;
		}
		else {
			throw new CodeException(CodeException.PARAM_VALUE_MISMATCH);
		}
	}
	protected String getFormatYn(String yn, String defaultVal) {
		if (StringUtil.isEmpty(yn)) {
			return yn;
		}
		else if (YES.equals(yn) ||
				NO.equals(yn)) {
			return yn;
		}
		else {
			return defaultVal;
		}
	}

	public void setOrderBy(String orderByVarName, boolean orderByAsc) {
		setOrderByVarName(orderByVarName);
		setOrderByAsc(orderByAsc);
	}

	public String getLicenseFileId() {
		return licenseFileId;
	}

	public void setLicenseFileId(String licenseFileId) {
		this.licenseFileId = licenseFileId;
	}
}