package org.sang.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sang.mapper.SearchMapper;
import org.sang.model.ArticleWithBLOBs;
import org.sang.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class SearchServiceImpl implements SearchService{
	
	@Autowired
	private SearchMapper searchMapper;
	
	@Override
	public void getArticlesByKeyWrod(HttpServletRequest request,Model model,String keyword) {
		 String pageNow = request.getParameter("pageNow");  
		  
		    Page page = null;  
		  
		    List<ArticleWithBLOBs> articles = new ArrayList<ArticleWithBLOBs>();  
		  
		    int totalCount = (int) searchMapper.getArticlesByKeyWrod(keyword);
		  
		    if (pageNow != null) {  
		        page = new Page(totalCount, Integer.parseInt(pageNow));  
		        articles = searchMapper.selectProductsByPage(page.getStartPos(), page.getPageSize(), keyword);  
		    } else {  
		        page = new Page(totalCount, 1);  
		        articles = searchMapper.selectProductsByPage(page.getStartPos(), page.getPageSize(), keyword);  
		    }  
		  
		    model.addAttribute("articles", articles);  
		    model.addAttribute("page", page);  
		    model.addAttribute("keyword", keyword);
	}

}
