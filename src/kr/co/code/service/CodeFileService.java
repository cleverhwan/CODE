package kr.co.code.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.code.common.exception.CodeException;
import kr.co.code.common.message.CodePropertyHandler;
import kr.co.code.common.util.UploadUtil;
import kr.co.code.controller.CodeCommonController;
import kr.co.code.dao.CodeCommonDao;

/**
 * 첨부 파일 저장 / 삭제
 * 첨부 파일 정보 저장 / 삭제 / 검색
 *
 */
@Service
public class CodeFileService {
	/**
	 * 첨부파일 타입 키
	 */
	public static final String sKEY_ATTACH_TYPE = "attach_type";
	/**
	 * 첨부파일 아이디 키
	 */
	public static final String sKEY_ATTACH_ID = "attach_id";
	/**
	 * 임시 첨부파일 아이디 키
	 */
	public static final String sKEY_ATTACH_TEMP_ID = "attach_temp_id";
	/**
	 * 첨부파일 순번 키
	 */
	public static final String sKEY_ATTACH_NO = "attach_no";
	/**
	 * 파일 경로
	 */
	private final String sKEY_FILE_PATH = "file_path";
	/**
	 * 원래 파일 이름
	 */
	public static final String sKEY_ORIGINAL_FILE_NAME = "original_file_name";
	/**
	 * 저장된 파일 이름
	 */
	private final String sKEY_SAVE_FILE_NAME = "save_file_name";
	/**
	 * 등록 사용자 아이디
	 */
	private final String sKEY_REGIST_ID = "regist_id";
	/**
	 * 원래 파일 이름
	 */
	public static final String sKEY_FILE_URI = "file_uri";

	/**
	 * 업로드 루트 패스
	 */
	private final String sUPLOAD_ROOT_PATH = CodePropertyHandler.getMessage("Globals.fileStorePath");
	/**
	 * 파일 구분자(윈도우 용)
	 */
	private final String sWINDO_FILE_SEPA = "\\\\";
	/**
	 * 파일 구분자(리눅스 용)
	 */
	public static final String sLINUX_FILE_SEPA = "/";

	/**
	 * 첨부파일 정보 저장 iBatis 아이디
	 */
	private final String sID_SAVE_ATTACH_FILE_INFO = "AttachFile.saveAttachFileInfo";
	/**
	 * 첨부된 모든 파일 정보 삭제 iBatis 아이디
	 */
	private final String sID_DEL_ATTACH_INFO = "AttachFile.delAttachInfo";
	/**
	 * 화면에 보여질 첨부파일 정보 목록 검색 iBatis 아이디
	 */
	private final String sID_GET_ALL_ATTACH_FILE_LIST_INFO = "AttachFile.getAllAttachFileListInfo";
	/**
	 * 첨부파일 저장경로 검색 iBatis 아이디
	 */
	private final String sID_GET_ATTACH_FILE_INFO = "AttachFile.getAttachFileInfo";
	/**
	 * 임시 첨부파일 저장경로 검색 iBatis 아이디
	 */
	private final String sID_GET_ATTACH_TEMP_FILE_INFO = "AttachFile.getAttachTempFileInfo";
	/**
	 * 첨부파일 저장경로 목록 검색 iBatis 아이디
	 */
	private final String sID_GET_ALL_ATTACH_FILES_INFO = "AttachFile.getAllAttachFilesInfo";

	protected Log log = LogFactory.getLog(this.getClass());

	@Autowired
	private CodeCommonDao mQcCommonDao;

	public Map<String,Object> getCommonInfoList(Map<String,Object> param){
		Map<String,Object> map = new HashMap<String,Object>();
		
		//to - do
		return map;
	}

