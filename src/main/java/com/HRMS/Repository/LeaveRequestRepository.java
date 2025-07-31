package com.HRMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HRMS.Entity.LeaveRequest;
import com.HRMS.Entity.User;

@Repository
public interface LeaveRequestRepository extends JpaRepository<LeaveRequest, Long>{
	
	List<LeaveRequest> findByUser(User user); // Get leave requests for a specific user
	List<LeaveRequest> findByStatus(String status); // Get leave requests by status (Pending, Approved, Rejected)

}
