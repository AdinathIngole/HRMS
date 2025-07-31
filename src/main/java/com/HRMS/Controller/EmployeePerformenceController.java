package com.HRMS.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HRMS.DTO.PerformanceDTO;
import com.HRMS.Entity.EmployeePerformance;
import com.HRMS.Service.EmployeePerformenceService;
import com.HRMS.Utils.Utils;

@RestController
@RequestMapping("/performance")
public class EmployeePerformenceController {

	@Autowired
	private EmployeePerformenceService employeePerformenceService;
	
	
	@GetMapping("/{userId}")
	public List<PerformanceDTO> getPerformanceByEmployeeId(@PathVariable Long userId) {
	    List<EmployeePerformance> performances = employeePerformenceService.getPerformanceByUserId(userId);
	    return Utils.convertPerformanceListToDTOList(performances);
	}

	@PostMapping("/save-emp-performance/{userId}")
	public EmployeePerformance savePerformance(@PathVariable Long userId,@RequestBody EmployeePerformance performance) {
		return employeePerformenceService.savePerformance(userId , performance);
		
	}
	
	/*
	 * @PostMapping("/addPerformance")
	 * 
	 * @PreAuthorize("hasAuthority('HR')") public ResponseEntity<String>
	 * addPerformance(@RequestBody PerformanceDTO performanceDTO){
	 * System.out.println("Performance DTO:" +performanceDTO);
	 * System.out.println("Date received: " + performanceDTO.getReviewDate());
	 * 
	 * if (performanceDTO.getReviewDate() == null) { return
	 * ResponseEntity.badRequest().body("Invalid date format. Expected yyyy-MM-dd."
	 * ); } employeePerformenceService.addPerformance(performanceDTO.getUserId(),
	 * performanceDTO.getRating(), performanceDTO.getFeedback(),
	 * performanceDTO.getReviewDate()); return
	 * ResponseEntity.ok("Performance added successfully"); }
	 */
	
	@PostMapping("/addPerformance")
	@PreAuthorize("hasAuthority('HR')")
    public EmployeePerformance addPerformance(@RequestBody PerformanceDTO performanceDTO) {
        return employeePerformenceService.addPerformance(performanceDTO);
    }
	@DeleteMapping("/{id}")
    public void deletePerformance(@PathVariable Long id) {
        employeePerformenceService.deletePerformance(id);
    }
}
