package com.HRMS.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HRMS.Entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	boolean existsByEmail(String email);
	Optional<User> findByName(String name);
	Optional<User> findByEmail(String email);
	List<User> findByCompanyName(String companyName);
}
