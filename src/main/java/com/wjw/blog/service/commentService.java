package com.wjw.blog.service;

import java.util.List;

import com.wjw.blog.entity.comment;

public interface commentService {
	
	//插入一篇评论
	int insert(comment com); 
	
	//获得相应文章下所有评论
	List<comment> getAllcomments(int id);
	
	//获取特定页码的评论文章信息
	List<comment> getPageComments(int id,int pageNum,int PageSize);
	
	//获取特定所有评论数量
	int getCommentsNumber(int id);
	
	//获取所有评论信息
	List<comment> getAllcomments(String pageNum,String pageSize);
	
	//获取所有评论数量
	int AllCommentNumber();
	
	//根据ID删除评论
	int deleteById(int id);
	
	//根据ID删除多个评论
	int deleteComments(String [] idStrings);
	
	//根据文章ID找到所有的评论内容
    String [] getArticleComments(int id);
}
