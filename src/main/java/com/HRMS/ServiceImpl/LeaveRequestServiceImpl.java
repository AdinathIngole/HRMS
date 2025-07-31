package com.HRMS.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.Entity.LeaveRequest;
import com.HRMS.Entity.User;
import com.HRMS.Exception.UserException;
import com.HRMS.Repository.LeaveRequestRepository;
import com.HRMS.Repository.UserRepository;
import com.HRMS.Service.LeaveRequestService;

@Service
public class LeaveRequestServiceImpl implements LeaveRequestService{

	@Autowired
	private LeaveRequestRepository leaveRequestRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public LeaveRequest submitLeaveRequest(Long userId, LeaveRequest leaveRequest) {
		
		User user = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));
		leaveRequest.setUser(user);
		leaveRequest.setStatus("pending");
		return leaveRequestRepository.save(leaveRequest);
	}

	@Override
	public List<LeaveRequest> getLeaveRequestsForUser(Long userId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserException("User Not Found"));
		
		return leaveRequestRepository.findByUser(user);
	}

	@Override
	public List<LeaveRequest> getPendingLeaveRequests() {
		
		return leaveRequestRepository.findByStatus("pending");
	}

	@Override
	public LeaveRequest updateLeaveStatus(Long leaveId, String status) {
		LeaveRequest leaveRequest = leaveRequestRepository.findById(leaveId).orElseThrow(() -> new UserException("Leave Request Not Found"));
		leaveRequest.setStatus(status);
		return leaveRequestRepository.save(leaveRequest);
	}

}
