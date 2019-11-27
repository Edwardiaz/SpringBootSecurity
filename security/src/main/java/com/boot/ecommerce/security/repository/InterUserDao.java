package com.boot.ecommerce.security.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.boot.ecommerce.security.entity.Users;

public interface InterUserDao extends CrudRepository<Users, Long>{

	@Query("select u from Users u where u.users=?1")
	public Users findByUsername(String username);
		
}
