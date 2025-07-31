package com.HRMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HRMS.Entity.User;
import com.HRMS.Request.LoginRequest;
import com.HRMS.Response.Response;
import com.HRMS.Service.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<Response> register(@RequestBody User user){
		
		Response response = userService.register(user);
		
		return ResponseEntity.status(response.getStatusCode()).body(response);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginRequest loginRequest){
		
		Response response = userService.login(loginRequest);
		return ResponseEntity.status(response.getStatusCode()).body(response);
	}
}
