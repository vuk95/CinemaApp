package com.cinemapp.CinemaBE.dto.response;

import lombok.Data;

@Data
public class AuthenticationResponse {
	
	private String accessToken;
	private String message;
	
	public AuthenticationResponse(String message) {
		this.message = message;
	}

	public AuthenticationResponse(String accessToken, String message) {
		this.accessToken = accessToken;
		this.message = message;
	}

}
