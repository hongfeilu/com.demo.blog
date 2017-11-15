package org.sang.controller;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.sang.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SearchController {
	@Autowired
    private SearchService searchService;
	
	@RequestMapping("/search")
    public String index(HttpServletRequest request,@RequestParam("q")String queryString,Model model) {
		if (queryString != null) {
			try {
				queryString = new String(queryString.getBytes("iso8859-1"), "utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
       searchService.getArticlesByKeyWrod(request,model,queryString);
//        model.addAttribute("articles", articleWithBLOBs);
//        model.addAttribute("query",queryString);
//		model.addAttribute("totalPages",page +"");
//		model.addAttribute("page",page);
       //你说行就行
        return "views/search";
    }

}
