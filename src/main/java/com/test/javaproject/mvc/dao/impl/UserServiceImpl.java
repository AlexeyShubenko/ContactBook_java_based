package com.test.javaproject.mvc.dao.impl;

import com.test.javaproject.mvc.dao.HibernateUtil;
import com.test.javaproject.mvc.dao.UserService;
import com.test.javaproject.mvc.domains.LoginTempObject;
import com.test.javaproject.mvc.domains.User;
import com.test.javaproject.mvc.dto.UserDto;
import com.test.javaproject.mvc.exceptions.UserNotFindException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

	private EntityManager manager;

	@Autowired
	public UserServiceImpl(SessionFactory sessionFactory){
		this.manager = sessionFactory.createEntityManager();

	}

	@Override
	public void saveUser(UserDto userDto) {
//		manager = HibernateUtil.getSessionFactory().createEntityManager();
		User user = new User.Builder().setFio(userDto)
				.setLoginName(userDto)
				.setPassword(userDto)
				.build();
		try{
			manager.getTransaction().begin();
			manager.persist(user);
			manager.getTransaction().commit();
			}catch(Exception e){
				manager.getTransaction().rollback();
			}finally {
//				manager.close();
			}
	}
	
	@Override
	public UserDto getUserByLoginObject(LoginTempObject loginObject) {
//		manager = HibernateUtil.getSessionFactory().createEntityManager();
//		manager = sessionFactory.createEntityManager();
		User user;
		UserDto userDto=null;
		try{
			manager.getTransaction().begin();
			user = manager.createQuery("from User where loginName=:loginName ", User.class)
								.setParameter("loginName", loginObject.getLoginName())
								.getSingleResult();
			System.out.println("User isNull: " + Objects.isNull(user));
			userDto = new UserDto.Builder()
					.setUserId(user)
					.setFio(user)
					.setLoginName(user)
					.setPassword(user)
					.build();
			manager.getTransaction().commit();
		}catch (NoResultException noResult){
				throw new UserNotFindException("Пользователь не найден");
		}catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}finally {
//			manager.close();
		}

		return userDto;
	}
	
	@Override
	public boolean checkExistingUser(UserDto userDto) {
		User userTemp = null;
		User user = new User.Builder().setFio(userDto)
				.setLoginName(userDto)
				.setPassword(userDto)
				.build();
//		manager = sessionFactory.createEntityManager();
//		manager = HibernateUtil.getSessionFactory().createEntityManager();
		try{
			manager.getTransaction().begin();
			userTemp = manager.createQuery("select u from User u where u.loginName=:loginName",User.class)
					.setParameter("loginName", user.getLoginName())
					.getSingleResult();
			manager.getTransaction().commit();
		}catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		}finally {
//			manager.close();
		}
		return Objects.nonNull(userTemp);
	}

}
