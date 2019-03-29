package com.wjw.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wjw.blog.entity.consumer;
import com.wjw.blog.service.consumerService;

@Controller
public class ConsumerController {
	
	@Autowired
	private consumerService cService;
	
	/***
	 * 进入后台登录页面
	 * @return
	 */
	@RequestMapping(value="/admin/login",method=RequestMethod.GET)
	public String adminComments(){
		return "admin/login";
	}
	
	/***
	 * 进入普通用户登录页面
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public String userlogin() {
		return "login";
	}
	
	
	/***
	 * 处理登录时的数据
	 * 
	 */
	@ResponseBody
	@RequestMapping(value="/logining",method=RequestMethod.POST)
	public String insert(@RequestParam("account") String account,@RequestParam("password") String password)
	{
		//int count=cService.login(account, password);
		consumer user=cService.userlogin(account, password);
		if(user!=null)
			return "success";
		else
			return "error";
	}
}
