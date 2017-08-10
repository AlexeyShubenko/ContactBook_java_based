package com.test.javaproject.mvc.service;

import com.test.javaproject.mvc.domains.SearchObject;
import com.test.javaproject.mvc.dto.ContactDto;

import java.util.List;

public interface ContactService {

	List<ContactDto> getContactList(int user_id);
	void saveContact(int user_id, ContactDto contactDto);
	void deleteContact(int contact_id);
	ContactDto getContactById(int contact_id);
	void editContact(ContactDto contact);
    boolean checkExistingContact(int user_id, String mobPhoneNumber);
}
