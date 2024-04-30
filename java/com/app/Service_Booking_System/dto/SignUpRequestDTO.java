package com.app.Service_Booking_System.dto;

import lombok.Data;

@Data
public class SignUpRequestDTO {

	private Long id;
	
	private String email;

	private String password;
	
	private String name;
	
	private String lastName;
	
	private String phone;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public SignUpRequestDTO(Long id, String email, String password, String name, String lastName, String phone) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
	}
	
	public SignUpRequestDTO() {
		// TODO Auto-generated constructor stub
	}
}
