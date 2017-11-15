package org.sang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.sang.model.ArticleWithBLOBs;
import org.sang.model.Category;
import org.sang.model.User;
import org.sang.service.IUserArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserArticleController {
	
	@Autowired
	private IUserArticleService userArticleService;
	
	
	
	@RequestMapping("/sang")
    public String sindex(HttpServletRequest request,Model model) {
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("userArticles", userArticleService.getUserArticles(user.getId()));
        return "admin/index";
    }
	
	@RequestMapping( "/sang/update/{id}")
    public String update(@PathVariable("id") Integer id, Model model) {
		ArticleWithBLOBs article = userArticleService.getArticle(id);
		model.addAttribute("article", article);
		List<Category> Categorys = userArticleService.getAllCategorys();
		Categorys.remove(0);
		model.addAttribute("categories", Categorys);
		String catrgoryNameByArticle = userArticleService.getCategory(article.getCategoryid()).getDisplayName();
		model.addAttribute("catrgoryNameByArticle", catrgoryNameByArticle);
        return "admin/editArticle";
    }
	
	@RequestMapping(value = "/sang/updateWrite", method = RequestMethod.POST)
	public String updateWrite(ArticleWithBLOBs articleWithBLOBs){
		userArticleService.updateArticle(articleWithBLOBs);
		return "redirect:/sang";
	}
	
	@RequestMapping("/sang/delete/{id}")
	public String delete(@PathVariable Integer id){
		userArticleService.deleteArticle(id);
		return "redirect:/sang";
	}
	
	
	
	
	

}
