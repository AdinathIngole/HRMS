package com.HRMS.Service;

import java.util.List;

import com.HRMS.DTO.PerformanceDTO;
import com.HRMS.Entity.EmployeePerformance;

public interface EmployeePerformenceService {

	List<EmployeePerformance> getPerformanceByUserId(Long userId);
	EmployeePerformance savePerformance(Long userId ,EmployeePerformance performence);
	EmployeePerformance addPerformance(PerformanceDTO performanceDTO);
	//EmployeePerformance addPerformance(Long userId , String rating ,String feedback , LocalDate date);
	void deletePerformance(Long id);
}
