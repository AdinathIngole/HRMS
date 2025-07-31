package com.HRMS.Utils;

import java.security.SecureRandom;
import java.util.List;
import java.util.stream.Collectors;

import com.HRMS.DTO.PerformanceDTO;
import com.HRMS.DTO.UserDTO;
import com.HRMS.Entity.EmployeePerformance;
import com.HRMS.Entity.User;

public class Utils {

	private static final String ALPHANUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private static final SecureRandom secureRandom = new SecureRandom();

	// Generate Alpha-Numeric Random Number
	public static String generateRandomConfirmationCode(int length) {
		StringBuilder stringBuilder = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int randomIndex = secureRandom.nextInt(ALPHANUMERIC_STRING.length());
			char randomChar = ALPHANUMERIC_STRING.charAt(randomIndex);
			stringBuilder.append(randomChar);
		}

		return stringBuilder.toString();
	}

	// User Entity to User DTO mapper function
	public static UserDTO mapUserEntityToUserDTO(User user) {

		UserDTO userDTO = new UserDTO();

		userDTO.setId(user.getId());
		userDTO.setEmail(user.getEmail());
		userDTO.setName(user.getName());
		userDTO.setMobileNo(user.getMobileNo());
		userDTO.setRole(user.getRole());
		userDTO.setCompanyName(user.getCompanyName());
		userDTO.setDepartment(user.getDepartment());
		userDTO.setHireDate(user.getHireDate());
		userDTO.setStatus(user.getStatus());

		return userDTO;

	}

	// User List Entity to User List DTO mapper function
	public static List<UserDTO> mapUserListEntityToUserListDTO(List<User> userList) {

		return userList.stream().map(Utils::mapUserEntityToUserDTO).collect(Collectors.toList());
	}

	// Performance Entity to Performance DTO mapper function
	public static PerformanceDTO mapEmployeePerformanceEntityToPerformanceDTO(EmployeePerformance performance) {

		PerformanceDTO performanceDTO = new PerformanceDTO();

		performanceDTO.setUserId(performance.getUser().getId());
		performanceDTO.setRating(performance.getRating());
		performanceDTO.setFeedback(performance.getFeedback());
		performanceDTO.setEmployeeName(performance.getEmployeeName());
		performanceDTO.setReviewDate(performance.getReviewDate());;

		return performanceDTO;

	}

	// Performance List Entity to Performance List DTO mapper function
	public static List<PerformanceDTO> convertPerformanceListToDTOList(
			List<EmployeePerformance> performanceList) {

		return performanceList.stream().map(Utils::mapEmployeePerformanceEntityToPerformanceDTO)
				.collect(Collectors.toList());
	}
}
