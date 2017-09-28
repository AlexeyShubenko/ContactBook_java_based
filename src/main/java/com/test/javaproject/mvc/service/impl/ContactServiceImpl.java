package com.test.javaproject.mvc.service.impl;

import com.test.javaproject.mvc.dao.impl.ContactDaoImpl;
import com.test.javaproject.mvc.service.ContactService;
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
	public List<ContactDto> getContactList(Long userId) {
		return contactDao.getContactList(userId);
	}

	@Override
	public void saveContact(Long userId, ContactDto contactDto) {
		contactDao.saveContact(userId, contactDto);
	}
	
	@Override
	public void editContact(ContactDto contactDto) {
		contactDao.editContact(contactDto);
	}
	
	@Override
	public ContactDto getContactById(Long contactId) {
		return contactDao.getContactById(contactId);
	}
	
	@Override
	public void deleteContact(Long contactId) {
		contactDao.deleteContact(contactId);
	}



	@Override
	public boolean checkExistingContact(Long user_id, String mobPhoneNumber) {
		return contactDao.checkExistingContact(user_id, mobPhoneNumber);
	}
}
