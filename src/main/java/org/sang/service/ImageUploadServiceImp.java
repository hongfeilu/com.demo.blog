package org.sang.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.joda.time.DateTime;
import org.sang.mapper.ArticleMapper;
import org.sang.mapper.UserMapper;
import org.sang.model.ArticleWithBLOBs;
import org.sang.model.User;
import org.sang.model.UserExample;
import org.sang.utils.FtpUtil;
import org.sang.utils.IDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageUploadServiceImp implements ImageUploadService {

	@Value("${FILE_UPLOAD_PATH}")
	private String FILE_UPLOAD_PATH;
	@Value("${FTP_SERVER_IP}")
	private String FTP_SERVER_IP;
	@Value("${FTP_SERVER_PORT}")
	private Integer FTP_SERVER_PORT;
	@Value("${FTP_SERVER_USERNAME}")
	private String FTP_SERVER_USERNAME;
	@Value("${FTP_SERVER_PASSWORD}")
	private String FTP_SERVER_PASSWORD;
	@Value("${IMAGE_BASE_URL}")
	private String IMAGE_BASE_URL;

	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	//上传文件
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Map uploadFile(MultipartFile uploadFile) throws Exception {
		Map resultMap = new HashMap();
		try {
			// 判断文件是否为空
			if (uploadFile.isEmpty())
				return null;
			// 上传文件以日期为单位分开存放，可以提高图片的查询速度
			String filePath = new DateTime().toString("/yyyy/MM/dd");
			// 取原始文件名
			String originalFilename = uploadFile.getOriginalFilename();
			// 新文件名
			String newFileName = IDUtil.genImageName() + originalFilename.substring(originalFilename.lastIndexOf("."));
			// 转存文件，上传到ftp服务器
			Boolean isSuccessed = FtpUtil.uploadFile(FTP_SERVER_IP, FTP_SERVER_PORT, FTP_SERVER_USERNAME,
					FTP_SERVER_PASSWORD, FILE_UPLOAD_PATH, filePath, newFileName, uploadFile.getInputStream());
			String path = filePath + "/" + newFileName;
			if (isSuccessed) {
				resultMap.put("error", 0);
				resultMap.put("url", IMAGE_BASE_URL + path);
			} else {
				resultMap.put("error", 1);
				resultMap.put("message", "图片上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resultMap;
	}

	//保存图片路径到数据库
	@SuppressWarnings("rawtypes")
	@Override
	public Boolean saveImage(Map imageResult,int categoryId,String content,String title,String userName) {
		String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		ArticleWithBLOBs articleWithBLOBs = new ArticleWithBLOBs();
		articleWithBLOBs.setFile((String)imageResult.get("url"));
		articleWithBLOBs.setDate(time);
		articleWithBLOBs.setCategoryid(categoryId);
		articleWithBLOBs.setTitle(title);
		articleWithBLOBs.setContent(content);
		UserExample example = new UserExample();
		List<User> list = userMapper.selectByExample(example);
		for(User u : list){
			if(u.getUsername().equals(userName)){
				articleWithBLOBs.setUserId(u.getId());
			}
		}
		int i = articleMapper.insertSelective(articleWithBLOBs);
		if(i == 1){
			return true;
		}
		return false;
	}
}
