//package com.quizapp.controllers;
//
//import java.security.Principal;
//
//import javax.validation.Valid;
//
//import org.modelmapper.ModelMapper;
//
////import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.quizapp.entities.User;
//import com.quizapp.payloads.JwtAuthRequest;
//import com.quizapp.payloads.JwtAuthResponse;
//import com.quizapp.payloads.UserDto;
//import com.quizapp.repositories.UserRepository;
//import com.quizapp.security.JwtTokenHelper;
//import com.quizapp.services.UserServiceI;
//
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@Slf4j
//@RequestMapping("/api/v1/auth")
//public class AuthController {
//
//	@Autowired
//	private JwtTokenHelper jwtTokenHelper;
//
//	@Autowired
//	private UserDetailsService userDetailsService;
//
//	@Autowired
//	private AuthenticationManager authenticationManager;
//	
//	@Autowired
//	private UserServiceI userService;
//
//	// Login API
//	@Operation(summary = "Login and get JWT token", description = "Authenticate user and generate a JWT token.")
//	@PostMapping(value = "/login", consumes = { "application/json", "application/xml" }, produces = {
//			"application/json", "application/xml" })
//	public ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest request) {
//		log.info("Login attempt for user: {}", request.getEmail());
//
//		// Authenticate username and password
//		try {
//			authenticate(request.getEmail(), request.getPassword());
//			log.info("Authentication successful for user: {}", request.getEmail());
//		} catch (BadCredentialsException e) {
//			log.error("Authentication failed for user: {}", request.getEmail(), e);
//			throw new BadCredentialsException("Invalid username or password", e);
//		}
//
//		// Find userDetails
//		UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
//		log.info("User details loaded for user: {}", request.getEmail());
//
//		// Generate token
//		String token = this.jwtTokenHelper.generateToken(userDetails);
//		log.info("JWT token generated successfully for user: {}", request.getEmail());
//
//		// Return response
//		//JwtAuthResponse response = new JwtAuthResponse(token);
//		JwtAuthResponse response = new JwtAuthResponse();
//		response.setToken(token);
//		log.info("Returning token to user: {}", request.getEmail());
//
//		return new ResponseEntity<>(response, HttpStatus.OK);
//	}
//
//	private void authenticate(String email, String password) {
//		UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email,
//				password);
//		try {
//			this.authenticationManager.authenticate(authenticationToken);
//		} catch (BadCredentialsException e) {
//			log.error("Authentication failed for user: {}", email, e);
//			throw new BadCredentialsException("Invalid username or password", e);
//		}
//
//	}
//	
//	// register new user api
//
//		@PostMapping("/register")
//		public ResponseEntity<UserDto> registerUser(@Valid @RequestBody UserDto userDto) {
//			UserDto registeredUser = this.userService.registerUser(userDto);
//			return new ResponseEntity<UserDto>(registeredUser, HttpStatus.CREATED);
//		}
//
//		
//		// get loggedin user data
//		@Autowired
//		private UserRepository userRepository;
//		@Autowired
//		private ModelMapper mapper;
//
//		@GetMapping("/current-user/")
//		public ResponseEntity<UserDto> getUser(Principal principal) {
//			User user = this.userRepository.findByEmail(principal.getName()).get();
//			return new ResponseEntity<UserDto>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
//		}
//}
