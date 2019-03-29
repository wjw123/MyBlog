package com.wjw.blog.service.Imp;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjw.blog.dao.consumerMapper;
import com.wjw.blog.entity.consumer;
import com.wjw.blog.service.consumerService;

@Service
public class consumerServiceImp implements consumerService {
	@Autowired
	private consumerMapper consumer;

	public List<consumer> getAllUsers(){
		return consumer.getAllConsumer();
	}

	public com.wjw.blog.entity.consumer consumer(int id) {
		return consumer.selectByPrimaryKey(id);
	}
	
	
	//管理员登录方法
	public int login(String account, String password) {
		return consumer.login(account, password);
	}
	
	//用户登录方法
	public consumer userlogin(String account, String password) {
		return consumer.userlogin(account, password);
	}
	
}
