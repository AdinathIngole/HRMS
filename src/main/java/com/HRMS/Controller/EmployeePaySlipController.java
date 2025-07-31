package com.HRMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.HRMS.Entity.EmployeePaySlip;
import com.HRMS.Service.EmployeePaySlipService;

@RestController
@RequestMapping("/paySlips")
public class EmployeePaySlipController {

	@Autowired
	private EmployeePaySlipService employeePaySlipService;
	
	 @PostMapping("/create")
	 @PreAuthorize("hasAuthority('HR')")
	 public ResponseEntity<String> createPayroll(@RequestBody EmployeePaySlip payroll){
		 System.out.println("Pay slip" +payroll);
		 employeePaySlipService.savePayroll(payroll);
		 return ResponseEntity.ok("Payroll created successfully!");
	 }
	
	@GetMapping("/getPaySlip")
	public ResponseEntity<?> getPaySlip(@RequestParam String employeeName , @RequestParam String salaryMonth){
		System.out.println("Emp name:" +employeeName);
		System.out.println("Salary month:" +salaryMonth);

		List<EmployeePaySlip> paySlips = employeePaySlipService.getPaySlips(employeeName, salaryMonth);
		
		System.out.println("paySlips: " +paySlips);
		if (paySlips == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Payslip not found for the given month.");
        }
		return ResponseEntity.ok(paySlips);
		
	}
}
