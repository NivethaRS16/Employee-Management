package com.example.demo.dto;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

public class UserRegistrationDto {
	@Size(min = 3, max = 40, message = "First name should be between 3 - 40 characters" )
	private String firstName;
	@Size(min = 3, max = 40, message = "Last name should be between 3 - 40 characters" )
	private String lastName;
	@NotBlank(message = "Enter your email id")
	@Email(message = "Enter a proper email id")
	private String email;
	@NotEmpty(message = "Please enter your password.")
	@Size(min = 5, max = 10, message = "Last name should be between 5 - 10 characters" )
	private String password;
	
	public UserRegistrationDto(){
		
	}
	
	public UserRegistrationDto(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
