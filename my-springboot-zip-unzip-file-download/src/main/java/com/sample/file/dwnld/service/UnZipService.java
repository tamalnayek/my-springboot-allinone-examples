package com.sample.file.dwnld.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.stereotype.Service;

@Service
public class UnZipService {

	public String doUnzipFile(String srcZipPath, String targetPath) {
		// String zipPath = "C:\\My WorkSpace\\ZipExample\\multiCompressed.zip";
		try {
			File destDir = new File(targetPath);
			byte[] buffer = new byte[1024];
			ZipInputStream zis = new ZipInputStream(new FileInputStream(srcZipPath));
			ZipEntry zipEntry = zis.getNextEntry();
			while (zipEntry != null) {
				File newFile = newFile(destDir, zipEntry);
				FileOutputStream fos = new FileOutputStream(newFile);
				int len;
				while ((len = zis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				zipEntry = zis.getNextEntry();
			}
			zis.closeEntry();
			zis.close();
		} catch (IOException e) {
			e.printStackTrace();
			return "Error to UnZip the File";
		}

		return "File unzip successfully";
	}

	private File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
		String fileName = zipEntry.getName();

		File destFile = new File(destinationDir, fileName);

		if (fileName.equalsIgnoreCase("MainDocument")) {
			System.out.println("File name exist");
		}
		String destDirPath = destinationDir.getCanonicalPath();
		String destFilePath = destFile.getCanonicalPath();

		System.out.println("destDirPath=" + destDirPath);
		System.out.println("destFilePath=" + destFilePath);

		if (!destFilePath.startsWith(destDirPath + File.separator)) {
			throw new IOException("Entry is outside of the target dir: " + zipEntry.getName());
		}

		return destFile;
	}
}
