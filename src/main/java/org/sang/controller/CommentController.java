package org.sang.controller;

import org.sang.model.Comments;
import org.sang.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/sang/comment",method = RequestMethod.POST)
	private String setComment(Comments comment){
		commentService.setComment(comment);
		String lu = "/detail/" + comment.getArticleId() + "/firstPage";
		return "redirect:"+lu;
	}
}
