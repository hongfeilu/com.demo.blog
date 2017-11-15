package org.sang.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.sang.mapper.ArticleMapper;
import org.sang.mapper.CategoryMapper;
import org.sang.mapper.CommentsMapper;
import org.sang.mapper.UserMapper;
import org.sang.model.ArticleExample;
import org.sang.model.ArticleWithBLOBs;
import org.sang.model.Category;
import org.sang.model.CategoryExample;
import org.sang.model.Comments;
import org.sang.model.CommentsExample;
import org.sang.model.User;
import org.sang.model.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImp implements IArticleService {

	@Autowired
	private ArticleMapper articleMapper;

	@Autowired
	private CategoryMapper categoryMapper;
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private CommentsMapper commentMapper;

	@Override
	public List<ArticleWithBLOBs> getAllArticle() {
		ArticleExample example = new ArticleExample();
		List<ArticleWithBLOBs> list = articleMapper.selectByExampleWithBLOBs(example);
		return list;
	}

	@Override
	public ArticleWithBLOBs getDetailById(Integer id) {
		ArticleWithBLOBs ar = articleMapper.selectByPrimaryKey(id);
		return ar;
	}

	@Override
	public List<ArticleWithBLOBs> getCategoryById(String displayName) {
		int categoryId = getIdByName(displayName);
		ArticleExample example = new ArticleExample();
		List<ArticleWithBLOBs> list = articleMapper.selectByExampleWithBLOBs(example);
		List<Integer> integerList = new ArrayList<>();
		for (ArticleWithBLOBs a : list) {
			if (a.getCategoryid() instanceof Integer) {
				if (a.getCategoryid() == categoryId) {
					int id = a.getId();
					integerList.add(id);
				}
			} else {
				continue;
			}
		}
		List<ArticleWithBLOBs> arList = new ArrayList<>();
		Iterator<Integer> it = integerList.iterator();
		while (it.hasNext()) {
			Integer id = it.next();
			ArticleWithBLOBs ar = articleMapper.selectByPrimaryKey(id);
			arList.add(ar);
		}
		return arList;
	}

	public int getIdByName(String displayName) {
		CategoryExample example = new CategoryExample();
		List<Category> list = categoryMapper.selectByExample(example);
		for (Category category : list) {
			if (category.getName().equals(displayName)) {
				int id = category.getId();
				return id;
			}
		}
		return -1;
	}

	@Override
	public List<Category> getCategories() {
		CategoryExample example = new CategoryExample();
		List<Category> list = categoryMapper.selectByExample(example);
		return list;
	}

	@Override
	public Boolean saveWrite(ArticleWithBLOBs articlePojo,String userName) {
		UserExample example = new UserExample();
		List<User> list = userMapper.selectByExample(example);
		for(User u : list){
			if(u.getUsername().equals(userName)){
				articlePojo.setUserId(u.getId());
			}
		}
		articlePojo.setDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		int i = articleMapper.insert(articlePojo);
		if (i == 1) {
			return true;
		}
		return false;
	}
	
	@Override
	public List<Comments> getCommentByArticleId(Integer id) {
		CommentsExample example = new CommentsExample();
		List<Comments> list = commentMapper.selectByExampleWithBLOBs(example);
		List<Comments> list2 = new ArrayList<>();
		for(Comments a : list){
			if(a.getArticleId().equals(id)){
				list2.add(a);
			}
		}
		return list2;
	}
}
