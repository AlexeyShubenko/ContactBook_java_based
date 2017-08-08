package com.test.javaproject.mvc.dao;

import com.test.javaproject.mvc.dto.UserDto;

/**
 * Created by Alexey on 08.08.2017.
 */
public interface UserDao {

    UserDto getUserByLoginObject(String login);
    void saveUser(UserDto userDto);

}
