package com.wjw.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.wjw.blog.service.aritcletypeService;

@Controller
public class ArticleTypeController {
	
	@Autowired
	private aritcletypeService aritcletypeservice;
}
