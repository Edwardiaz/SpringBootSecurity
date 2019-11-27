package com.boot.ecommerce.security.service;

import java.util.List;

import com.boot.ecommerce.security.entity.Users;

public interface InterUserService {

	public List<Users> findAll();
	
	public Users findById(Long id);
}
