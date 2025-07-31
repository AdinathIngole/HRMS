package com.HRMS.ServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.HRMS.Entity.ContactUs;
import com.HRMS.Repository.ContactUsRepository;
import com.HRMS.Service.ContactUsService;

@Service
public class ContactUsServiceImpl implements ContactUsService {

	@Autowired
	private ContactUsRepository contactRepository;
	
	@Override
	public void saveContact(ContactUs contactUs) {
		
		contactRepository.save(contactUs);
	}


}
