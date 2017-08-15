package kr.co.code.common.util;

import java.io.File;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import kr.co.code.common.exception.CodeException;


public class UploadUtil {

	public static final String FILE_UPLOAD_ROOT_PATH = "";


	public static final String SEPARATOR = "/";
	public static final String Excel = "Excel";

	public static final String KEY_FILE_SIZE				= "size";
	public static final String KEY_ORG_FILE_NAME	= "orgfilename";
	public static final String KEY_FILE_PATH			= "path";
	public static final String KEY_FILE_NAME			= "filename";


	// 허용할 파일 확장자
	public static final String[] FILE_EXT_LIST = { "xls", "xlsx","csv","ppt","pptx","doc","docx","hwp","jpg","jpeg","bmp","gif","zip","mp4","png","txt","pdf","apk","ipa"};

	// 관리자 파일 업로드 사이즈 : 10MB
	public static final long MAX_FILE_SIZE = 1024 * 1024 * 10;

	// 프로모션 소스 배포 업로드 사이즈 : 10MB
	public static final long PROMOTION_MAX_FILE_SIZE = 1024 * 1024 * 10;

	// 파일 마임타입 제한
	public static final String[] FILE_TYPE_LIST = {"application/octet-stream", "application/x-msdownload", "application/x-sh"};
	public static final String[] PROMOTION_FILE_TYPE_LIST = {"application/octet-stream", "application/x-msdownload", "application/x-sh"};


	/**
	 * 년도 (ex : /2012/)
	 * @return
	 */
	public static String getBizHashDir(){

		String hashSrc = DateUtil.format(DateUtil.getCurrentDateTime(), "yyyy");
		StringBuffer rt = new StringBuffer();
		rt.append(SEPARATOR);
		rt.append(hashSrc);
		rt.append(SEPARATOR);

		return rt.toString();
	}


	/**
	 * 파일 업로드
	 * @param mf
	 * @return
	 * @throws IOException
	 * @throws SlttException
	 */
	public static HashMap<String, Object> upload(MultipartFile mf, String filePath ) throws IOException, CodeException{
		HashMap<String, Object> hm = new HashMap<String, Object>();

		// 파일을 업로드할 경로를 구한다.
		String DirPath =  filePath;

		// 파일을 업로드할 경로를 만든다.
		createDirectory(DirPath);

		// 파일 확장자
		String ext  = (mf.getOriginalFilename()).substring((mf.getOriginalFilename()).lastIndexOf(".")+1);
		// 파일 Type
		String type = mf.getContentType();

		
		if(!fileCheck(ext, type)) {
			throw new CodeException(CodeException.NOT_ALLOW_FILE);	
		}
		
		// 파일명을 생성한다.
		// 2012.08.01 파일명 생성 : YYMMDDhhmmss + 랜덤숫자(2자리)
		String uuid = UUID.randomUUID().toString(); //추가
		String fileName = DateUtil.format(DateUtil.getCurrentDateTime(), "yyMMddkkmmss") + "_" + uuid  +"."+ext;

		// 올릴 파일을 설정한다.
		File destDir = new File(DirPath + SEPARATOR + fileName);

		// 파일 업로드를 수행한다.
		mf.transferTo(destDir);

		// 파일패스
		hm.put("path", DirPath);
		// 파일명
		hm.put("filename", fileName);
		// 원본파일명
		hm.put("orgfilename", mf.getOriginalFilename());
		// 파일크기
		hm.put("size", mf.getSize());

		return hm;
	}




	public static boolean createDirectory(String path) {
		File dir = new File(path);
		return createDirectory(dir);
	}


	public static boolean createDirectory(File dir) {
		if ( dir.exists() && dir.isDirectory() ) return true;
		if ( dir.exists() && !dir.isDirectory() ) return false;
		return dir.mkdirs();
	}


	public static void deleteDirectory(File directory) throws IOException {
		FileUtils.deleteDirectory(directory);
	}


	public static void deleteFile(File file) throws IOException {
		if ( file.isDirectory() ) {
			throw new IllegalArgumentException("Destination '"+file.getAbsolutePath()+"' is a directory");
		}
		FileUtils.forceDelete(file);
	}


	public static void forceDelete(File file) throws IOException {
		FileUtils.forceDelete(file);
	}


	public static String hexa(String data) throws NoSuchAlgorithmException{
		String strENCData = "";
		MessageDigest md = MessageDigest.getInstance("MD5");
		byte[] bytData = data.getBytes();

		md.update(bytData);
		byte[] digest = md.digest();

		for (int i = 0; i < digest.length; i++) {
			strENCData = strENCData + Integer.toHexString(digest[i] & 0xFF).toUpperCase();
		}

		String hexa1 = strENCData.substring(0, 2);
		String hexa2 = strENCData.substring(2, 4);

		return SEPARATOR+hexa1+SEPARATOR+hexa2;
	}


	public static boolean fileCheck(String ext, String type) {
		String[] checkExtList = null;								// 파일 확장자 체크 리스트
		String[] checkTypeList = null;								// 파일 타입 체크 리스트
		//
		checkExtList = FILE_EXT_LIST;
		checkTypeList = FILE_TYPE_LIST;
		// 파일 확장자 체크
		boolean notAllowExt = false;
		for(String checkExt : checkExtList) {
			if(ext.toLowerCase().equals(checkExt)) {
				notAllowExt = true;
			}
		}

		if(!notAllowExt) {
			return false;
		}

		/*
		 // 파일 타입 체크
		for(String checkType : checkTypeList) {
			if(type.equalsIgnoreCase(checkType)) {
				return false;
			}
		}
		*/

		return true;
	}


	/**
	 * 파일명 replace
	 * @param fileName
	 * @return
	 */
	public static String replaceFileName(String fileName) {

		if(StringUtil.isEmpty(fileName)) {
			return "";
		}

		String source = fileName.substring(0, fileName.lastIndexOf("."));		// 확장자 앞
		String ext = fileName.substring(fileName.lastIndexOf(".")+1);			// 확장자
		fileName = source.replaceAll("\\/", "");
		fileName = fileName.replaceAll("\\.", "");
		fileName = fileName + "." + ext;
		return fileName;
	 }
}
