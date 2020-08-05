package com.sample.file.dwnld.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.springframework.stereotype.Service;

@Service
public class ZipService {

	public String doZipFile(String sourceFile, String zipFile) {

		try {
			FileOutputStream fos = new FileOutputStream(zipFile);
			ZipOutputStream zipOut = new ZipOutputStream(fos);

			File fileToZip = new File(sourceFile);
			FileInputStream fis = new FileInputStream(fileToZip);
			// Create Zip file in directory.
			ZipEntry zipEntry = new ZipEntry(fileToZip.getName());
			// Push file inside Zip.
			zipOut.putNextEntry(zipEntry);
			// Copy contain inside File.
			byte[] bytes = new byte[1024];
			int length;
			while ((length = fis.read(bytes)) >= 0) {
				zipOut.write(bytes, 0, length);
			}
			zipOut.close();
			fis.close();

			// Remove file from path.//FileUtils.forceDelete(fileToZip);
			//fileToZip.delete();

			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "Error to Zip the File";
		}
		return "Directory files zip successfully";
	}

	public String doZipDirectory(String sourceFolder, String zipDirFile) {
		// String sourceFile = "zipTest";
		try {
			FileOutputStream fos = new FileOutputStream(zipDirFile);
			ZipOutputStream zipOut = new ZipOutputStream(fos);
			File fileToZip = new File(sourceFolder);
			zipFile(fileToZip, fileToZip.getName(), zipOut);
			zipOut.close();
			fos.close();
			// Remove all directory (including files)
			//cleanDirFiles(fileToZip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Error to Zip the Directory";	
		}

		return "Directory files zip successfully";
	}

	private void zipFile(File fileToZip, String fileName, ZipOutputStream zipOut) throws IOException {
		if (fileToZip.isHidden()) {
			return;
		}
		if (fileToZip.isDirectory()) {
			if (fileName.endsWith("/")) {
				zipOut.putNextEntry(new ZipEntry(fileName));
				zipOut.closeEntry();
			} else {
				zipOut.putNextEntry(new ZipEntry(fileName + "/"));
				zipOut.closeEntry();
			}
			File[] children = fileToZip.listFiles();
			for (File childFile : children) {
				zipFile(childFile, fileName + "/" + childFile.getName(), zipOut);
			}
			return;
		}
		FileInputStream fis = new FileInputStream(fileToZip);
		ZipEntry zipEntry = new ZipEntry(fileName);
		zipOut.putNextEntry(zipEntry);
		byte[] bytes = new byte[1024];
		int length;
		while ((length = fis.read(bytes)) >= 0) {
			zipOut.write(bytes, 0, length);
		}
		fis.close();
	}

	private void cleanDirFiles(File dir) {
		for (File file : dir.listFiles()) {
			if (file.isDirectory())
				cleanDirFiles(file);
			file.delete();
		}
	}
}
