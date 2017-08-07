package com.test.javaproject.mvc.dao;

import com.test.javaproject.config.DbConfig;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class HibernateUtil {

	@Autowired
	private static SessionFactory instance;

	private HibernateUtil(){
	}

	public static SessionFactory getSessionFactory(){
		return instance;
	}
	
}
