package com.app.Service_Booking_System.Entity;

import com.app.Service_Booking_System.dto.UserDTO;
import com.app.Service_Booking_System.enums.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String email;

	private String password;
	
	private String name;
	
	private String lastName;
	
	private String phone;
	
	private UserRole role;

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

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public User(Long id, String email, String password, String name, String lastName, String phone, UserRole role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.role = role;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	public UserDTO getDTO() {
	UserDTO userDTO = new UserDTO();
	userDTO.setName(name);
	userDTO.setLastName(lastName);
	userDTO.setRole(role);
	userDTO.setEmail(email);
	
	return userDTO;
	}
}
