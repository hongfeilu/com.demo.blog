package org.sang.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.sang.model.Article;
import org.sang.model.ArticleExample;
import org.sang.model.ArticleWithBLOBs;

public interface ArticleMapper {

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	long countByExample(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	int deleteByExample(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	int deleteByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	int insert(ArticleWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	int insertSelective(ArticleWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	List<ArticleWithBLOBs> selectByExampleWithBLOBs(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	List<Article> selectByExample(ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	ArticleWithBLOBs selectByPrimaryKey(Integer id);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	int updateByExampleSelective(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	int updateByExampleWithBLOBs(@Param("record") ArticleWithBLOBs record, @Param("example") ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	int updateByExample(@Param("record") Article record, @Param("example") ArticleExample example);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	int updateByPrimaryKeySelective(ArticleWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

	/**
	 * This method was generated by MyBatis Generator. This method corresponds to the database table article
	 * @mbg.generated  Tue Nov 07 16:00:47 CST 2017
	 */
	int updateByPrimaryKey(Article record);
}