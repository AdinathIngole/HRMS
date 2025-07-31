package com.HRMS.Service;

import java.util.List;

import com.HRMS.Entity.LeaveRequest;

public interface LeaveRequestService {

	LeaveRequest submitLeaveRequest(Long userId, LeaveRequest leaveRequest);
	List<LeaveRequest> getLeaveRequestsForUser(Long userId);
	List<LeaveRequest> getPendingLeaveRequests();
	LeaveRequest updateLeaveStatus(Long leaveId, String status);
}
