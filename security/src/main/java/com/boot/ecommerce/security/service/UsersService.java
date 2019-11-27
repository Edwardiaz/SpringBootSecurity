package com.boot.ecommerce.security.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.boot.ecommerce.security.entity.Users;
import com.boot.ecommerce.security.repository.InterUserDao;

@Service//con esta anotacion registramos esta clase como un bean dentro del contenedor de spring
		//@Service es un estereotipo de @Component
public class UsersService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(UsersService.class);
	
	@Autowired
	private InterUserDao userDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Users user = userDao.findByUsername(username);
		
		
		if(user == null) {
			logger.error("Login error: user doesn't exist");
			throw new UsernameNotFoundException("Login ERROR: user doesn't exist in the system...");
		}
		List<GrantedAuthority> authorities = user.getListUsersRole()
											.stream()
											.map(role -> new SimpleGrantedAuthority(role.getRoleobj().getRolename()))
											.peek(authority -> logger.info("Role: "+authority.getAuthority()))
											.collect(Collectors.toList());
		
		return new User(username, user.getPassword(), true, true, true, true, authorities);
	}

}
