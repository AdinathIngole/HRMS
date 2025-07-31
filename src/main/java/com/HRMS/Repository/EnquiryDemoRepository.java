package com.HRMS.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.HRMS.Entity.EnquiryDemo;

@Repository
public interface EnquiryDemoRepository extends JpaRepository<EnquiryDemo, Long>{

}
