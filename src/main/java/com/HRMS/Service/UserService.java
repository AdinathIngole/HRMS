package com.HRMS.Service;

import com.HRMS.Entity.User;
import com.HRMS.Request.LoginRequest;
import com.HRMS.Response.Response;

public interface UserService {

	Response register(User user);
	Response login(LoginRequest loginRequest);
	Response getAllUsers();
	Response deleteUser(String userId);
	Response getUserById(String userId);
	Response getMyInfo(String email);
	Response getUsersByCompanyName(String companyName); // New Method
}
