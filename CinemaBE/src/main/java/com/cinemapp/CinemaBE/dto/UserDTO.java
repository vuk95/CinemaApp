package com.cinemapp.CinemaBE.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UserDTO {

	private Long userId;
	
	@NotEmpty(message = "This field is required")
	@Size(min = 1, max = 25)
	private String city;
	
	@Pattern(regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$", message = "Invalid email address")
	@NotEmpty(message = "This field is required")
	private String email;
	
	@NotEmpty(message = "This field is required")
	@Size(min = 1, max = 25)
	private String firstName;
	
	@NotEmpty(message = "This field is required")
	@Size(min = 1, max = 25)
	private String lastName;
	
	@NotEmpty(message = "This field is required")
	@Pattern(regexp = "^\\+381[0-9]{9}$")
	private String phoneNumber;
	
	@NotEmpty(message = "This field is required")
	@Size(min = 6, max = 30, message = "You must enter the minimum of six characters")
	private String password;
	
}
