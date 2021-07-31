package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "employees")
public class Employee {
	
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private long id;
	
	@Column(name = "first_name")
	@Size(min = 3, max = 40, message = "First name should be between 3 - 40 characters" )
	@NotBlank(message = "Enter the first name")
	private String firstName;
	
	@Column(name = "last_name")
	@Size(min = 3, max = 40, message = "Last name should be between 3 - 40 characters" )
	@NotBlank(message = "Enter the last name")
	private String lastName;
	
	@Column(name = "email",unique = true)
	@NotBlank(message = "Enter the email id")
	@Email(message = "Enter a proper email id")
	private String email;
	
	@Column(name = "ranking")
	@NotBlank(message = "Enter the ranking")
	private String ranking;
	
	public Employee() {
    }
	
	public Employee(String firstName, String lastName, String email, String ranking) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.ranking = ranking;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	
	
}
