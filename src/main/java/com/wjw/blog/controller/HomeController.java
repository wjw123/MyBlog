package com.wjw.blog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.wjw.blog.entity.aritcle;
import com.wjw.blog.service.aritcleService;
import com.wjw.blog.service.consumerService;

@Controller
public class HomeController {
	
	@Autowired
	private consumerService consumer;
	
	@Autowired
	private aritcleService aritcleServices;
	
	private static final int PAGESIZE=10;
	
	//文章主页显示
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String home(Model model){
		
		//获取特定页面的文章信息
		List<aritcle>aritcles=aritcleServices.getAllaritcles("1", String.valueOf(PAGESIZE), "0");
		
		//获取页面页码
		int count=aritcleServices.getCount(0);
		
		//所有文章信息
		model.addAttribute("aritcles", aritcles);
		
		//页码总数
		model.addAttribute("PageNum", count);
		
		//显示当前页面页码数
		model.addAttribute("currentPageNum", "1");
		return "home";
	}
	
	
	//显示文章分页
	@RequestMapping(value="/{PageNum}",method=RequestMethod.GET)
	public String pagehome(@PathVariable("PageNum") String PageNum,Model model){
		
		//获取页码总数量
		int count=aritcleServices.getCount(0);
		
		int currentPageNum=Integer.valueOf(PageNum);
		
		if(currentPageNum>count)
			return "redirect:/";
		
		//获取当前页面的所有文章信息
		List<aritcle>aritcles=aritcleServices.getAllaritcles(PageNum, String.valueOf(PAGESIZE), "0");
		
		//保存当前页面的所有文章信息
		model.addAttribute("aritcles", aritcles);
		
		//保存页码总数
		model.addAttribute("PageNum", count);
		
		//保存当前页面
		model.addAttribute("currentPageNum", PageNum);
		
		return "home";
	}
}