	/**
	 * 다운로드할 첨부파일 정보를 얻는다.
	 * @param psAttachType	첨부 유형
	 * @param psAttachId 첨부 아이디
	 * @param psAttachNo 첨부파일 순번
	 * @return 파일 정보
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> getAttachFileDownloadInfo( String psAttachType, String psAttachId, String psAttachNo ){
		Map<String, Object> Map_Param = new HashMap<String,Object>();
		Map_Param.put( CodeFileService.sKEY_ATTACH_TYPE, psAttachType );
		Map_Param.put( CodeFileService.sKEY_ATTACH_ID, psAttachId );
		Map_Param.put( CodeFileService.sKEY_ATTACH_NO, psAttachNo );

		List<Map> List_Result = mQcCommonDao.select( sID_GET_ATTACH_FILE_INFO, Map_Param );
		if( List_Result.isEmpty() ) {
			return new HashMap<String,Object>();
		} else {
			return (Map<String, Object>) List_Result.get( 0 );
		}
	}
	
	/**
	 * 다운로드할 첨부파일 정보를 얻는다.
	 * @param psAttachType	첨부 유형
	 * @param psAttachId 첨부 아이디
	 * @param psAttachNo 첨부파일 순번
	 * @return 파일 정보
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Map<String, Object> getAttachTempFileDownloadInfo( String psAttachType, String psAttachId, String psAttachNo ){
		Map<String, Object> Map_Param = new HashMap<String,Object>();
		Map_Param.put( CodeFileService.sKEY_ATTACH_TYPE, psAttachType );
		Map_Param.put( CodeFileService.sKEY_ATTACH_TEMP_ID, psAttachId );
		Map_Param.put( CodeFileService.sKEY_ATTACH_NO, psAttachNo );

		List<Map> List_Result = mQcCommonDao.select( sID_GET_ATTACH_TEMP_FILE_INFO, Map_Param );
		if( List_Result.isEmpty() ) {
			return new HashMap<String,Object>();
		} else {
			return (Map<String, Object>) List_Result.get( 0 );
		}
	}

	/**
	 * 파일 업로드. 브라우저에서 첨부한 파일들을 해당 위치에 저장한다.
	 * @param psAttachType 첨부파일 타입
	 * @param psAttachId 첨부파일들 대분류 아이디
	 * @param psCustId 사용자 아이디
	 * @param pMap_MultiFile MultipartFile 객체들이 들어있는 Map 객체
	 * @param psUploadSubPath 업로드 루트 패스 하위에 생성할 폴더 명
	 * @return 업로드된 파일정보 목록
	 * @throws CodeException 
	 * @throws IOException 
	 */
	public Map<String,Object> saveAttachFileUpload( String psAttachType, String psAttachId, String psCustId, Map<String, MultipartFile> pMap_MultiFile, String psUploadSubPath ) throws IOException, CodeException {
		Map<String, Object> Map_Result = new HashMap<String, Object>();
		List<Map<String,Object>> List_FileInfo = new ArrayList<Map<String,Object>>();
		
		try {
			if( pMap_MultiFile == null || pMap_MultiFile.isEmpty() ) {
				Map_Result.put( CodeCommonController.sRES_KEY_RESULT, CodeCommonController.sRESULT_VALUE_FAIL );
				Map_Result.put( CodeCommonController.sRES_KEY_MESSAGE, "업로드할 파일이 없습니다.(1)" );
	 		} else {
				Set<String> Set_FileName = pMap_MultiFile.keySet();
				if( Set_FileName == null ) {
					Map_Result.put( CodeCommonController.sRES_KEY_RESULT, CodeCommonController.sRESULT_VALUE_FAIL );
					Map_Result.put( CodeCommonController.sRES_KEY_MESSAGE, "업로드할 파일이 없습니다.(2)" );
				} else {
					for( String sFileName : Set_FileName ) {
						if( sFileName == null || sFileName.trim().isEmpty() ) {
							continue;
						} else {
							MultipartFile oFile = pMap_MultiFile.get( sFileName );
							if( oFile == null || oFile.isEmpty() ) {
								continue;
							} else {
								Map<String, Object> Map_FileInfo = UploadUtil.upload( oFile, sUPLOAD_ROOT_PATH + trimSubPath( psUploadSubPath ) );
								List_FileInfo.add( Map_FileInfo );
							}
						}
					}

					if( List_FileInfo.isEmpty() ) {
						Map_Result.put( CodeCommonController.sRES_KEY_RESULT, CodeCommonController.sRESULT_VALUE_FAIL );
						Map_Result.put( CodeCommonController.sRES_KEY_MESSAGE, "업로드할 파일이 없습니다.(3)" );
					} else {
						Map_Result.put( CodeCommonController.sRES_KEY_RESULT, CodeCommonController.sRESULT_VALUE_SUCCESS );
						Map_Result.put( CodeCommonController.sRES_KEY_DATA_INFO, List_FileInfo );

						saveAttachFileInfo(psAttachType, psAttachId, psCustId, List_FileInfo);
					}
				}
			}
		} catch( IOException e ) {
			Map_Result.put( CodeCommonController.sRES_KEY_RESULT, CodeCommonController.sRESULT_VALUE_FAIL );
			Map_Result.put( CodeCommonController.sRES_KEY_MESSAGE, "업로드 중 오류발생 : " + e.getMessage() );

			new CodeException( e );
		}

		return Map_Result;
	}

