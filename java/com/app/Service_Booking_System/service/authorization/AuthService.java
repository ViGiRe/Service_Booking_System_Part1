package com.app.Service_Booking_System.service.authorization;

import com.app.Service_Booking_System.dto.SignUpRequestDTO;
import com.app.Service_Booking_System.dto.UserDTO;

public interface AuthService {
	
	UserDTO SignUpClient(SignUpRequestDTO signUpRequestDTO);
	Boolean presentByEmail(String email);
	UserDTO SignUpCompany(SignUpRequestDTO signUpRequestDTO);
}
