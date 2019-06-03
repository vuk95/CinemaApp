package com.cinemapp.CinemaBE.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cinemapp.CinemaBE.domain.User;
import com.cinemapp.CinemaBE.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User user = userRepository.findByEmail(email);

		if (user == null) {
			throw new UsernameNotFoundException(String.format("No user found with email '%s'.", email));
		} else {
			List<GrantedAuthority> grantedAuthorities = user.getRoles().stream()
					.map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toList());
			
			return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
					grantedAuthorities);
		}
	}

}
