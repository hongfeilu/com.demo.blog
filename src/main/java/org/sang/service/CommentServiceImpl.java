package org.sang.service;


import org.sang.mapper.CommentsMapper;
import org.sang.model.Comments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
	
	@Autowired
	private CommentsMapper commentsMapper;
	
	@Override
	public void setComment(Comments comment) {
		commentsMapper.insert(comment);
	}

}
