package com.app.Service_Booking_System.service.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.Service_Booking_System.Entity.User;
import com.app.Service_Booking_System.Repository.UserRepository;
import com.app.Service_Booking_System.dto.SignUpRequestDTO;
import com.app.Service_Booking_System.dto.UserDTO;
import com.app.Service_Booking_System.enums.UserRole;

@Service
public class AuthServiceImpl implements AuthService{

	
	@Autowired
	private UserRepository userRepository;
	
	public UserDTO SignUpClient(SignUpRequestDTO signUpRequestDTO) {
		User user = new User();
		
		user.setName(signUpRequestDTO.getName());
		user.setLastName(signUpRequestDTO.getLastName());
		user.setEmail(signUpRequestDTO.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(signUpRequestDTO.getPassword()));
		user.setPhone(signUpRequestDTO.getPhone());
		
		user.setRole(UserRole.Client);
		
		return userRepository.save(user).getDTO();
	}
	
	public Boolean presentByEmail(String email) {
		return userRepository.findFirstByEmail(email) != null;
	}
	
	
	public UserDTO SignUpCompany(SignUpRequestDTO signUpRequestDTO) {
		User user = new User();
		
		user.setName(signUpRequestDTO.getName());
		user.setEmail(signUpRequestDTO.getEmail());
		user.setPhone(signUpRequestDTO.getPhone());
		user.setPassword(new BCryptPasswordEncoder().encode(signUpRequestDTO.getPassword()));
		
		user.setRole(UserRole.COMPANY);
		
		return userRepository.save(user).getDTO();
	}
	
}
