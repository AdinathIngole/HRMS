package com.HRMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HRMS.Entity.LeaveRequest;
import com.HRMS.Service.LeaveRequestService;

@RestController
@RequestMapping("/leave")
public class LeaveRequestController {

	@Autowired
	private LeaveRequestService leaveRequestService;
	
	@PostMapping("/leaveRequest/{userId}")
	public ResponseEntity<LeaveRequest> submitLeaveRequest(@PathVariable Long userId, @RequestBody LeaveRequest leaveRequest) {
		
		if(userId == null) {
			System.out.println("userId is null...");
			  
		}
		LeaveRequest submitLeaveRequest = leaveRequestService.submitLeaveRequest(userId, leaveRequest);
		 return ResponseEntity.ok(submitLeaveRequest);
		
	}
	
	@GetMapping("employee/{userId}")
	public ResponseEntity<List<LeaveRequest>> getLeaveRequestsForUser(@PathVariable Long userId){
		if(userId == null) {
			System.out.println("userId is null...");
			  
		}
		List<LeaveRequest> leaveRequestsForUser = leaveRequestService.getLeaveRequestsForUser(userId);
		 return ResponseEntity.ok(leaveRequestsForUser);
		
	}
	
	@GetMapping("/admin/pending")
	public List<LeaveRequest> getPendingLeaveRequests(){
		return leaveRequestService.getPendingLeaveRequests();
		
	}
	
	@PutMapping("updateLeaveStatus/{leaveId}")
	public LeaveRequest updateLeaveStatus(@PathVariable Long leaveId, @RequestParam String status) {
		return leaveRequestService.updateLeaveStatus(leaveId, status);
		
	}
}
