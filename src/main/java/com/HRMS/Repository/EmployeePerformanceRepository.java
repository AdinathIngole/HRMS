package com.HRMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HRMS.Entity.EmployeePerformance;

@Repository
public interface EmployeePerformanceRepository extends JpaRepository<EmployeePerformance, Long>{

	List<EmployeePerformance> findByUserId(Long userId);
}
