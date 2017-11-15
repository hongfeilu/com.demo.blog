package org.sang.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface ImageUploadService {
	public Map uploadFile(MultipartFile uploadFile) throws Exception;

	public Boolean saveImage(Map imageResult,int categoryId,String content,String title,String userName);
}
