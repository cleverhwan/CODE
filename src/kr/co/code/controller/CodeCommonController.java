package kr.co.code.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.code.biz.common.security.util.CodeUserDetailsUtil;
import kr.co.code.biz.common.vo.SessionUserVO;
import kr.co.code.common.util.StringUtil;
import kr.co.code.service.CodeCodeService;
import kr.co.code.service.CodeFileService;


@Controller
public class CodeCommonController {
	/**
	 * Redirect URL 키
	 */
	public static final String sREQ_KEY_REDIRECT_URL = "redirect_url";

	/**
	 * 첨부파일 타입 키
	 */
	public static final String sREQ_KEY_ATTACH_TYPE = CodeFileService.sKEY_ATTACH_TYPE;
	/**
	 * 첨부파일 아이디 키
	 */
	public static final String sREQ_KEY_ATTACH_ID = CodeFileService.sKEY_ATTACH_ID;
	/**
	 * 임시 첨부파일 아이디 키
	 */
	public static final String sREQ_KEY_ATTACH_TEMP_ID = CodeFileService.sKEY_ATTACH_TEMP_ID;
	/**
	 * 첨부파일 순번 키
	 */
	public static final String sREQ_KEY_ATTACH_NO = CodeFileService.sKEY_ATTACH_NO;
	/**
	 * 첨부파일 저장 하위 디렉토리 키
	 */
	public static final String sREQ_KEY_SUB_DIR = "sub_dir";

	/**
	 * 첨부파일 업로드 결과 키
	 */
	public static final String sRES_KEY_RESULT = "RESULT";
	/**
	 * 첨부파일 업로드 결과 메시지 키(업로드 실패의 경우에만 존재)
	 */
	public static final String sRES_KEY_MESSAGE = "MESSAGE";
	/**
	 * 첨부파일 업로드 정보 키(업로드 성공의 경우에만 존재)
	 */
	public static final String sRES_KEY_DATA_INFO = "DATA_INFO";

	/**
	 * 첨부파일 업로드 결과 성공 값
	 */
	public static final String sRESULT_VALUE_SUCCESS = "true";
	/**
	 * 첨부파일 업로드 결과 실패 값
	 */
	public static final String sRESULT_VALUE_FAIL = "false";

	protected Log log = LogFactory.getLog(this.getClass());
	
	@Autowired
	private CodeFileService fileService;
	
	@Autowired
	private CodeCodeService codeService;
	
	/**
	 * QC 메인 페이지 호출 
	 * @param pRequest HttpServletRequest 객체
	 * @return ModelAndView 객체
	 * @throws Exception
	 */
	@RequestMapping(value = "/qc/Main")
	public ModelAndView Main( HttpServletRequest pRequest ) throws Exception {
		SessionUserVO oUser = CodeUserDetailsUtil.getUser( pRequest );
		if( oUser == null ) {
			return new ModelAndView( "/home/home" );
		} else {
			ModelMap oModel = new ModelMap();

			return new ModelAndView( "/qc/index", oModel );
		}
	}

