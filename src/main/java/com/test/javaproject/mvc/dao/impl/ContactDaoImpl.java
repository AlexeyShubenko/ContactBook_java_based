package com.test.javaproject.mvc.dao.impl;

import com.test.javaproject.mvc.dao.ContactDao;
import com.test.javaproject.mvc.domains.Contact;
import com.test.javaproject.mvc.domains.SearchObject;
import com.test.javaproject.mvc.domains.User;
import com.test.javaproject.mvc.dto.ContactDto;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alexey on 08.08.2017.
 */
@Component
public class ContactDaoImpl implements ContactDao{

    private SearchObject search;
    private SessionFactory sessionFactory;

    @Autowired
    public ContactDaoImpl(SessionFactory sessionFactory, SearchObject search){
        this.sessionFactory = sessionFactory;
        this.search = search;
    }


    @Override
    public List<ContactDto> getContactList(int user_id) {
        List<Contact> contacts;
        List<ContactDto> contactsDto = new ArrayList<>();
        EntityManager entityManager = this.sessionFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            contacts = entityManager.createQuery("from Contact c where c.user.user_id = :user_id",Contact.class)
                    .setParameter("user_id", user_id)
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
    public void saveContact(int user_id, ContactDto contactDto) {
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
            readUser = entityManager.createQuery("from User where user_id =:user_id",User.class)
                    .setParameter("user_id",user_id)
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
            Contact oldCont = entityManager.createQuery("from Contact where contact_id=:contact_id",Contact.class)
                    .setParameter("contact_id", contactDto.getContact_id())
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
    public ContactDto getContactById(int contact_id) {
        Contact contact;
        ContactDto contactDto = null;
        EntityManager entityManager = this.sessionFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            contact = entityManager.createQuery("from Contact where contact_id=:contact_id",Contact.class)
                    .setParameter("contact_id", contact_id)
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
    public void deleteContact(int contact_id) {
        Contact contact;
        EntityManager entityManager = this.sessionFactory.createEntityManager();
        try{
            entityManager.getTransaction().begin();
            contact = entityManager.createQuery("from Contact where contact_id=:contact_id",Contact.class)
                    .setParameter("contact_id", contact_id)
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
    public List<ContactDto> getContactsByParam(SearchObject s, int user_id) {
        List<ContactDto> contactsDto = getContactList(user_id);
        ///true - search by name, false - search by number
        if(s.isFlag()){
            return search.searchByName(s, contactsDto);
        }else return search.searchByNumber(s, contactsDto);
    }

    @Override
    public boolean checkExistingContact(int user_id, String mobPhoneNumber) {
        EntityManager entityManager = sessionFactory.createEntityManager();
        List<Contact> contacts=null;
        try {
            entityManager.getTransaction().begin();
            contacts = entityManager.createQuery("from Contact c where c.user.user_id = :user_id and c.mobPhoneNumber=:mobPhoneNumber", Contact.class)
                    .setParameter("user_id",user_id)
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
