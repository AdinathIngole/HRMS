package com.HRMS.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.HRMS.DTO.UserDTO;
import com.HRMS.Entity.User;
import com.HRMS.Exception.UserException;
import com.HRMS.Repository.UserRepository;
import com.HRMS.Request.LoginRequest;
import com.HRMS.Response.Response;
import com.HRMS.Service.UserService;
import com.HRMS.Utils.JWTUtils;
import com.HRMS.Utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	public Response register(User user) {

		Response response = new Response();
		try {
			if (user.getRole() == null || user.getRole().isBlank()) {
				user.setRole("USER");
			}
			if (userRepository.existsByEmail(user.getEmail())) {
				throw new UserException(user.getEmail() + "Email is Already Exists");
			}

			user.setPassword(passwordEncoder.encode(user.getPassword()));
			User saveUser = userRepository.save(user);

			UserDTO userDTO = Utils.mapUserEntityToUserDTO(saveUser);

			response.setStatusCode(200);
			response.setUser(userDTO);

		} catch (UserException e) {
			response.setStatusCode(400);
			response.setMessage(e.getMessage());
		} catch (Exception e) {

			response.setStatusCode(500);
			response.setMessage("Error Occurred During User Registration" + e.getMessage());

		}
		return response;
	}

	@Override
	public Response login(LoginRequest loginRequest) {

		Response response = new Response();

		try {

			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

			var user = userRepository.findByEmail(loginRequest.getEmail())
					.orElseThrow(() -> new UserException("User Not Found"));
			var token = jwtUtils.genrateToken(user);

			response.setStatusCode(200);
			response.setToken(token);
			response.setRole(user.getRole());
			response.setUserId(user.getId());
			response.setExpirationTime("7 Days");
			response.setMessage("Successful");

		} catch (UserException e) {

			response.setStatusCode(404);
			response.setMessage(e.getMessage());

		} catch (Exception e) {

			response.setStatusCode(500);
			response.setMessage("Error Occurred During User Login" + e.getMessage());

		}
		return response;
	}

	@Override
	public Response getAllUsers() {

		Response response = new Response();

		try {
			List<User> userList = userRepository.findAll();
			List<UserDTO> userDTOList = Utils.mapUserListEntityToUserListDTO(userList);

			response.setStatusCode(200);
			
			response.setMessage("successful");
			response.setUserList(userDTOList);
		} catch (Exception e) {
			response.setStatusCode(500);
			response.setMessage("Error getting all users " + e.getMessage());

		}
		return response;
	}

	@Override
	public Response deleteUser(String userId) {

		Response response = new Response();

		try {
			userRepository.findById(Long.valueOf(userId)).orElseThrow(() -> new UserException("User Not Found"));

			userRepository.deleteById(Long.valueOf(userId));

			response.setStatusCode(200);
			response.setMessage("successful");

		} catch (UserException e) {

			response.setStatusCode(404);
			response.setMessage(e.getMessage());

		} catch (Exception e) {

			response.setStatusCode(500);
			response.setMessage("Error getting delete User " + e.getMessage());

		}
		return response;
	}

	@Override
	public Response getUserById(String userId) {
		Response response = new Response();

		try {
			User user = userRepository.findById(Long.valueOf(userId))
					.orElseThrow(() -> new UserException("User Not Found"));

			UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);

			response.setStatusCode(200);
			response.setMessage("successful");
			response.setUser(userDTO);

		} catch (UserException e) {

			response.setStatusCode(404);
			response.setMessage(e.getMessage());

		} catch (Exception e) {

			response.setStatusCode(500);
			response.setMessage("Error getting User by ID " + e.getMessage());

		}
		return response;
	}

	@Override
	public Response getMyInfo(String email) {

		Response response = new Response();

		try {
			User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User Not Found"));

			UserDTO userDTO = Utils.mapUserEntityToUserDTO(user);

			response.setStatusCode(200);
			response.setMessage("successful");
			response.setUser(userDTO);

		} catch (UserException e) {

			response.setStatusCode(404);
			response.setMessage(e.getMessage());

		} catch (Exception e) {

			response.setStatusCode(500);
			response.setMessage("Error getting User by Email " + e.getMessage());

		}
		return response;
	}

	@Override
	public Response getUsersByCompanyName(String companyName) {
		// TODO Auto-generated method stub
		Response response = new Response();
		
		try {
			List<User> userListByCompanyName = userRepository.findByCompanyName(companyName);
			
			if (userListByCompanyName.isEmpty()) {
	            throw new UserException("No employees found for company: " + companyName);
	        }
			
			List<UserDTO> UserListDTO = Utils.mapUserListEntityToUserListDTO(userListByCompanyName);
			
			response.setStatusCode(200);
			response.setMessage("Successful");
			response.setUserList(UserListDTO);
		} catch (UserException e) {
	        response.setStatusCode(404);
	        response.setMessage(e.getMessage());
	    } catch (Exception e) {
	        response.setStatusCode(500);
	        response.setMessage("Error fetching employees by company: " + e.getMessage());
	    }

	    return response;
	}

}
