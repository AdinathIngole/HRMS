package com.HRMS.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class EmployeePerformance {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	 private LocalDate reviewDate;
	 private String rating;
	 private String feedback;
	 
	 @ManyToOne
	 @JoinColumn(name = "user_id", nullable = false)
	 private User user;

	 public String getEmployeeName() {
	        return user.getName(); // Assuming `User` entity has a `name` field
	    }
	 
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getFeedback() {
		return feedback;
	}

	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	 
	 
}
