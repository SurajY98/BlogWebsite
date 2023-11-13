package com.suraj.blog.servicesImpl;

/**
 * @author suraj.yadav
 * @created on Nov 5, 2023
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.suraj.blog.services.FileUploadService;

@Service
public class FileUploadServiceImpl implements FileUploadService {

	@Override
	public String uploadFile(String path, MultipartFile file) throws IOException {
		/* We are getting the file name */
		String fileName = file.getOriginalFilename();

		/* We are generating the random file or updating the name of the file */
		String randomIo = UUID.randomUUID().toString();
		String randomFileName = randomIo.concat(fileName.substring(fileName.lastIndexOf(".")));

		/* Getting path of the file */
		String filePath = path + File.separator + randomFileName;

		/* Creating the folder if not created. */
		File file2 = new File(path);
		if (!file2.exists()) {
			file2.mkdir();
		}

		/* Copying the path of the file */
		Files.copy(file.getInputStream(), Paths.get(filePath));
		return fileName;
	}

	/* Method for serve image */
	@Override
	public InputStream getResource(String path, String fileName) throws FileNotFoundException {
		String fullPath = path+ File.separator + fileName;
		return new FileInputStream(fullPath);
	}

}
