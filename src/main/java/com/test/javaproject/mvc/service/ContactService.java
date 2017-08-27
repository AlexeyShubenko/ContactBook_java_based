package com.test.javaproject.mvc.service;

import com.test.javaproject.mvc.domains.SearchObject;
import com.test.javaproject.mvc.dto.ContactDto;

import java.util.List;

public interface ContactService {

	List<ContactDto> getContactList(Long user_id);
	void saveContact(Long user_id, ContactDto contactDto);
	void deleteContact(Long contact_id);
	ContactDto getContactById(Long contact_id);
	void editContact(ContactDto contact);
    boolean checkExistingContact(Long user_id, String mobPhoneNumber);
}
