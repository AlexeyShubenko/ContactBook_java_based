package com.test.javaproject.mvc.dao;


import com.test.javaproject.mvc.domains.LoginTempObject;
import com.test.javaproject.mvc.dto.UserDto;

public interface UserService {

	UserDto getUserByLoginObject(LoginTempObject loginObject);
	boolean checkExistingUser(UserDto userDto);
	void saveUser(UserDto userDto);
	
}
