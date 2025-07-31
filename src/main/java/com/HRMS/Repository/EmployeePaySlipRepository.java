package com.HRMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HRMS.Entity.EmployeePaySlip;

@Repository
public interface EmployeePaySlipRepository extends JpaRepository<EmployeePaySlip, Long> {

	List<EmployeePaySlip> findByEmployeeNameAndSalaryMonth(String employeeName , String salaryMonth);
}
