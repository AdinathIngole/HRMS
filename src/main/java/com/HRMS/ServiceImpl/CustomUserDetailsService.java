package com.HRMS.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.HRMS.Entity.User;
import com.HRMS.Exception.UserException;
import com.HRMS.Repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		 User user = userRepository.findByEmail(username).orElseThrow(()-> new UserException("username/email not found"));
		 // Assuming 'role' is a single string like "ADMIN" or "HR"
		    GrantedAuthority authority = new SimpleGrantedAuthority(user.getRole());
		 return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),List.of(authority));
	}

}
