package com.HRMS.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class EmployeePaySlip {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	 
		private String employeeName;
		private String employeeId;
		
		private String salaryMonth; // Format: YYYY-MM
		private double basicSalary;
		
		private double deductions;
		private double netSalary;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getEmployeeName() {
			return employeeName;
		}
		public void setEmployeeName(String employeeName) {
			this.employeeName = employeeName;
		}
		public String getEmployeeId() {
			return employeeId;
		}
		public void setEmployeeId(String employeeId) {
			this.employeeId = employeeId;
		}
		
		public String getSalaryMonth() {
			return salaryMonth;
		}
		public void setSalaryMonth(String salaryMonth) {
			this.salaryMonth = salaryMonth;
		}
		public double getBasicSalary() {
			return basicSalary;
		}
		public void setBasicSalary(double basicSalary) {
			this.basicSalary = basicSalary;
		}
		
		
		public double getDeductions() {
			return deductions;
		}
		public void setDeductions(double deductions) {
			this.deductions = deductions;
		}
		public double getNetSalary() {
			return netSalary;
		}
		public void setNetSalary(double netSalary) {
			this.netSalary = netSalary;
		}
		
		
}
