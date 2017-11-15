package org.sang.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.sang.mapper.ArticleMapper;
import org.sang.mapper.CategoryMapper;
import org.sang.model.ArticleExample;
import org.sang.model.ArticleExample.Criteria;
import org.sang.model.ArticleWithBLOBs;
import org.sang.model.Category;
import org.sang.model.CategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

@Service
public class UserArticleServiceImpl implements IUserArticleService {
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	
	@Autowired
	private ArticleMapper articleMapper;
	
	@Autowired
	private CategoryMapper categoryMapper;

	@Override
	public List<ArticleWithBLOBs> getUserArticles(Integer userId) {
		ArticleExample example = new ArticleExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<ArticleWithBLOBs> list = articleMapper.selectByExampleWithBLOBs(example);
		return list;
	}

	@Override
	public ArticleWithBLOBs getArticle(Integer id) {
		return articleMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Category> getAllCategorys() {
		CategoryExample example = new CategoryExample();
		List<Category> categorys = categoryMapper.selectByExample(example);
		return categorys;
	}

	@Override
	public Category getCategory(Integer id) {
		Category category = categoryMapper.selectByPrimaryKey(id);
		return category;
	}

	@Override
	public void updateArticle(ArticleWithBLOBs articleWithBLOBs) {
		articleWithBLOBs.setDate(sdf.format(new Date()));
		if (articleWithBLOBs.getContent().length() > 20) {
			articleWithBLOBs.setSummary(articleWithBLOBs.getContent().substring(0, 20));
	    } else {
	    	articleWithBLOBs.setSummary(articleWithBLOBs.getContent().substring(0, articleWithBLOBs.getContent().length()));
		}
		articleMapper.updateByPrimaryKeySelective(articleWithBLOBs);
	}

	@Override
	public void deleteArticle(Integer id) {
		articleMapper.deleteByPrimaryKey(id);
	}
	
	

}
