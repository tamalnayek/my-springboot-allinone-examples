package com.sample.file.dwnld.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLConnection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/download")
public class FileDownloadController {
	
	// Placed all files inside this path and then copy or dwnld through this app.
	private static final String EXTERNAL_FILE_PATH = "C:/My WorkSpace/File-Download-Example/";
	
	@RequestMapping("/file/{fileName:.+}")
	public void downloadResource(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("fileName") String fileName) throws IOException {
		
		System.out.println ("https://www.javainuse.com/spring/boot-file-download");
		
		System.out.println(" ** req.getRemoteAddr() >> " + req.getRemoteAddr());
		System.out.println(" ** req.getRemoteHost() >> " + req.getRemoteHost());
		System.out.println(" ** req.getRemoteUser() >> " + req.getRemoteUser());
			
		File file = new File(EXTERNAL_FILE_PATH + fileName);
		if (file.exists()) {
			String metaType = URLConnection.guessContentTypeFromName(file.getName());
			
			if(metaType == null) {
				metaType = "application/octet-stream";
			}
			
			res.setContentType(metaType);
			/**
			 * In a regular HTTP response, the Content-Disposition response header is a
			 * header indicating if the content is expected to be displayed "inline" in the
			 * browser, that is, as a Web page or as part of a Web page, or as an
			 * "attachment", that is downloaded and saved locally.
			 * 
			 */

			/**
			 * Here we have mentioned it to show inline
			 */
			res.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
			 //Here we have mentioned it to show as attachment
			//res.setHeader("Content-Disposition", String.format("attachment; filename=\"" + file.getName() + "\""));
			
			res.setContentLength((int) file.length());
			InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
			FileCopyUtils.copy(inputStream, res.getOutputStream());
			
		}
		
	}

}
