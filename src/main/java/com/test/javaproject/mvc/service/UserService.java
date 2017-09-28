package com.test.javaproject.mvc.service;

import com.test.javaproject.mvc.dto.UserDto;

public interface UserService {

	boolean checkExistingUser(String login);
//	boolean checkExistingUser(UserDto userDto);
	void saveUser(UserDto userDto);
	UserDto verifyUser(String login, String password);
	
}
