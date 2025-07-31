package com.HRMS.Service;

import java.util.List;

import com.HRMS.Entity.EmployeePaySlip;

public interface EmployeePaySlipService {

	List<EmployeePaySlip> getPaySlips(String employeeId, String salaryMonth);
	public void savePayroll(EmployeePaySlip payroll);
}
