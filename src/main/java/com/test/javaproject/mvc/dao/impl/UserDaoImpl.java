package com.test.javaproject.mvc.dao.impl;

import com.test.javaproject.mvc.dao.UserDao;
import com.test.javaproject.mvc.domains.User;
import com.test.javaproject.mvc.dto.UserDto;
import com.test.javaproject.mvc.exceptions.UserNotFindException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.Objects;

/**
 * Created by Alexey on 08.08.2017.
 */
@Component
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public UserDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserDto getUserByLoginObject(String login) {
        EntityManager entityManager = this.sessionFactory.createEntityManager();
        User user;
        UserDto userDto=null;
        try{
            entityManager.getTransaction().begin();
            user = entityManager.createQuery("from User where login=:login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
            userDto = new UserDto.Builder()
                    .setUserId(user)
                    .setFio(user)
                    .setLogin(user)
                    .setPassword(user)
                    .build();
            entityManager.getTransaction().commit();
        }catch (NoResultException noResult){
            throw new UserNotFindException("User is not found!");
        }catch (Exception e) {
            entityManager.getTransaction().rollback();
            e.printStackTrace();
        }finally {
            entityManager.close();
        }
        return userDto;
    }

    @Override
    public void saveUser(UserDto userDto) {
        EntityManager entityManager = this.sessionFactory.createEntityManager();
        User user = new User.Builder().setFio(userDto)
                .setLogin(userDto)
                .setPassword(userDto)
                .build();
        try{
            entityManager.getTransaction().begin();
            entityManager.persist(user);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }finally {
            entityManager.close();
        }
    }
}
