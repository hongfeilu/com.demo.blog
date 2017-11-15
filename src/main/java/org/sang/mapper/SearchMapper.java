package org.sang.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.sang.model.ArticleWithBLOBs;

public interface SearchMapper {
	long getArticlesByKeyWrod(String keyword);
	
	public List<ArticleWithBLOBs> selectProductsByPage(@Param(value="startPos") Integer startPos,@Param(value="pageSize") Integer pageSize,@Param(value="keyword") String keyword);
}
