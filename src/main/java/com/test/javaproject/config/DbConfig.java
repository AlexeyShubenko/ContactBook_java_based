package com.test.javaproject.config;

import com.test.javaproject.mvc.domains.Contact;
import com.test.javaproject.mvc.domains.User;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Objects;
import java.util.Properties;

/**
 * Created by Alexey on 20.05.2017.
 */
@Configuration
@PropertySource(value = "classpath:hibernate.properties")
@EnableTransactionManagement
public class DbConfig {

    private static final String PROP_DATABASE_DRIVER = "db.driver";
    private static final String PROP_DATABASE_URL = "db.url";
    private static final String PROP_DATABASE_USERNAME = "db.username";
    private static final String PROP_DATABASE_PASSWORD = "db.password";

    //    Hibernate Configuration:
    private static final String PROP_HIBERNATE_DIALECT = "db.hibernate.dialect";
    private static final String PROP_HIBERNATE_SHOW_SQL = "db.hibernate.show_sql";
    private static final String PROP_HIBERNATE_HBM2DDL_AUTO = "db.hibernate.hbm2ddl.auto";
    private static final String PROP_HIBERNATE_CONNECTION_CHARSET = "db.hibernate.connection.CharSet";
    private static final String PROP_HIBERNATE_CONNECTION_CHARACTERENCODING = "db.hibernate.connection.characterEncoding";
    private static final String PROP_HIBERNATE_CONNECTION_USEUNICODE = "db.hibernate.connection.useUnicode";

    private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "db.entitymanager.packages.to.scan";

    @Resource
    private Environment env;

    @Bean
    public DataSource dataSource() {
        // Прописываем коннект к БД. Создается пул соединений и наполняем его каким-то определенным кол-ом соединений с БД.
        System.out.println("Environment isNull: " + Objects.isNull(env));
        System.out.println(env.getRequiredProperty(PROP_DATABASE_DRIVER));
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_DRIVER));
        dataSource.setUrl(env.getRequiredProperty(PROP_DATABASE_URL));
        dataSource.setUsername(env.getRequiredProperty(PROP_DATABASE_USERNAME));
        dataSource.setPassword(env.getRequiredProperty(PROP_DATABASE_PASSWORD));

        return dataSource;
    }

//    @Bean
//    public JpaTransactionManager transactionManager() {
//        // Подымаем менеджер транзакций, чтобы аннотация @Transactional работала.
//        JpaTransactionManager transactionManager = new JpaTransactionManager();
//        transactionManager.setEntityManagerFactory(getSessionFactory());
//
//        return transactionManager;
//    }

    private Properties getHibernateProperties() {
        Properties properties = new Properties();

        properties.put("hibernate.dialect", env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
        properties.put("hibernate.show_sql", env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty(PROP_HIBERNATE_HBM2DDL_AUTO));
        properties.put("hibernate.connection.CharSet", env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_CHARSET));
        properties.put("hibernate.connection.characterEncoding",
                env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_CHARACTERENCODING));
        properties.put("hibernate.connection.useUnicode",
                env.getRequiredProperty(PROP_HIBERNATE_CONNECTION_USEUNICODE));

        return properties;
    }

    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(){
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource());
//        sessionBuilder.addPackage(env.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN));
        sessionBuilder.addAnnotatedClasses(User.class);
        sessionBuilder.addAnnotatedClasses(Contact.class);
        sessionBuilder.addProperties(getHibernateProperties());
        return sessionBuilder.buildSessionFactory();

//        LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
//        localSessionFactoryBean.setDataSource(dataSource());
//        localSessionFactoryBean.setHibernateProperties(getHibernateProperties());
//        localSessionFactoryBean.setPackagesToScan(env.getRequiredProperty(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN));
//
//        return localSessionFactoryBean.getObject();
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        configurer.setIgnoreUnresolvablePlaceholders(true);
        configurer.setIgnoreResourceNotFound(true);
        return configurer;
    }


}
