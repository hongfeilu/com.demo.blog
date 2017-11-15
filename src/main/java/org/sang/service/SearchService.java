package org.sang.service;


import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface SearchService {
	public void getArticlesByKeyWrod(HttpServletRequest request,Model model,String keyword);
}
