package com.wjw.blog.service;

import java.util.List;

import com.wjw.blog.entity.consumer;

public interface consumerService {
	List<consumer> getAllUsers();
	
	consumer consumer(int id);
	
	int login(String account,String password);
	
	public consumer userlogin(String account, String password);
}
