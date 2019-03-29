package com.wjw.blog.service.Imp;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import com.wjw.blog.dao.aritcleMapper;
import com.wjw.blog.dao.commentMapper;
import com.wjw.blog.entity.aritcle;
import com.wjw.blog.service.aritcleService;

@Service
public class aritcleServiceImp implements aritcleService {
	private int i=0;
	
	@Autowired
	private aritcleMapper aritcleMapper;
	
	@Autowired
	private commentMapper cMapper;
	
	private static final int PAGESIZE=10;
	
	/***
	 * 管理端对文章的所有操作
	 */

	//返回所有文章的数据
	public List<aritcle> getAllaritcles(String pageNum,String pageSize,String typeId){
		int begin=(Integer.valueOf(pageNum)-1)*10;
		int pagesize=Integer.valueOf(pageSize);
		int typeid=Integer.valueOf(typeId);
		return aritcleMapper.getAllaritcles(begin,pagesize,typeid);
	}

	//插入一篇文章
	public int insert(aritcle aritcles) {
		String content=aritcles.getContent();
		aritcles.setContent(HtmlUtils.htmlEscape(content));
		return aritcleMapper.insertSelective(aritcles);
	}

	//批量删除文章
	@Transactional
	public int delAricles(String[] ids) {
		for(String j:ids){
			int m=Integer.valueOf(j);
			i+=this.delArticle(m);
		}
		return i;
	}

	//删除某一篇文章
	@Transactional
	public int delArticle(int id) {
		//获取该文章下的评论数量
		int CommentNum=cMapper.AllCommentsNumber(id);
		//如果评论数量不为0，则先删除所有评论内容
		if(CommentNum>=1){
			int count=cMapper.deleteByArticles(id);
		}
		return aritcleMapper.deleteByPrimaryKey(id);
	}

	//获取某一篇文章的信息
	public aritcle getArticle(int id) {
		aritcle aritcle=aritcleMapper.selectByPrimaryKey(id);
		//如果没找到，返回为null
		if(aritcle==null)
			return null;
		String content=HtmlUtils.htmlUnescape(aritcle.getContent());
		aritcle.setContent(content);
		return aritcle;
	}

	//返回所有页面数量
	public int getCount(int typeid) {
		
		int count=aritcleMapper.getCount(typeid);
		int PageNum=count/10;
		PageNum=count%10==0?PageNum:PageNum+1;
		return PageNum;
		
	}

	//返回所有数量信息
	public int getSize(int typeid){
		return aritcleMapper.getCount(typeid);
	}

	//新增评论时添加评论数量
	public int updateComments(aritcle aritcles) {
		int number=aritcles.getNumber();
		number++;
		aritcles.setNumber(number);
		int count=aritcleMapper.updateByPrimaryKey(aritcles);
		return count;
	}

	//删除评论时修改评论数量
	public int delCommentsNum(int id) {
		return aritcleMapper.DelCommentNum(id);
	}
	
}
