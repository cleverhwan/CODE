package kr.co.code.biz.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.code.common.logging.CodeLogger;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;


public class DownloadUtil extends AbstractView {
	
	private CodeLogger log = CodeLogger.getLogger();
	
	public DownloadUtil() {
		setContentType("application/download; charset=utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {	
		File file = (File) model.get("downloadFile");
		String sOriginalFileName = (String) model.get("originalFileName");
		
		response.setContentType(getContentType());
		response.setContentLength((int) file.length());
		
		String fileName = null;
		if( sOriginalFileName == null || sOriginalFileName.trim().isEmpty() ) {
	        sOriginalFileName = file.getName();
		}

		/*
		String userAgent = request.getHeader("User-Agent");
		boolean ie = userAgent.indexOf("MSIE") > -1;
		
        if(ie)
        {
            fileName = URLEncoder.encode(sOriginalFileName, "utf-8");
            log.debug("IE!");
        } else
        {
            fileName = new String(sOriginalFileName.getBytes("utf-8"));
            log.debug("String (fileName.getBytes(utf-8)");
        }
        */

		sOriginalFileName = sOriginalFileName.replace(" ", "*");
        fileName = URLEncoder.encode(sOriginalFileName, "utf-8");
        fileName = fileName.replace("*", "%20");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();
		
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			FileCopyUtils.copy(fis, out);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
				}
			}
		}
		
		out.flush();
	}
}
