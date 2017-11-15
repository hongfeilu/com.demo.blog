package org.sang.service;

import java.util.List;

import org.sang.model.ArticleWithBLOBs;
import org.sang.model.Category;

public interface IUserArticleService {

	public List<ArticleWithBLOBs> getUserArticles(Integer userId);
	
	public ArticleWithBLOBs getArticle(Integer id);
	
	public void updateArticle(ArticleWithBLOBs articleWithBLOBs);
	
	public List<Category> getAllCategorys();
	
	public Category getCategory(Integer id);
	
	public void deleteArticle(Integer id);

}
