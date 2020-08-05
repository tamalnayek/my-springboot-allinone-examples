package com.sample.file.dwnld.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sample.file.dwnld.service.UnZipService;
import com.sample.file.dwnld.service.ZipService;

@RestController
public class FileZipUnZipController {

	String sourceFileLocation = "C:/My WorkSpace/ZipExample/";
	String zipSingleFileLocation = "C:/My WorkSpace/ZipExample/SingleFileZip.zip";
	
	String zipDirLocation = "C:/My WorkSpace/ZipExample/DirZip.zip";
	String zipSourceDir = "C:/My WorkSpace/";
	
	String targetUnZipFileLocation = "C:/My WorkSpace/ZipExample";
	
	@Autowired
	private ZipService zipService;
	
	@Autowired
	private UnZipService unZipService;

	@GetMapping(value = "/zip/file/{fileName}")
	public String doZipSingleFile(HttpServletRequest req, HttpServletResponse res,
			@PathVariable("fileName") String fileName) {

		String filePath = sourceFileLocation.concat(fileName);
		return zipService.doZipFile(filePath, zipSingleFileLocation);		
	}

	@GetMapping(value = "/zip/folder/{dirName}")
	public String doZipFolder(HttpServletRequest req, HttpServletResponse res, 
			@PathVariable("dirName") String dirName){
		
		String sourceDirPath = zipSourceDir.concat(dirName);
		return zipService.doZipDirectory(sourceDirPath, zipDirLocation);
	}
	
	@GetMapping(value = "/unzip/file/{zipFileName}")
	public String doUnZipFolder(HttpServletRequest req, HttpServletResponse res, 
			@PathVariable("zipFileName") String zipFileName){
		
		String sourceDirPath = sourceFileLocation.concat(zipFileName);
		return unZipService.doUnzipFile(sourceDirPath, targetUnZipFileLocation);
	}

	

}
