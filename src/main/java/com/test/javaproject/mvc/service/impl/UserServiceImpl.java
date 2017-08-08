package com.test.javaproject.mvc.service.impl;

import com.test.javaproject.mvc.dao.impl.UserDaoImpl;
import com.test.javaproject.mvc.exceptions.PasswordErrorException;
import com.test.javaproject.mvc.exceptions.UserNotFindException;
import com.test.javaproject.mvc.service.UserService;
import com.test.javaproject.mvc.domains.LoginTempObject;
import com.test.javaproject.mvc.domains.User;
import com.test.javaproject.mvc.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

	private UserDaoImpl userDao;

	@Autowired
	public UserServiceImpl(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	@Override
	public void saveUser(UserDto userDto) {
		userDao.saveUser(userDto);
	}

	@Override
	public UserDto verifyUser(String login, String password) {
		UserDto userDto;
//		try {
			userDto = userDao.getUserByLoginObject(login);
//		}catch (UserNotFindException ex){
//			//if user is not exist
//			return null;
//		}
		if(userDto.getPassword().equals(password)){
			return userDto;
		}else {
			throw new PasswordErrorException();
		}
	}

	@Override
	public boolean checkExistingUser(String login) {
		UserDto userDto;
		try {
			userDto = userDao.getUserByLoginObject(login);
		}catch (UserNotFindException ex){
			//if user is not exist
			return false;
		}
		//user exist
		return true;
	}
	
//	@Override
//	public boolean checkExistingUser(UserDto userDto) {
//		User userTemp = null;
//		EntityManager entityManager = this.sessionFactory.createEntityManager();
//		try{
//			entityManager.getTransaction().begin();
//			userTemp = entityManager.createQuery("select u from User u where u.loginName=:loginName",User.class)
//					.setParameter("loginName", userDto.getLoginName())
//					.getSingleResult();
//			System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
//			System.out.println("User isNull: " + Objects.isNull(userTemp));
//			entityManager.getTransaction().commit();
//		}catch (NoResultException e) {
//			entityManager.getTransaction().rollback();
//			e.printStackTrace();
//		}finally {
//			entityManager.close();
//		}
//		return Objects.nonNull(userTemp);
//	}

}
