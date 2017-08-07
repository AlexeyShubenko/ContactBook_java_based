package com.test.javaproject.mvc.dao;

import com.test.javaproject.mvc.domains.SearchObject;
import com.test.javaproject.mvc.dto.ContactDto;

import java.util.List;

public interface ContactService {

	List<ContactDto> getContactList(int user_id);
	void saveContact(int user_id, ContactDto contactDto);
	void deleteContact(int contact_id);
	ContactDto getContactById(int contact_id);
	void editContact(ContactDto contact);
	 
	List<ContactDto> getContactsByParam(SearchObject s, int user_id);
}
