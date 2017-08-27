package com.test.javaproject.mvc.controllers;

import com.test.javaproject.mvc.dao.ContactDao;
import com.test.javaproject.mvc.domains.SearchParameter;
import com.test.javaproject.mvc.dto.ContactDto;
import com.test.javaproject.mvc.dto.UserDto;
import com.test.javaproject.mvc.service.impl.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Alexey on 08.08.2017.
 */
@RestController
public class SearchController {

    private WorkService workService;

    @Autowired
    public SearchController(WorkService workService) {
        this.workService = workService;
    }

    @RequestMapping(value = "/getContactsByName", method = RequestMethod.POST)
    public List<ContactDto> getContactsByName(@RequestBody SearchParameter searchParameter, HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        List<ContactDto> allContactsByParameter = null;
        if(Objects.nonNull(userDto)){
            allContactsByParameter =
                    workService.getSearchServiceImpl().getContactsByParameter(searchParameter, userDto.getUserId());
        }
        return allContactsByParameter;
    }

    @RequestMapping(value = "/getAllContacts", method = RequestMethod.GET)
    public List<ContactDto> getAllContacts(HttpSession session){
        UserDto userDto = (UserDto) session.getAttribute("userDto");
        List<ContactDto> allContacts = null;
        if(Objects.nonNull(userDto)) {
            allContacts = workService.getContactServiceImpl().getContactList(userDto.getUserId());
        }
        return allContacts;
    }


}