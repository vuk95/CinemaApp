package com.cinemapp.CinemaBE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cinemapp.CinemaBE.config.TokenUtils;
import com.cinemapp.CinemaBE.dto.LoginDTO;
import com.cinemapp.CinemaBE.dto.response.AuthenticationResponse;

@RestController
public class LoginController {
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	TokenUtils tokenUtils;
	
	@RequestMapping(method = RequestMethod.POST, value = "/login", consumes = "application/json")
	public ResponseEntity<?> login(@RequestBody LoginDTO loginDTO){
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(loginDTO.getEmail(),
				loginDTO.getPassword());
		
		UserDetails details;

		try {
			Authentication authentication = authenticationManager.authenticate(token);
			SecurityContextHolder.getContext().setAuthentication(authentication);
			details = userDetailsService.loadUserByUsername(loginDTO.getEmail());
		}
		catch (Exception e) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new AuthenticationResponse(e.getMessage()));
		}
		
		String jwtToken = tokenUtils.generateToken(details);
		
		return ResponseEntity.ok(new AuthenticationResponse(jwtToken, "ok"));
	}
	
}
