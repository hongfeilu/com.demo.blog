package org.sang.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sang.model.User;
import org.sang.service.ImageUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ImageController {

	@Autowired
	private ImageUploadService imageUploadService;

	//点击保存后上传文件和保存categoryId
	@SuppressWarnings("rawtypes")
	@RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
	public String save(MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws Exception {
		User user1 = (User) request.getSession().getAttribute("user");
		String userName = user1.getUsername();
		String categoryIdStr = request.getParameter("paramInput");
		String content = request.getParameter("content");
		String title = request.getParameter("title");
		int categoryId = Integer.parseInt(categoryIdStr);
		Map imageResult = imageUploadService.uploadFile(file);
		int status = (int) imageResult.get("error");
		if(imageResult != null && status == 0){
			//保存图片地址到数据库
			Boolean b = imageUploadService.saveImage(imageResult,categoryId,content,title,userName);
			if(b.equals(true)){
				return "redirect:http://localhost:8081/";
			}
		}
		return "false";
	}

}
