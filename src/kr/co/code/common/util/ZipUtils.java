package kr.co.code.common.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;


//import java.util.zip.ZipEntry;
//import java.util.zip.ZipInputStream;
//import java.util.zip.ZipOutputStream;
import net.sf.jazzlib.ZipEntry;
import net.sf.jazzlib.ZipInputStream;
import net.sf.jazzlib.ZipOutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class ZipUtils {

	private static final int COMPRESSION_LEVEL = 8;

	private static final int BUFFER_SIZE = 1024 * 2;

	/**
	 * 지정된 폴더를 Zip 파일로 압축한다. 
	 * @param sourcePath  - 압축 대상 디렉토리
	 * @param output - 저장 zip 파일 이름
	 * @throws Exception
	 */
	public static void zip(String sourcePath, String output) throws Exception {
		// 압축 대상(sourcePath)이 디렉토리나 파일이 아니면 리턴한다.
		File sourceFile = new File(sourcePath);
		if (!sourceFile.isFile() && !sourceFile.isDirectory()) {
			throw new Exception("압축 대상의 파일을 찾을 수가 없습니다.");
		}

		// output 의 확장자가 zip이 아니면 리턴한다.
		if (!(StringUtils.substringAfterLast(output, ".")).equalsIgnoreCase("zip")) {
			throw new Exception("압축 후 저장 파일명의 확장자를 확인하세요");
		}

		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		ZipOutputStream zos = null;

		try {
			// FileOutputStream
			fos = new FileOutputStream(output);
			// BufferedStream
			bos = new BufferedOutputStream(fos);
			// ZipOutputStream
			zos = new ZipOutputStream(bos);
			// 압축 레벨 - 최대 압축률은 9, 디폴트 8
			zos.setLevel(COMPRESSION_LEVEL);
			// Zip 파일 생성
			zipEntry(sourceFile, sourcePath, zos);
			// ZipOutputStream finish
			zos.finish();
		} finally {
			if (zos != null) {
				zos.close();
			}
			if (bos != null) {
				bos.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}

	/**
	 * 압축
	 * @param sourceFile
	 * @param sourcePath
	 * @param zos
	 * @throws Exception
	 */
	private static void zipEntry(File sourceFile, String sourcePath, ZipOutputStream zos) throws Exception {
		// sourceFile 이 디렉토리인 경우 하위 파일 리스트 가져와 재귀호출
		if (sourceFile.isDirectory()) {
			// .metadata 디렉토리 return
			if (sourceFile.getName().equalsIgnoreCase(".metadata")) {
				return;
			}
			// sourceFile 의 하위 파일 리스트
			File[] fileArray = sourceFile.listFiles();
			for (int i = 0; i < fileArray.length; i++) {
				// 재귀 호출
				zipEntry(fileArray[i], sourcePath, zos);
			}
		// sourcehFile 이 디렉토리가 아닌 경우
		} else {
			BufferedInputStream bis = null;
			try {
				String sFilePath = sourceFile.getPath();
				String zipEntryName = sFilePath.substring(sourcePath.length() + 1, sFilePath.length());

				bis = new BufferedInputStream(new FileInputStream(sourceFile));
				ZipEntry zentry = new ZipEntry(zipEntryName);
				zentry.setTime(sourceFile.lastModified());
				zos.putNextEntry(zentry);
				
				byte[] buffer = new byte[BUFFER_SIZE];
				int cnt = 0;
				while ((cnt = bis.read(buffer, 0, BUFFER_SIZE)) != -1) {
					zos.write(buffer, 0, cnt);
				}
				zos.closeEntry();
			} finally {
				if (bis != null) {
					bis.close();
				}
			}
		}
	}

	/**
	 * Zip 파일의 압축을 푼다.
	 * @param zipFile - 압축 풀 Zip 파일
	 * @param targetDir  - 압축 푼 파일이 들어간 디렉토리
	 * @param fileNameToLowerCase - 파일명을 소문자로 바꿀지 여부
	 * @throws Exception
	 */
	public static void unzip(File zipFile, File targetDir, boolean fileNameToLowerCase) throws Exception {
		FileInputStream fis = null;
		ZipInputStream zis = null;
		ZipEntry zentry = null;

		try {
			// FileInputStream
			fis = new FileInputStream(zipFile);
			// ZipInputStream
			zis = new ZipInputStream(fis);

			while ((zentry = zis.getNextEntry()) != null) {
				String fileNameToUnzip = zentry.getName();
				// fileName toLowerCase
				if (fileNameToLowerCase) {
					fileNameToUnzip = fileNameToUnzip.toLowerCase();
				}

				File targetFile = new File(targetDir, fileNameToUnzip);
				
				// Directory 인 경우
				if (zentry.isDirectory()) {
					// 디렉토리 생성
//					FileUtils.makeDir(targetFile.getAbsolutePath());
					FileUtils.forceMkdir(targetFile);
				// File 인 경우
				} else {
					// parent Directory 생성
//					FileUtils.makeDir(targetFile.getParent());
					FileUtils.forceMkdir(targetFile.getParentFile());
					unzipEntry(zis, targetFile);
				}
			}
		} finally {
			if (zis != null) {
				zis.close();
			}
			if (fis != null) {
				fis.close();
			}
		}
	}

	/**
	 * Zip 파일의 한 개 엔트리의 압축을 푼다.
	 * @param zis - Zip Input Stream
	 * @param filePath - 압축 풀린 파일의 경로
	 * @return
	 * @throws Exception
	 */
	protected static File unzipEntry(ZipInputStream zis, File targetFile) throws Exception {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(targetFile);

			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = zis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			}
		} finally {
			if (fos != null) {
				fos.close();
			}
		}
		return targetFile;
	}
	
	// 파일을 복사하는 메소드
	public static void fileCopy(String inFileName, String outFileName) {
		try {
			FileInputStream inputStream = new FileInputStream(inFileName);
			FileOutputStream outputStream = new FileOutputStream(outFileName);
			
			FileChannel fcin = inputStream.getChannel();
			FileChannel fcout = outputStream.getChannel();
			
			long size = fcin.size();
			fcin.transferTo(0, size, fcout);
			
			fcout.close();
			fcin.close();
			
			outputStream.close();
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean fileDelete(String delTarget) {
		File delDir = new File(delTarget);
		// 폴더가 존재할 경우
		if (delDir.isDirectory()) {
			// 하위 폴더 및 파일 저장
			File[] allFiles = delDir.listFiles();
			// 하위 폴더가 존재할 경우
			for (File delAllDir : allFiles) {
				// 하위폴더에서 처음으로 돌아가 실행
				fileDelete(delAllDir.getAbsolutePath());
			}
		}
		// 파일 삭제
		return delDir.delete();
	}
}