	/**
	 * 첨부파일 다운로드
	 * @param pMap_Param 요청 파라메터
	 * @param pResponse HttpServletResponse 객체
	 * @return ModelAndView 객체
	 * @throws Exception
	 */
	@RequestMapping( value = "/qc/comm/getAttachFileDownload" )
	public ModelAndView getAttachFileDownLoad( @RequestParam Map<String,Object> pMap_Param, HttpServletResponse pResponse ) throws Exception {
		pMap_Param = StringUtil.cleanWeekness( pMap_Param );

		String sAttachType = (String) pMap_Param.get( CodeCommonController.sREQ_KEY_ATTACH_TYPE );
		if( sAttachType == null || sAttachType.trim().isEmpty() ) {
			pResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "첨부파일 요청 정보가 없습니다.(1)" );
		} else {
			String sAttachId = (String) pMap_Param.get( CodeCommonController.sREQ_KEY_ATTACH_ID );
			if( sAttachId == null || sAttachId.trim().isEmpty() ) {
				pResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "첨부파일 요청 정보가 없습니다.(2)" );
			} else {
				String sAttachNo = (String) pMap_Param.get( CodeCommonController.sREQ_KEY_ATTACH_NO );
				if( sAttachNo == null || sAttachNo.trim().isEmpty() ) {
					pResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "첨부파일 요청 정보가 없습니다.(3)" );
				} else {
					Map<String,Object> Map_AttachFileDownloadInfo = fileService.getAttachFileDownloadInfo( sAttachType, sAttachId, sAttachNo );
					if( Map_AttachFileDownloadInfo.isEmpty() ) {
						pResponse.sendError( HttpServletResponse.SC_NO_CONTENT, "첨부파일 정보가 없습니다.(1)" );
					} else {
						String sFilePath = (String) Map_AttachFileDownloadInfo.get( CodeFileService.sKEY_FILE_URI );
						if( sFilePath == null || sFilePath.trim().isEmpty() ) {
							pResponse.sendError( HttpServletResponse.SC_NO_CONTENT, "첨부파일 정보가 없습니다.(2)" );
						} else {
							File oFile = new File(sFilePath);
							if( ! oFile.exists() || ! oFile.isFile() ) {
								pResponse.sendError( HttpServletResponse.SC_NO_CONTENT, "첨부파일 정보가 없습니다.(3)" );
							} else {
								String sOriginalFileName = (String) Map_AttachFileDownloadInfo.get( CodeFileService.sKEY_ORIGINAL_FILE_NAME );

								ModelMap oModelMap = new ModelMap();
								oModelMap.addAttribute( "downloadFile", oFile );
								oModelMap.addAttribute( "originalFileName", sOriginalFileName );

								return new ModelAndView( "downloadUtil", oModelMap );
							}
						}
					}
				}
			}
		}

		return new ModelAndView();
	}
	
	/**
	 * 첨부파일 다운로드
	 * @param pMap_Param 요청 파라메터
	 * @param pResponse HttpServletResponse 객체
	 * @return ModelAndView 객체
	 * @throws Exception
	 */
	@RequestMapping( value = "/qc/comm/getAttachTempFileDownload" )
	public ModelAndView getAttachTempFileDownload( @RequestParam Map<String,Object> pMap_Param, HttpServletResponse pResponse ) throws Exception {
		pMap_Param = StringUtil.cleanWeekness( pMap_Param );

		String sAttachType = (String) pMap_Param.get( CodeCommonController.sREQ_KEY_ATTACH_TYPE );
		if( sAttachType == null || sAttachType.trim().isEmpty() ) {
			pResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "첨부파일 요청 정보가 없습니다.(1)" );
		} else {
			String sAttachId = (String) pMap_Param.get( CodeCommonController.sREQ_KEY_ATTACH_TEMP_ID );
			if( sAttachId == null || sAttachId.trim().isEmpty() ) {
				pResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "첨부파일 요청 정보가 없습니다.(2)" );
			} else {
				String sAttachNo = (String) pMap_Param.get( CodeCommonController.sREQ_KEY_ATTACH_NO );
				if( sAttachNo == null || sAttachNo.trim().isEmpty() ) {
					pResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "첨부파일 요청 정보가 없습니다.(3)" );
				} else {
					Map<String,Object> Map_AttachFileDownloadInfo = fileService.getAttachTempFileDownloadInfo( sAttachType, sAttachId, sAttachNo );
					if( Map_AttachFileDownloadInfo.isEmpty() ) {
						pResponse.sendError( HttpServletResponse.SC_NO_CONTENT, "첨부파일 정보가 없습니다.(1)" );
					} else {
						String sFilePath = (String) Map_AttachFileDownloadInfo.get( CodeFileService.sKEY_FILE_URI );
						if( sFilePath == null || sFilePath.trim().isEmpty() ) {
							pResponse.sendError( HttpServletResponse.SC_NO_CONTENT, "첨부파일 정보가 없습니다.(2)" );
						} else {
							File oFile = new File(sFilePath);
							if( ! oFile.exists() || ! oFile.isFile() ) {
								pResponse.sendError( HttpServletResponse.SC_NO_CONTENT, "첨부파일 정보가 없습니다.(3)" );
							} else {
								String sOriginalFileName = (String) Map_AttachFileDownloadInfo.get( CodeFileService.sKEY_ORIGINAL_FILE_NAME );

								ModelMap oModelMap = new ModelMap();
								oModelMap.addAttribute( "downloadFile", oFile );
								oModelMap.addAttribute( "originalFileName", sOriginalFileName );

								return new ModelAndView( "downloadUtil", oModelMap );
							}
						}
					}
				}
			}
		}

		return new ModelAndView();
	}

	/**
	 * 요청한 첨부 이미지 파일 전송
	 * @param pMap_Param 요청 파라메터
	 * @param pResponse HttpServletResponse 객체
	 * @throws Exception
	 */
	@RequestMapping( value = "/qc/comm/getAttachImage" )
	public void getAttachImage( @RequestParam Map<String,Object> pMap_Param, HttpServletResponse pResponse ) throws Exception {
		pMap_Param = StringUtil.cleanWeekness( pMap_Param );

		String sAttachType = (String) pMap_Param.get( CodeCommonController.sREQ_KEY_ATTACH_TYPE );
		if( sAttachType == null || sAttachType.trim().isEmpty() ) {
			pResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "이미지 요청 정보가 없습니다.(1)" );
		} else {
			String sAttachId = (String) pMap_Param.get( CodeCommonController.sREQ_KEY_ATTACH_ID );
			if( sAttachId == null || sAttachId.trim().isEmpty() ) {
				pResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "이미지 요청 정보가 없습니다.(2)" );
			} else {
				String sAttachNo = (String) pMap_Param.get( CodeCommonController.sREQ_KEY_ATTACH_NO );
				if( sAttachNo == null || sAttachNo.trim().isEmpty() ) {
					pResponse.sendError( HttpServletResponse.SC_BAD_REQUEST, "이미지 요청 정보가 없습니다.(3)" );
				} else {
					Map<String,Object> Map_AttachFileDownloadInfo = fileService.getAttachFileDownloadInfo( sAttachType, sAttachId, sAttachNo );
					if( Map_AttachFileDownloadInfo.isEmpty() ) {
						pResponse.sendError( HttpServletResponse.SC_NO_CONTENT, "이미지 정보가 없습니다.(1)" );
					} else {
						String sFilePath = (String) Map_AttachFileDownloadInfo.get( CodeFileService.sKEY_FILE_URI );
						if( sFilePath == null || sFilePath.trim().isEmpty() ) {
							pResponse.sendError( HttpServletResponse.SC_NO_CONTENT, "이미지 정보가 없습니다.(2)" );
						} else {
							File oFile = new File( sFilePath );
							if( ! oFile.exists() || ! oFile.isFile() ) {
								pResponse.sendError( HttpServletResponse.SC_NO_CONTENT, "이미지 정보가 없습니다.(3)" );
							} else {
								FileInputStream oFileInputStream = null;
								ByteArrayOutputStream oByteArrayOutputStream = null;
								ServletOutputStream oServletOutputStream = null;
								try {
									oByteArrayOutputStream = new ByteArrayOutputStream();
									oFileInputStream = new FileInputStream( sFilePath );
	
									byte[] bTemp = new byte[1024];
									int nSize = 0;
									while( true ) {
										nSize = oFileInputStream.read( bTemp );
										if( nSize == -1 )  {
											break;
										} else {
											oByteArrayOutputStream.write( bTemp, 0, nSize );
										}
									}

									pResponse.setHeader( "Content-Type", "image/jpeg" );

									oServletOutputStream = pResponse.getOutputStream();
									oServletOutputStream.write( oByteArrayOutputStream.toByteArray() );
									oServletOutputStream.flush();
								} catch( FileNotFoundException fnfe ) {
									fnfe.printStackTrace();
									pResponse.sendError( HttpServletResponse.SC_NO_CONTENT, "이미지 정보가 없습니다.(4)" );
								} catch( IOException ioe ) {
									ioe.printStackTrace();
									pResponse.sendError( HttpServletResponse.SC_NO_CONTENT, "이미지를 불러올 수 없습니다." );
								} finally {
									if( oFileInputStream != null ) {
										oFileInputStream.close();
										oFileInputStream = null;
									}

									if( oServletOutputStream != null ) {
										oServletOutputStream.close();
										oServletOutputStream = null;
									}
	
									if( oByteArrayOutputStream != null ) {
										oByteArrayOutputStream.close();
										oByteArrayOutputStream = null;
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	/**
	 * common code list
	 * @param param  요청 파라메터
	 * @return 결과 정보
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/qc/comm/getCommonCodeList", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getCommonCodeList( @RequestParam Map<String,Object> param ) throws Exception {
		param = StringUtil.cleanWeekness( param );

		Map<String,Object> map = new HashMap<String, Object>();
		List<Map> list = codeService.getCommonCodeList(param);
		map.put("resultList", list);
		return map;
	}
	
	
	/**
	 * category top code list
	 * @param param  요청 파라메터
	 * @return 결과 정보
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/qc/comm/getJobGroupTopList", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getJobGroupTopList( @RequestParam Map<String,Object> param ) throws Exception {
		param = StringUtil.cleanWeekness( param );

		Map<String,Object> map = new HashMap<String, Object>();
		List<Map> list = codeService.getJobGroupTopList(param);
		map.put("resultList", list);
		return map;
	}
	
	/**
	 * category middle code list
	 * @param param  요청 파라메터
	 * @return 결과 정보
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/qc/comm/getJobGroupMiddleList", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getJobGroupMiddleList( @RequestParam Map<String,Object> param ) throws Exception {
		param = StringUtil.cleanWeekness( param );

		Map<String,Object> map = new HashMap<String, Object>();
		List<Map> list = codeService.getJobGroupMiddleList(param);
		map.put("resultList", list);
		return map;
	}
	
	/**
	 * category bottom code list
	 * @param param  요청 파라메터
	 * @return 결과 정보
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/qc/comm/getJobGroupBottomList", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getJobGroupBottomList( @RequestParam Map<String,Object> param ) throws Exception {
		param = StringUtil.cleanWeekness( param );

		Map<String,Object> map = new HashMap<String, Object>();
		List<Map> list = codeService.getJobGroupBottomList(param);
		map.put("resultList", list);
		return map;
	}
	
	/**
	 * 사용자 권한별 목록 가져오기.
	 * @param param  요청 파라메터
	 * @param request  HttpServletRequest 객체
	 * @return 결과 정보
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/qc/comm/getMemberSelectList", method = RequestMethod.POST)
	public @ResponseBody Map<String,Object> getMemberSelectList( @RequestParam Map<String,Object> param, HttpServletRequest request ) throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		boolean result = true;
		String sessionYn = "Y";
		SessionUserVO sesUserVO = CodeUserDetailsUtil.getUser(request);
		if(sesUserVO != null){
			param = StringUtil.cleanWeekness( param );

			List<Map> list = codeService.getMemberSelectList(param);
			map.put("resultList", list);
		}else{
			/*세션 없으면 요렇게 처리.*/
			result=false;
			sessionYn = "N";
		}
		map.put("sessionYn", sessionYn); //세센여부
		map.put("result", result);
		
		return map;
	}

	/**
	 * 엑셀 다운로드
	 * @param request HttpServletRequest 객체
	 * @return 테이블 엑셀 EXPORT
	 */
	@RequestMapping(value = "/qc/comm/excelDownload", method = RequestMethod.POST)
	public String excelDownload( HttpServletRequest pRequest,@RequestParam Map<String,Object> param) throws Exception {

		String excelSource = (String) param.get("excelSource");
		pRequest.setAttribute("excelSource", excelSource);
		return "/qc/common/ExcelDownLoad";
	}
}