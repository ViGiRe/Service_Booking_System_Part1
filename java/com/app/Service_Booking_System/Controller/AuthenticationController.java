package com.app.Service_Booking_System.Controller;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.oauth2.login.UserInfoEndpointDsl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.Service_Booking_System.Entity.User;
import com.app.Service_Booking_System.Repository.UserRepository;
import com.app.Service_Booking_System.Utils.JWTUtils;
import com.app.Service_Booking_System.dto.AuthenticationRequest;
import com.app.Service_Booking_System.dto.SignUpRequestDTO;
import com.app.Service_Booking_System.dto.UserDTO;
import com.app.Service_Booking_System.service.authorization.AuthService;
import com.app.Service_Booking_System.service.authorization.JWT.UserDetailsServiceImpl;

import jakarta.servlet.http.HttpServletResponse;

@CrossOrigin("**")
@RestController
//@EnableAsync
public class AuthenticationController {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsServiceImpl userDetailService;
	
	@Autowired
	private JWTUtils jwtUtils;
	
	@Autowired
	private UserRepository userRepository;
	
	private static final String Token_prefix = "Bearer ";
	
	private static final String Header_String = "Authorization ";
	
	
	@PostMapping("/client/sign-up")
	public ResponseEntity<?> signUpClient(@RequestBody SignUpRequestDTO signUpRequestDTO){
		
		if (authService.presentByEmail(signUpRequestDTO.getEmail())) {
			return new ResponseEntity<>(" Client already exist with this email",HttpStatus.NOT_ACCEPTABLE);
		}
		
		UserDTO createdUser = authService.SignUpClient(signUpRequestDTO);
		
		return new ResponseEntity<>(createdUser,HttpStatus.OK);
		
	}
	
	@PostMapping("/company/sign-up")
	public ResponseEntity<?> signUpCompany(@RequestBody SignUpRequestDTO signUpRequestDTO){
		
		if (authService.presentByEmail(signUpRequestDTO.getEmail())) {
			return new ResponseEntity<>(" Company already exist with this email",HttpStatus.NOT_ACCEPTABLE);
		}
		
		UserDTO createdUser = authService.SignUpCompany(signUpRequestDTO);
		
		return new ResponseEntity<>(createdUser,HttpStatus.OK);
		
	}
	
	@PostMapping("/authentication")
	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
											HttpServletResponse response) throws IOException, JSONException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(),
												authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Incorrect UserName or Password",e);
		}
		
		final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUserName());
		
		final String jwt = jwtUtils.generateToken(userDetails.getUsername());
		
		User user = userRepository.findFirstByEmail(authenticationRequest.getUserName());
		
		response.getWriter().write(new JSONObject()
				.put("userId", user.getId())
				.put("role", user.getRole())
				.toString()
		);
		
		response.addHeader("Access-Control-Expose-Headers", "Authorization");
		response.addHeader("Access-Control-allow-Headers", "Authorization" + 
		       "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-Header");
		response.addHeader(Header_String, Token_prefix + jwt);
	}

//	
//	@PostMapping("/authentication")
//	
//	public void createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest,
//	                                        HttpServletResponse response) throws IOException, JSONException {
//	    try {
//	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//	                authenticationRequest.getUserName(), authenticationRequest.getPassword()));
//	    } catch (BadCredentialsException e) {
//	        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Incorrect UserName or Password");
//	        return;
//	    }
//
//	    final UserDetails userDetails = userDetailService.loadUserByUsername(authenticationRequest.getUserName());
//
//	    final String jwt = jwtUtils.generateToken(userDetails.getUsername());
//
//	    User user = userRepository.findFirstByEmail(authenticationRequest.getUserName());
//
//	    JSONObject jsonResponse = new JSONObject();
//	    jsonResponse.put("userId", user.getId());
//	    jsonResponse.put("role", user.getRole());
//
//	    response.setContentType("application/json");
//	    response.setStatus(HttpServletResponse.SC_OK);
//	    response.getWriter().write(jsonResponse.toString());
//
//	    response.addHeader("Access-Control-Expose-Headers", "Authorization");
//	    response.addHeader("Access-Control-Allow-Headers", "Authorization, X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, X-Custom-Header");
//	    response.addHeader("Authorization", Token_prefix + jwt);
//	}

}
