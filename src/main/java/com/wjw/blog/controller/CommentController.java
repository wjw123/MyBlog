package com.wjw.blog.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjw.blog.entity.aritcle;
import com.wjw.blog.entity.comment;
import com.wjw.blog.entity.consumer;
import com.wjw.blog.service.aritcleService;
import com.wjw.blog.service.commentService;
import com.wjw.blog.service.consumerService;

@Controller
public class CommentController {
	
	@Autowired
	private commentService service;
	
	@Autowired
	private consumerService cservice;
	
	@Autowired
	private aritcleService aservice;
	
	private static final int CommentPageSize=5;
	
	/**
	 * 添加评论信息
	 */
	@ResponseBody
	@RequestMapping(value="/comment/add",method=RequestMethod.POST)
	@Transactional
	public String insert(@RequestParam("content") String content,@RequestParam("authorid") int authorid,@RequestParam("aritcleid") int aritcleid){
		try {
			consumer consumer=cservice.consumer(authorid);
			aritcle aritcle=aservice.getArticle(aritcleid);
			
			comment comm=new comment();
			comm.setConsumer(consumer);
			comm.setAritcle(aritcle);
			comm.setContent(content);
			comm.setTime(new Date());
			
			int result=service.insert(comm);
			int results=aservice.updateComments(aritcle);
			
			if(result>=1&&results>=1){
				return "success";
			}else{
				return "error";
			}
			
		} catch (Exception e) {
			return "error";
		}
	}
	
	/***
	 * 评论信息翻页
	 */
	@RequestMapping(value="/comment/page",method=RequestMethod.GET)
	public String page(@RequestParam("articleId") String articleId,@RequestParam("PageNum") String pageNum,Model model) {
		int id=Integer.valueOf(articleId);
		int PageNum=Integer.valueOf(pageNum);
		List<comment>comments=service.getPageComments(id,PageNum,CommentPageSize);	
		
		model.addAttribute("comments", comments);
		//if(comments!=null)
			return "article::commentsContainer";
		//else
			//return "error";	
	}
	
	/***
	 * 管理评论信息.
	 * 
	 */
	
	/***
	 * 进入管理评论页面
	 */
	@RequestMapping(value="/admin/comments",method=RequestMethod.GET)
	public String adminComments(){
		return "admin/comment";
	}
	
	/***
	 * 管理评论信息(实现分页)
	 */
	@ResponseBody
	@RequestMapping(value="/admin/getAllcomments",method=RequestMethod.GET)
	public Map<String,Object> getAllarticles(@RequestParam("pageNum") String pageNum, @RequestParam("pageSize") String pageSize){
		
		List<comment>comments=service.getAllcomments(pageNum, pageSize);
		
        Map<String,Object>datatable=new HashMap<String, Object>();

        int count=service.AllCommentNumber();
        
        datatable.put("code",0);
        datatable.put("msg","");
        datatable.put("count",count);
        datatable.put("data",comments);
		return datatable;
	}
	
	//删除某个评论
	@ResponseBody
	@RequestMapping(value="/admin/deleteById",method=RequestMethod.POST)
	public String deleteById(@RequestParam("commentId") String commentId){
		int id=Integer.valueOf(commentId);
		int result=service.deleteById(id);
		if(result>=1)
			return "success";
		else
			return "error";
	}
	
	/***
	 * 删除多篇文章操作
	 */
	@ResponseBody
	@RequestMapping(value="/admin/delComments",method=RequestMethod.POST)
	public String delArticles(@RequestParam("ids") String ids) {
		try {
		String[] idStrings=ids.split("_");
		int m=service.deleteComments(idStrings);
		if(m>=1) {
			return "success";
		}else {
			return "error";
		}
		}catch (Exception e) {
			return "error";
		}
	}

}
