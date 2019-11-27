package com.boot.ecommerce.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.ecommerce.security.entity.Users;
import com.boot.ecommerce.security.repository.InterUserRepository;

@Service
public class ImpUserService implements InterUserService{

	@Autowired
	private InterUserRepository userRepo;
	
	@Override
	@Transactional(readOnly = true)
	public List<Users> findAll() {
		return (List<Users>) userRepo.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Users findById(Long id) {
		return userRepo.findById(id).orElse(null);
	}

}
