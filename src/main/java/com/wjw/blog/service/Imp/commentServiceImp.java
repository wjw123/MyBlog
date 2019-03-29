package com.wjw.blog.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.HtmlUtils;

import com.wjw.blog.dao.commentMapper;
import com.wjw.blog.entity.comment;
import com.wjw.blog.service.aritcleService;
import com.wjw.blog.service.commentService;

@Service
public class commentServiceImp implements commentService {
	
	private int i=0;
	
	@Autowired
	private commentMapper mapper;
	
	@Autowired
	private aritcleService aService;
	
	private static final int CommentPageSize=5;

	//插入一篇评论
	public int insert(comment com) {
		String content=com.getContent();
		com.setContent(HtmlUtils.htmlEscape(content));
		int result=mapper.insert(com);
		return result;
	}

	//获取所有的评论信息
	public List<comment> getAllcomments(int id){
		List<comment>comments=mapper.getAllcomments(id);
		for(comment comm:comments) {
			String content=comm.getContent();
			comm.setContent(HtmlUtils.htmlUnescape(content));
		}
		return comments;
	}

	//获取某个ID的特定的评论信息
	public List<comment> getPageComments(int id, int pageNum, int PageSize) {
		int begin=(pageNum-1)*PageSize;
		List<comment>comments=mapper.getPagecomments(id, begin, PageSize);
		for(comment comm:comments){
			String content=comm.getContent();
			comm.setContent(HtmlUtils.htmlUnescape(content));
		}
		
		//如果为空，返回null
		if(comments.size()<=0)
			return null;
		return comments;
	}

	//返回某一篇文章的评论数量
	public int getCommentsNumber(int id) {
		return mapper.AllCommentsNumber(id);
	}

	//返回分页评论数据
	public List<comment> getAllcomments(String pageNum,String pageSize){
		int begin=(Integer.valueOf(pageNum)-1)*CommentPageSize;
		int pagesize=Integer.valueOf(pageSize);
		return mapper.getAllcommentss(begin,pagesize);
	}

	//返回所有评论数量
	public int AllCommentNumber() {
		return mapper.AllCommentNumber();
	}

	//根据id删除评论
	@Transactional
	public int deleteById(int id){
		//减少评论数量
		int counts=aService.delCommentsNum(id);
		int count=mapper.deleteByPrimaryKey(id);
		return count;
	}

	//删除多个评论
	@Transactional
	public int deleteComments(String[] idStrings) {
		for(String j:idStrings){
			int m=Integer.valueOf(j);
			i+=this.deleteById(m);
		}
		return i;
	}

	//根据文章ID返回所有的评论信息
	public String[] getArticleComments(int id) {
		List<comment> comments=mapper.getAllcomments(id);
		int size=comments.size();
		if(size==0||comments==null)
			return null;
		
		String[] result=new String[size];
		for(int i=0;i<comments.size();i++) {
			result[i]=comments.get(i).getId().toString();
		}
		return result;
	}

}
