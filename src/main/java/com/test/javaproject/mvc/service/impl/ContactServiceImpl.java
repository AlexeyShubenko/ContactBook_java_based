package com.test.javaproject.mvc.service.impl;

import com.test.javaproject.mvc.dao.impl.ContactDaoImpl;
import com.test.javaproject.mvc.service.ContactService;
import com.test.javaproject.mvc.domains.SearchObject;
import com.test.javaproject.mvc.dto.ContactDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

	private ContactDaoImpl contactDao;

	@Autowired
	public ContactServiceImpl(ContactDaoImpl contactDao) {
		this.contactDao = contactDao;
	}

	@Override
	public List<ContactDto> getContactList(int user_id) {
		return contactDao.getContactList(user_id);
	}

	@Override
	public void saveContact(int user_id, ContactDto contactDto) {
		contactDao.saveContact(user_id, contactDto);
	}
	
	@Override
	public void editContact(ContactDto contactDto) {
		contactDao.editContact(contactDto);
	}
	
	@Override
	public ContactDto getContactById(int contact_id) {
		return contactDao.getContactById(contact_id);
	}
	
	@Override
	public void deleteContact(int contact_id) {
		contactDao.deleteContact(contact_id);
	}

	@Override
	public List<ContactDto> getContactsByParam(SearchObject s, int user_id) {
		return contactDao.getContactsByParam(s,user_id);
	}

	@Override
	public boolean checkExistingContact(int user_id, String mobPhoneNumber) {
		return contactDao.checkExistingContact(user_id, mobPhoneNumber);
	}
}
