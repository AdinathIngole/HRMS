package com.HRMS.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.HRMS.Entity.Attendence;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendence, Long> {

	List<Attendence> findByUserId(Long userId);

	@Query("SELECT a FROM Attendence a JOIN User u ON a.userId = u.id WHERE LOWER(u.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	List<Attendence> findByNameContainingIgnoreCase(String name);
}
