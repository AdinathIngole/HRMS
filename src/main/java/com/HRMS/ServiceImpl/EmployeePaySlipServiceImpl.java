package com.HRMS.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.Entity.EmployeePaySlip;
import com.HRMS.Repository.EmployeePaySlipRepository;
import com.HRMS.Service.EmployeePaySlipService;

@Service
public class EmployeePaySlipServiceImpl implements EmployeePaySlipService {

	@Autowired
	private EmployeePaySlipRepository employeePaySlipRepository;
	@Override
	public List<EmployeePaySlip> getPaySlips(String employeeName, String salaryMonth) {
		
		return employeePaySlipRepository.findByEmployeeNameAndSalaryMonth(employeeName, salaryMonth);
	}
	
	@Override
	public void savePayroll(EmployeePaySlip payroll) {
		System.out.println("pay roll: " +payroll);
		employeePaySlipRepository.save(payroll);
		
	}

}
