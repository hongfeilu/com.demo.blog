package org.sang.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sang.model.ArticleWithBLOBs;
import org.sang.model.Category;
import org.sang.model.Comments;
import org.sang.model.User;
import org.sang.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArticleCRUDController {

	@Autowired
	private IArticleService article;

	// 查询所有，页面显示所有博文
	@RequestMapping("/")
	public String getAllArticle(Model model) {
		List<ArticleWithBLOBs> list = article.getAllArticle();
		model.addAttribute("articles", list);
		return "/views/index";
	}

	// 点开阅读全文
	@RequestMapping("/detail/{id}/firstPage")
	public String getDetailById(Model model, @PathVariable Integer id) {
		ArticleWithBLOBs ar = article.getDetailById(id);
		List<Comments> list = article.getCommentByArticleId(id);
		model.addAttribute("articles", ar);
		model.addAttribute("comments", list);
		return "/views/detail";
	}

	// 点开专栏
	@RequestMapping("/column/{displayName}")
	public String showdisplayNamePage(Model model, @PathVariable String displayName) {
		List<ArticleWithBLOBs> ar = article.getCategoryById(displayName);
		model.addAttribute("articles", ar);
		return "/views/index";
	}

	// 打开写文章页面
	@RequestMapping("/sang/writePage")
	public String showWrite(Model model) {
		List<Category> categories = article.getCategories();
		// categories.remove(0);
		model.addAttribute("categories", categories);
		return "/admin/write";
	}

	// 保存写文章页面
		@RequestMapping("/sang/write")
		public String saveWrite(ArticleWithBLOBs articlePojo,User user,HttpServletRequest request,HttpServletResponse response) {
			User user1 = (User) request.getSession().getAttribute("user");
			String userName = user1.getUsername();
			Boolean b = article.saveWrite(articlePojo,userName);
			if (b.equals(true)) {
				return "redirect:http://localhost:8081/";
			}
			return "false";
		}
}
