package com.HRMS.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.HRMS.Entity.ContactUs;
import com.HRMS.Entity.EnquiryDemo;
import com.HRMS.Repository.EnquiryDemoRepository;
import com.HRMS.Service.ContactUsService;
import com.HRMS.Service.EmailService;

@RestController
@RequestMapping("/contact")
public class ContactUsController {

	@Autowired
	private ContactUsService contactUsService;
	
	@Autowired
	private EnquiryDemoRepository enquiryDemoRepository;
	
	@Autowired
	private EmailService emailService;
	
	
	@PostMapping("/submit")
	@PreAuthorize("hasAuthority('HR')")
    public ResponseEntity<String> submitContact(@RequestBody ContactUs contactUs) {
        try {
            contactUsService.saveContact(contactUs);
            
         // Email to admin
            String sendTo = "adiingole1432@gmail.com";
            String subject = "New Contact Enquiry.";
            String body = "Name:" +contactUs.getName() + "\nEmail: " + contactUs.getEmail() + "\nMessage: " + contactUs.getMessage();
           
            emailService.sendEmail(sendTo, subject, body);
            
         // Email to Receiver HR
            String receiver = contactUs.getEmail();
            String receiver_sub = "Thank You for Contacting Us";
            String receiver_body = "Dear " + contactUs.getName() + ",\n\nThank you for reaching out. We have received your message and will get back to you soon.";
            
            emailService.sendEmail(receiver, receiver_sub, receiver_body);
           
            return ResponseEntity.ok("Contact saved and emails sent successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the request.");
        }
    }
	
	@PostMapping("/submitDemo")
	 public ResponseEntity<String> submitDemo(@RequestBody EnquiryDemo enquiryDemo) {
	        try {
	        	enquiryDemoRepository.save(enquiryDemo);
	        	// Email to admin
	            String sendTo = "adiingole1432@gmail.com";
	            String subject = "New Enquiry for Demo.";
	            String body = "Name:" +enquiryDemo.getFullName() 
	            			+ "\nEmail: " + enquiryDemo.getEmail() 
	            			+ "\nContact No: " + enquiryDemo.getPhoneNumber() 
	            			+ "\nCompany Name: " + enquiryDemo.getCompanyName() 
	            			+ "\nNo of Employees: " + enquiryDemo.getNo_of_employees();
	           
	            emailService.sendEmail(sendTo, subject, body);
	            
	         // Email to Receiver HR
	            String receiver = enquiryDemo.getEmail();
	            String receiver_sub = "Thank You for Contacting Us.";
	            String receiver_body = "Dear " + enquiryDemo.getFullName() + ",\n\nThank you for reaching out. We have received your message and will get back to you soon.";
	            
	            emailService.sendEmail(receiver, receiver_sub, receiver_body);
	           
	            return ResponseEntity.ok("Enquiry for Demo send and emails sent successfully!");
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process the request.");
	        }
	    }
}
