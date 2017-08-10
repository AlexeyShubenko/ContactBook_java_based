package com.test.javaproject.mvc.service;

import com.test.javaproject.mvc.domains.SearchParameter;
import com.test.javaproject.mvc.dto.ContactDto;

import java.util.List;

/**
 * Created by Alexey on 10.08.2017.
 */
public interface SearchService {

    List<ContactDto> getContactsByParameter(SearchParameter searchParameter, int userId);

}
