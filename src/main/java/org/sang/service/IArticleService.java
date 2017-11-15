package org.sang.service;

import java.util.List;

import org.sang.model.ArticleWithBLOBs;
import org.sang.model.Category;
import org.sang.model.Comments;

public interface IArticleService {
	public List<ArticleWithBLOBs> getAllArticle();

	ArticleWithBLOBs getDetailById(Integer id);

	List<ArticleWithBLOBs> getCategoryById(String displayName);

	public List<Category> getCategories();

	Boolean saveWrite(ArticleWithBLOBs articlePojo,String userName);

	List<Comments> getCommentByArticleId(Integer id);
}
