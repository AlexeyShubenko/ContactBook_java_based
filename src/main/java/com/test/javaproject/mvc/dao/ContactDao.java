package com.test.javaproject.mvc.dao;

import com.test.javaproject.mvc.domains.SearchObject;
import com.test.javaproject.mvc.dto.ContactDto;

import java.util.List;

/**
 * Created by Alexey on 08.08.2017.
 */
public interface ContactDao {

    List<ContactDto> getContactList(int user_id);
    void saveContact(int user_id, ContactDto contactDto);
    void editContact(ContactDto contactDto);
    ContactDto getContactById(int contact_id);
    void deleteContact(int contact_id);
    boolean checkExistingContact(int user_id, String mobPhoneNumber);
}
