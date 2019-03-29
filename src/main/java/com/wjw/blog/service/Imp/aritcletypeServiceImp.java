package com.wjw.blog.service.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wjw.blog.dao.aritcletypeMapper;
import com.wjw.blog.entity.aritcletype;
import com.wjw.blog.service.aritcletypeService;

@Service
public class aritcletypeServiceImp implements aritcletypeService {

	@Autowired
	private aritcletypeMapper aritcletypeMappers;

	public List<aritcletype> getAllaritcletypes() {
		return aritcletypeMappers.getAllaritcletype();
	}
	
}
