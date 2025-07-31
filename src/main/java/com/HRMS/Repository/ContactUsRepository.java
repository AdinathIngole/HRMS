package com.HRMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HRMS.Entity.ContactUs;

@Repository
public interface ContactUsRepository extends JpaRepository<ContactUs, Long> {

}
