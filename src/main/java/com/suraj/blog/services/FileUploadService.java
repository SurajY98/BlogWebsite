package com.suraj.blog.services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author suraj.yadav
 * @created on Nov 5, 2023
 */

public interface FileUploadService {

	String uploadFile(String path, MultipartFile file) throws IOException;
	InputStream getResource(String path,String fileName) throws IOException;
}
