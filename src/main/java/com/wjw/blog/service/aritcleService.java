package com.wjw.blog.service;

import java.util.List;

import com.wjw.blog.entity.aritcle;

public interface aritcleService {
	
	//获取分页获取所有文章数据
	List<aritcle> getAllaritcles(String pageNum,String pageSize,String typeId);
	
	//批量删除文章
	int delAricles(String[] ids);
	
	//删除单个文章
	int delArticle(int id);
	
	//插入一篇文章
	int insert(aritcle aritcles);
	
	//根据ID返回一篇文章信息
	aritcle getArticle(int id);
	
	//返回所有文章的页数
	int getCount(int typeid);
	
	//获取所有文章的数量
	int getSize(int typeid);
	
	//添加评论时修改评论数量
	int updateComments(aritcle aritcles);
	
	//删除评论时修改评论数量
	int delCommentsNum(int id);
}
