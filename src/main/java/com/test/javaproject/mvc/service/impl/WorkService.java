package com.test.javaproject.mvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkService {

    private UserServiceImpl userServiceImpl;
    private ContactServiceImpl contactServiceImpl;
    private SearchServiceImpl searchServiceImpl;

	@Autowired
	public WorkService(UserServiceImpl userServiceImpl, ContactServiceImpl contactServiceImpl, SearchServiceImpl searchServiceImpl){
		this.userServiceImpl = userServiceImpl;
		this.contactServiceImpl = contactServiceImpl;
		this.searchServiceImpl = searchServiceImpl;
	}

	public UserServiceImpl getUserServiceImpl() {
		return userServiceImpl;
	}

	public ContactServiceImpl getContactServiceImpl() {
		return contactServiceImpl;
	}

	public SearchServiceImpl getSearchServiceImpl() {
		return searchServiceImpl;
	}
}
