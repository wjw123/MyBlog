package com.wjw.blog.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wjw.blog.entity.aritcle;
import com.wjw.blog.entity.aritcletype;
import com.wjw.blog.entity.comment;
import com.wjw.blog.service.aritcleService;
import com.wjw.blog.service.aritcletypeService;
import com.wjw.blog.service.commentService;


@Controller
public class ArticleController {
	
	@Autowired
	private aritcleService aritcleservice;
	
	@Autowired
	private aritcletypeService aritcletypeServices;
	
	@Autowired
	private commentService cservice;
	
	private static final int PAGESIZE=5;
	
	/****
	 * 
	 * @param 管理文章要求操作
	 * @return
	 */
	
	/*进入管理主页面*/
	@RequestMapping(value="/admin/article",method=RequestMethod.GET)
	public String admin(Model model){
	    //获取所有的文章类型
		List<aritcletype>aritcletypes=aritcletypeServices.getAllaritcletypes();
		
		model.addAttribute("aritcletypes", aritcletypes);
		return "admin/article";
	}
	
	
	/*返回所有的文章数据(实现分页)*/
	@ResponseBody
	@RequestMapping(value="/admin/getAllarticles",method=RequestMethod.GET)
	public Map<String,Object> getAllarticles(@RequestParam("pageNum") String pageNum, @RequestParam("pageSize") String pageSize, @RequestParam("typeId") String typeId) {
		List<aritcle>aritcles=aritcleservice.getAllaritcles(pageNum,pageSize,typeId);
        Map<String,Object>datatable=new HashMap<String, Object>();
        
        //获取所有该类型的文章数量
        int count=aritcleservice.getSize(Integer.valueOf(typeId));
        
        datatable.put("code",0);
        datatable.put("msg","");
        datatable.put("count",count);
        datatable.put("data",aritcles);
		return datatable;
	}
	
	/**
	 * 进入撰写文章页面
	 * */
	@RequestMapping(value="/admin/writeArticle",method=RequestMethod.GET)
	public String writeArticle(Model model){
		List<aritcletype>aritcletypes=aritcletypeServices.getAllaritcletypes();
		model.addAttribute("aritcletypes", aritcletypes);
		return "admin/writeArticle";
	}
	
	/****
	 * 确认添加文章
	 */
	@ResponseBody
	@RequestMapping(value="/admin/addArticle",method=RequestMethod.POST)
	public String addArticle(@RequestParam("newmsg") String newmsg){
		try {
            ObjectMapper mapper = new ObjectMapper();
            aritcle aritcles= mapper.readValue(newmsg,aritcle.class);
            int num=aritcleservice.insert(aritcles);
            if(num>=1)
            	return "success";
            else
            	return "error";
		}catch (Exception e) {
            return "error";
        }
	}
	
	/***
	 * 删除多篇文章操作
	 */
	@ResponseBody
	@RequestMapping(value="/admin/delArticles",method=RequestMethod.POST)
	public String delArticles(@RequestParam("ids") String ids) {
		try {
		String[] idStrings=ids.split("_");
		int m=aritcleservice.delAricles(idStrings);
		if(m>=1){
			return "success";
		}else {
			return "error";
		}
		}catch (Exception e) {
			return "error";
		}
	}
	
	/***
	 * 删除一篇文章
	 */
	@ResponseBody
	@RequestMapping(value="/admin/delArticle",method=RequestMethod.POST)
	public String delArticle(@RequestParam("id") String id) {
		try {
		int m=aritcleservice.delArticle(Integer.valueOf(id));
		if(m>=1) {
			return "success";
		}else {
			return "error";
		}
		}catch (Exception e) {
			return "error";
		}
	}
	
	/***
	 * 浏览文章需要操作
	 */
	
	/**进入某一页具体文章页面
	 * */
	@RequestMapping(value="/article/{id}",method=RequestMethod.GET)
	public String article(@PathVariable("id") String id,Model model){
		aritcle aritcles=aritcleservice.getArticle(Integer.valueOf(id));
		
		//获取该文章下面的所有评论某一页的
		List<comment>comments=cservice.getPageComments(Integer.valueOf(id),1,PAGESIZE);
		
		//获取数量
		int number=cservice.getCommentsNumber(Integer.valueOf(id));
		
		if(aritcles==null){
			return "redirect:/";
		}
		model.addAttribute("article", aritcles);
		model.addAttribute("comments", comments);
		//添加评论数量
		model.addAttribute("number",number);
		return "article";
	}
}