	/**
	 * 파일 업로드. 브라우저에서 첨부한 파일들을 해당 위치에 저장한다. :: 개별 파일 업로드로.. 각 로우별 attach 아이디가 필요한 경우
	 * @param psAttachType 첨부파일 타입
	 * @param psAttachId 첨부파일들 대분류 아이디
	 * @param psCustId 사용자 아이디
	 * @param oFile MultipartFile 객체
	 * @param psUploadSubPath 업로드 루트 패스 하위에 생성할 폴더 명
	 * @return 업로드된 파일정보 목록
	 * @throws CodeException 
	 * @throws IOException 
	 */
	public Map<String,Object> saveEachAttachFileUpload( String psAttachType, String psAttachId, String psCustId, MultipartFile oFile, String psUploadSubPath ) throws IOException, CodeException {
		Map<String, Object> Map_Result = new HashMap<String, Object>();
		List<Map<String,Object>> List_FileInfo = new ArrayList<Map<String,Object>>();
		try {
					if( oFile != null && oFile.isEmpty()==false  ) {
						Map<String, Object> Map_FileInfo = UploadUtil.upload( oFile, sUPLOAD_ROOT_PATH + trimSubPath( psUploadSubPath ) );
						List_FileInfo.add( Map_FileInfo );
					}

					if( List_FileInfo.isEmpty() ) {
						Map_Result.put( CodeCommonController.sRES_KEY_RESULT, CodeCommonController.sRESULT_VALUE_FAIL );
						Map_Result.put( CodeCommonController.sRES_KEY_MESSAGE, "업로드할 파일이 없습니다.(3)" );
					} else {
						Map_Result.put( CodeCommonController.sRES_KEY_RESULT, CodeCommonController.sRESULT_VALUE_SUCCESS );
						Map_Result.put( CodeCommonController.sRES_KEY_DATA_INFO, List_FileInfo );

						saveAttachFileInfo(psAttachType, psAttachId, psCustId, List_FileInfo);
					}
		} catch( IOException e ) {
			Map_Result.put( CodeCommonController.sRES_KEY_RESULT, CodeCommonController.sRESULT_VALUE_FAIL );
			Map_Result.put( CodeCommonController.sRES_KEY_MESSAGE, "업로드 중 오류발생 : " + e.getMessage() );

			new CodeException( e );
		}

		return Map_Result;
	}

	/**
	 * Sub Path를 형식에 맞게 수정하여 반환.<br/>예) '\path\01' > 'path/01/'
	 * @param psInputSubPath 원본 Sub Path
	 * @return 변경된 Sub Path
	 */
	private String trimSubPath( String psInputSubPath ) {
		
		String sSubPath = psInputSubPath;
		if( sSubPath == null ) {
			sSubPath = "";
		} else {
			sSubPath = psInputSubPath.trim().replaceAll( sWINDO_FILE_SEPA, CodeFileService.sLINUX_FILE_SEPA );

			String sTemp = sSubPath.substring( 0, 1 );
			if( sTemp.equals( CodeFileService.sLINUX_FILE_SEPA ) ) {
				sSubPath = sSubPath.substring( 1 );
			}

			sTemp = sSubPath.substring( sSubPath.length() - 1 );
			if( !sTemp.equals( CodeFileService.sLINUX_FILE_SEPA ) ) {
				sSubPath += CodeFileService.sLINUX_FILE_SEPA;
			}
		}

		return sSubPath;
	}

