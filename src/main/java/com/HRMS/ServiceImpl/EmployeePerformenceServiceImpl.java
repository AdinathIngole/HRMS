package com.HRMS.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.DTO.PerformanceDTO;
import com.HRMS.Entity.EmployeePerformance;
import com.HRMS.Entity.User;
import com.HRMS.Exception.UserException;
import com.HRMS.Repository.EmployeePerformanceRepository;
import com.HRMS.Repository.UserRepository;
import com.HRMS.Service.EmployeePerformenceService;

@Service
public class EmployeePerformenceServiceImpl implements EmployeePerformenceService {

	@Autowired
	private EmployeePerformanceRepository employeePerformenceRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<EmployeePerformance> getPerformanceByUserId(Long userId) {
		
		 var performances = employeePerformenceRepository.findByUserId(userId);
		  return performances;
	}

	@Override
	public EmployeePerformance savePerformance(Long userId , EmployeePerformance performence) {
		User user = userRepository.findById(userId).orElseThrow(()-> new UserException("User Not Found"));	
		performence.setUser(user);
		return employeePerformenceRepository.save(performence);
	}

	/*
	 * @Override public EmployeePerformance addPerformance(Long userId, String
	 * rating, String feedback, LocalDate date) { User user =
	 * userRepository.findById(userId).orElseThrow(()-> new
	 * UserException("User Not Found"));
	 * 
	 * EmployeePerformance performance = new EmployeePerformance();
	 * 
	 * performance.setUser(user); performance.setRating(rating);
	 * performance.setFeedback(feedback); performance.setReviewDate(date);; return
	 * employeePerformenceRepository.save(performance); }
	 */
	@Override
	public void deletePerformance(Long id) {
		employeePerformenceRepository.deleteById(id);
		
	}

	@Override
	public EmployeePerformance addPerformance(PerformanceDTO performanceDTO) {
		 Optional<User> user = Optional.empty();
		 
		 if (performanceDTO.getUserId() != null) {
	            user = userRepository.findById(performanceDTO.getUserId());
	        } else if (performanceDTO.getEmployeeName() != null && !performanceDTO.getEmployeeName().isEmpty()) {
	            user = userRepository.findByName(performanceDTO.getEmployeeName());
	        }
		 if (user.isEmpty()) {
	            throw new IllegalArgumentException("Employee not found with the given ID or Name");
	        }
		 
		 EmployeePerformance performance = new EmployeePerformance();
		 
		 performance.setUser(user.get());
	        performance.setRating(performanceDTO.getRating());
	        performance.setFeedback(performanceDTO.getFeedback());
	        performance.setReviewDate(performanceDTO.getReviewDate());
		return employeePerformenceRepository.save(performance);
	}

}
