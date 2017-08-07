package com.test.javaproject.mvc.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkService {

    private UserServiceImpl userServiceImpl;
    private ContactServiceImpl contactServiceImpl;

	@Autowired
	public WorkService(UserServiceImpl userServiceImpl, ContactServiceImpl contactServiceImpl){
		this.userServiceImpl = userServiceImpl;
		this.contactServiceImpl = contactServiceImpl;
	}

	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public ContactServiceImpl getContactServiceImpl() {
		return contactServiceImpl;
	}

	public void setContactServiceImpl(ContactServiceImpl contactServiceImpl) {
		this.contactServiceImpl = contactServiceImpl;
	}

}
