package com.HRMS.Controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HRMS.Entity.Attendence;
import com.HRMS.Entity.User;
import com.HRMS.Exception.UserException;
import com.HRMS.Repository.AttendanceRepository;
import com.HRMS.Repository.UserRepository;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/searchEmpAttendence")
	public List<Attendence> searchEmpAttendence(
	    @RequestParam(required = false) String name,
	    @RequestParam(required = false) Long id) {

		System.out.println("Search by Name: " + name + ", ID: " + id);

	    if ((name == null || name.isEmpty()) && id == null) {
	        throw new UserException("Please provide either name or ID for search.");
	    }

	    if (id != null) {
	        return attendanceRepository.findByUserId(id);
	    } else if (name != null && !name.isEmpty()) {
	        return attendanceRepository.findByNameContainingIgnoreCase(name);
	    }

	    throw new UserException("Unexpected error occurred while searching.");
	}

	@GetMapping("/get-Attendance-Record")
	public List<Attendence> getRecordsByUserId(@AuthenticationPrincipal UserDetails userDetails) {

		// Fetch the logged-in user's email from the UserDetails
		String email = userDetails.getUsername();

		// Find the User entity by email
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User Not Found"));

		return attendanceRepository.findByUserId(user.getId());
	}

	@PostMapping("/add-attendance")
	public Attendence addAttendance(@RequestBody Attendence attendance,
			@AuthenticationPrincipal UserDetails userDetails) {

		// Fetch the logged-in user's email from the UserDetails
		String email = userDetails.getUsername();

		// Find the User entity by email
		User user = userRepository.findByEmail(email).orElseThrow(() -> new UserException("User not found"));

		// Associate the attendance record with the logged-in user and set the current
		// date
		attendance.setUserId(user.getId());
		attendance.setDate(LocalDate.now());

		return attendanceRepository.save(attendance);
	}
}
