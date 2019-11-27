package com.boot.ecommerce.security.repository;

import org.springframework.data.repository.CrudRepository;

import com.boot.ecommerce.security.entity.Users;

public interface InterUserRepository extends CrudRepository<Users, Long>{

	
}
