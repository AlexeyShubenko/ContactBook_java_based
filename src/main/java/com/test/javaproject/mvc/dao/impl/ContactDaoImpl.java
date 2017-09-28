package com.test.javaproject.mvc.dao.impl;

import com.test.javaproject.mvc.dao.ContactDao;
import com.test.javaproject.mvc.domains.Contact;
import com.test.javaproject.mvc.domains.User;
import com.test.javaproject.mvc.dto.ContactDto;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 08.08.2017.
 */
@Component
public class ContactDaoImpl implements ContactDao{

    private SessionFactory sessionFactory;

    @Autowired
    public ContactDaoImpl(SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
    }


    @Override
    public List<ContactDto> getContactList(Long userId) {
        List<Contact> contacts;
        List<ContactDto> contactsDto = new ArrayList<>();
        EntityManager entityManager = this.sessionFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            contacts = entityManager.createQuery("from Contact c where c.user.userId = :userId",Contact.class)
                    .setParameter("userId", userId)
                    .getResultList();
            for (Contact c:contacts){
                System.out.println(c.toString());
                ContactDto contactDto = new ContactDto.Builder()
                        .setContactId(c)
                        .setAddress(c)
                        .setEmail(c)
                        .setFirstName(c)
                        .setHomePhoneNumber(c)
                        .setLastName(c)
                        .setMiddleName(c)
                        .setMobPhoneNumber(c)
                        .build();
                contactsDto.add(contactDto);
            }
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();

        }finally{
            entityManager.close();
        }
        return contactsDto;
    }

    @Override
    public void saveContact(Long userId, ContactDto contactDto) {
        EntityManager entityManager = this.sessionFactory.createEntityManager();
        User readUser;
        Contact contact = new Contact.Builder()
                .setFirstName(contactDto)
                .setLastName(contactDto)
                .setMiddleName(contactDto)
                .setHomePhoneNumber(contactDto)
                .setMobPhoneNumber(contactDto)
                .setAddress(contactDto)
                .setEmail(contactDto)
                .build();
        try{
            entityManager.getTransaction().begin();
            readUser = entityManager.createQuery("from User where userId =:userId",User.class)
                    .setParameter("userId",userId)
                    .getSingleResult();
            contact.setUser(readUser);
            entityManager.persist(contact);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }finally{
            entityManager.close();
        }
    }

    @Override
    public void editContact(ContactDto contactDto) {
        EntityManager entityManager = this.sessionFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            Contact oldCont = entityManager.createQuery("from Contact where contactId=:contactId",Contact.class)
                    .setParameter("contactId", contactDto.getContactId())
                    .getSingleResult();
            oldCont.setFirstName(contactDto.getFirstName());
            oldCont.setLastName(contactDto.getLastName());
            oldCont.setMiddleName(contactDto.getMiddleName());
            oldCont.setMobPhoneNumber(contactDto.getMobPhoneNumber());
            oldCont.setHomePhoneNumber(contactDto.getHomePhoneNumber());
            oldCont.setAddress(contactDto.getAddress());
            oldCont.setEmail(contactDto.getEmail());
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }finally{
            entityManager.close();
        }
    }

    @Override
    public ContactDto getContactById(Long contactId) {
        Contact contact;
        ContactDto contactDto = null;
        EntityManager entityManager = this.sessionFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            contact = entityManager.createQuery("from Contact where contactId=:contactId",Contact.class)
                    .setParameter("contactId", contactId)
                    .getSingleResult();
            contactDto = new ContactDto.Builder()
                    .setContactId(contact)
                    .setAddress(contact)
                    .setEmail(contact)
                    .setFirstName(contact)
                    .setHomePhoneNumber(contact)
                    .setLastName(contact)
                    .setMiddleName(contact)
                    .setMobPhoneNumber(contact)
                    .build();
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }finally{
            entityManager.close();
        }
        return contactDto;
    }

    @Override
    public void deleteContact(Long contactId) {
        Contact contact;
        EntityManager entityManager = this.sessionFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            contact = entityManager.createQuery("from Contact where contactId=:contactId",Contact.class)
                    .setParameter("contactId", contactId)
                    .getSingleResult();;
            entityManager.remove(contact);
            entityManager.getTransaction().commit();
        }catch(Exception e){
            entityManager.getTransaction().rollback();
        }finally{
            entityManager.close();
        }
    }

    @Override
    public boolean checkExistingContact(Long userId, String mobPhoneNumber) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Contact> contacts=null;
        try {
            entityManager.getTransaction().begin();
            contacts = entityManager.createQuery("from Contact c where c.user.userId = :userId and c.mobPhoneNumber=:mobPhoneNumber", Contact.class)
                    .setParameter("userId",userId)
                    .setParameter("mobPhoneNumber", mobPhoneNumber)
                    .getResultList();
            entityManager.getTransaction().commit();
        }catch (Exception ex){
            entityManager.getTransaction().rollback();
        }
        //size > 0 => true else false
        return contacts.size()>0?true:false;
    }


}
