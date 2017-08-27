package com.test.javaproject.mvc.service.impl;

import com.test.javaproject.mvc.domains.SearchParameter;
import com.test.javaproject.mvc.dto.ContactDto;
import com.test.javaproject.mvc.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 10.08.2017.
 */
@Service
public class SearchServiceImpl implements SearchService {

    private ContactServiceImpl contactService;

    @Autowired
    public SearchServiceImpl(ContactServiceImpl contactService){
        this.contactService = contactService;
    }

    @Override
    public List<ContactDto> getContactsByParameter(SearchParameter searchParameter, Long userId) {
        String searchBy = searchParameter.getSearchBy();
        String parameter = searchParameter.getParameter();
        List<ContactDto> allContacts  = contactService.getContactList(userId);
        if(searchBy.equals("byName")){
            return searchByName(parameter, allContacts);
        }else {
            return searchByNumber(parameter, allContacts);
        }
    }
    private List<ContactDto> searchByName(String parameter, List<ContactDto> allContacts){
        List<ContactDto> byname = new ArrayList<>();
        for(ContactDto c: allContacts){
            if(c.getFirstName().regionMatches(true, 0, parameter, 0, parameter.length())
                    || c.getLastName().regionMatches(true, 0, parameter, 0, parameter.length())){
                byname.add(c);
            }
        }
        return byname;
    }

    private List<ContactDto> searchByNumber(String parameter, List<ContactDto> allContacts){
        List<ContactDto> bynum = new ArrayList<>();
        for(ContactDto c: allContacts){
            if(c.getMobPhoneNumber().regionMatches(true, 0, parameter, 0, parameter.length()))
                bynum.add(c);
        }
        return bynum;
    }

}
