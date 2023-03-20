package com.devsuperior.asdemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.devsuperior.asdemo.entities.User;
import com.devsuperior.asdemo.repositories.UserRepository;

//@Service
//public class UserService implements UserDetailsService {
public class UserService {

	@Autowired
	private UserRepository repository;

//	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User user = repository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("Email not found");
		}
		return user;
	}
}
