package com.test.javaproject.mvc.dao;

import com.test.javaproject.mvc.domains.SearchObject;
import com.test.javaproject.mvc.dto.ContactDto;

import java.util.List;

/**
 * Created by Alexey on 08.08.2017.
 */
public interface ContactDao {

    List<ContactDto> getContactList(Long userId);
    void saveContact(Long userId, ContactDto contactDto);
    void editContact(ContactDto contactDto);
    ContactDto getContactById(Long userId);
    void deleteContact(Long userId);
    boolean checkExistingContact(Long userId, String mobPhoneNumber);
}