	/**
	 * 첨부파일 Upload 정보를 저장한다.
	 * @param psAttachType 첨부파일 타입
	 * @param psAttachId 첨부파일들 대분류 아이디
	 * @param psCustId 사용자 아이디
	 * @param List_FileInfo 첨부파일 업로드 정보
	 */
	private void saveAttachFileInfo( String psAttachType, String psAttachId, String psCustId, List<Map<String,Object>> List_FileInfo ) {
		Map<String, Object> Map_Temp = new HashMap<String, Object>();
		Map_Temp.put( CodeFileService.sKEY_ATTACH_TYPE, psAttachType );
		Map_Temp.put( CodeFileService.sKEY_ATTACH_ID, psAttachId );
		Map_Temp.put( CodeFileService.sKEY_ATTACH_TYPE+"2", psAttachType );
		Map_Temp.put( CodeFileService.sKEY_ATTACH_ID+"2", psAttachId );
		Map_Temp.put( sKEY_REGIST_ID, psCustId);

		for( Map<String,Object> Map_FileInfo : List_FileInfo ) {
			Map_Temp.put( sKEY_FILE_PATH, Map_FileInfo.get("path") );
			Map_Temp.put( sKEY_ORIGINAL_FILE_NAME, Map_FileInfo.get("orgfilename") );
			Map_Temp.put( sKEY_SAVE_FILE_NAME, Map_FileInfo.get("filename") );

			mQcCommonDao.insert(sID_SAVE_ATTACH_FILE_INFO, Map_Temp);
		}
	}

	/**
	 * 첨부파일들의 정보를 얻는다.
	 * @param psAttachType	첨부 유형
	 * @param psAttachId 첨부 아이디
	 * @return 첨부 아이디에 속해있는 첨부 파일 목록
	 */
	@SuppressWarnings("rawtypes")
	public List<Map> getAllAttachFileListInfo( String psAttachType, String psAttachId ) {
		Map<String, Object> Map_Param = new HashMap<String,Object>();
		Map_Param.put( CodeFileService.sKEY_ATTACH_TYPE, psAttachType );
		Map_Param.put( CodeFileService.sKEY_ATTACH_ID, psAttachId );

		List<Map> List_Result = mQcCommonDao.select( sID_GET_ALL_ATTACH_FILE_LIST_INFO, Map_Param );
		if( List_Result == null || List_Result.isEmpty() ) {
			return new ArrayList<Map>();
		} else {
			return List_Result;
		}
	}

	/**
	 * 첨부된 모든 파일 및 정보를 지운다.
	 * @param psAttachType	첨부 유형
	 * @param psAttachId 첨부 아이디
	 * @param psAttachNo 첨부파일 순번
	 * @return 
	 */
	@SuppressWarnings("rawtypes")
	public int delAttach( String psAttachType, String psAttachId , String psAttachNo) {
		Map<String, Object> Map_Param = new HashMap<String,Object>();
		Map_Param.put( CodeFileService.sKEY_ATTACH_TYPE, psAttachType );
		Map_Param.put( CodeFileService.sKEY_ATTACH_ID, psAttachId );
		Map_Param.put( CodeFileService.sKEY_ATTACH_NO, psAttachNo );

		List<Map> List_Result = mQcCommonDao.select( sID_GET_ALL_ATTACH_FILES_INFO, Map_Param );

		for( Map Map_Temp : List_Result ) {
			if( Map_Temp != null && !Map_Temp.isEmpty() ) {
				try {
					FileUtils.forceDelete( new File( (String) Map_Temp.get( CodeFileService.sKEY_FILE_URI ) ) );
				} catch (IOException e) {
					log.debug(" 오류 :: " + e.getMessage());
				}
			}
		}

		return mQcCommonDao.delete( sID_DEL_ATTACH_INFO, Map_Param );
	}

}