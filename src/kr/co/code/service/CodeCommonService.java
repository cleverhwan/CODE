package kr.co.code.service;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import kr.co.code.biz.common.security.util.CodeUserDetailsUtil;
import kr.co.code.biz.common.vo.SessionUserVO;
import kr.co.code.common.exception.CodeException;
import kr.co.code.common.util.DataMap;
import kr.co.code.dao.CodeCommonDao;

@Service
public class CodeCommonService {

	@Autowired
	private CodeCommonDao commonDao;

	@SuppressWarnings("rawtypes")
	public List<Map> select(String sQueryID) throws CodeException {
		try {
			if (sQueryID == null) throw new IllegalArgumentException("QueryID is null!!!");
			return this.commonDao.select(sQueryID);
		} catch (DataAccessException dae) {
			throw new CodeException(dae);
		} catch (Exception e) {
			throw new CodeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public List<Map> select(String sQueryID, Map mParam) throws CodeException {
		try {
			if (sQueryID == null) throw new IllegalArgumentException("QueryID is null!!!");
			return this.commonDao.select(sQueryID, mParam);
		} catch (DataAccessException dae) {
			throw new CodeException(dae);
		} catch (Exception e) {
			throw new CodeException(e);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public int selectCnt(String sQueryID, Map mParam) throws CodeException {
		try {
			if (sQueryID == null) throw new IllegalArgumentException("QueryID is null!!!");
			return this.commonDao.selectCnt(sQueryID, mParam);
		} catch (DataAccessException dae) {
			throw new CodeException(dae);
		} catch (Exception e) {
			throw new CodeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public List<Map> select(String sQueryID, Object ... obj) throws CodeException {
		try {
			if (sQueryID == null) throw new IllegalArgumentException("QueryID is null!!!");
			return this.commonDao.select(sQueryID, obj);
		} catch (DataAccessException dae) {
			throw new CodeException(dae);
		} catch (Exception e) {
			throw new CodeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public Map selectRow(String sQueryID) throws CodeException {
		try {
			if (sQueryID == null) throw new IllegalArgumentException("QueryID is null!!!");
			return this.commonDao.selectRow(sQueryID);
		} catch (DataAccessException dae) {
			throw new CodeException(dae);
		} catch (Exception e) {
			throw new CodeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public Map selectRow(String sQueryID, Map mParam) throws CodeException {
		try {
			if (sQueryID == null) throw new IllegalArgumentException("QueryID is null!!!");
			return this.commonDao.selectRow(sQueryID, mParam);
		} catch (DataAccessException dae) {
			throw new CodeException(dae);
		} catch (Exception e) {
			throw new CodeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public Map selectRow(String sQueryID, Object ... obj) throws CodeException {
		try {
			if (sQueryID == null) throw new IllegalArgumentException("QueryID is null!!!");
			return this.commonDao.selectRow(sQueryID, obj);
		} catch (DataAccessException dae) {
			throw new CodeException(dae);
		} catch (Exception e) {
			throw new CodeException(e);
		}
	}

	public Object selectItem(String sQueryID) throws CodeException {
		try {
			if (sQueryID == null) throw new IllegalArgumentException("QueryID is null!!!");
			return this.commonDao.selectItem(sQueryID);
		} catch (DataAccessException dae) {
			throw new CodeException(dae);
		} catch (Exception e) {
			throw new CodeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public Object selectItem(String sQueryID, Map mParam) throws CodeException {
		try {
			if (sQueryID == null) throw new IllegalArgumentException("QueryID is null!!!");
			return this.commonDao.selectItem(sQueryID, mParam);
		} catch (DataAccessException dae) {
			throw new CodeException(dae);
		} catch (Exception e) {
			throw new CodeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public Object insert(String sQueryID, Map mParam) throws CodeException {
		try {
			if (sQueryID == null) throw new IllegalArgumentException("QueryID is null!!!");
			return this.commonDao.insert(sQueryID, mParam);
		} catch (DataAccessException dae) {
			throw new CodeException(dae);
		} catch (Exception e) {
			throw new CodeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public int update(String sQueryID, Map mParam) throws CodeException {
		try {
			if (sQueryID == null) throw new IllegalArgumentException("QueryID is null!!!");
			return this.commonDao.update(sQueryID, mParam);
		} catch (DataAccessException dae) {
			throw new CodeException(dae);
		} catch (Exception e) {
			throw new CodeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public int delete(String sQueryID, Map mParam) throws CodeException {
		try {
			if (sQueryID == null) throw new IllegalArgumentException("QueryID is null!!!");
			return this.commonDao.delete(sQueryID, mParam);
		} catch (DataAccessException dae) {
			throw new CodeException(dae);
		} catch (Exception e) {
			throw new CodeException(e);
		}
	}

	/**
	 * 페이지 기능 역할에 따른 URL을 반환한다.
	 * @param pRequest HttpServletRequest 객체
	 * @param psPageId 페이지 기능 아이디
	 * @param psUrl 페이지 URL
	 * @return
	 */
	public String chkRoleUrl( HttpServletRequest pRequest, String psPageId, String psUrl ) {
		if( chkRole( pRequest, psPageId ) ) {
			return psUrl;
		} else {
			return "/home/loginBefore";
		}
	}

	/**
	 * 페이지 기능에 대한 권한을 알아온다.
	 * @param pRequest
	 * @param psId
	 * @return
	 */
	public boolean chkRole(HttpServletRequest pRequest, String psId) {
		SessionUserVO oUser = CodeUserDetailsUtil.getUser(pRequest);
		if( oUser == null ) {
			return false;
		} else {
			DataMap oFuncInfo = oUser.getDataMap();
			if( oFuncInfo == null || oFuncInfo.isEmpty() ) {
				return false;
			} else {
				String sResult = oFuncInfo.getString(psId);
				if( sResult == null || sResult.trim().isEmpty() || !sResult.equals("Y") ) {
					return false;
				} else {
					return true;
				}
			}
		}
	}

	/**
	 * Byte 배열 Excel File을 전송한다.
	 * @param pResponse HttpServletResponse 객체
	 * @param pData Byte 배열
	 */
	public void sendExcelData( HttpServletResponse pResponse, HSSFWorkbook pWorkbook, String pFileName ) {
		pResponse.setHeader( "Content-Type", "application/octet-stream" );
		pResponse.setHeader( "Content-Disposition", "attachment; filename=" + pFileName );

		OutputStream oOutputStream = null;
		 try {
			oOutputStream = pResponse.getOutputStream();

			pWorkbook.write( oOutputStream );
		} catch ( IOException ioe ) {
			ioe.printStackTrace();
		} finally {
			if( oOutputStream != null ) {
				try {
					oOutputStream.flush();
					oOutputStream.close();
					oOutputStream = null;
				} catch( IOException ioe ) {
					ioe.printStackTrace();
				}
			}
		}
	}}
