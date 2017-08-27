package mvc.service;

import com.test.javaproject.mvc.domains.SearchParameter;
import com.test.javaproject.mvc.dto.ContactDto;
import com.test.javaproject.mvc.service.SearchService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Created by Alexey on 15.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class SearchServiceTest {

    @Mock
    private SearchService searchService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getContactsByParameter() throws Exception {
        SearchParameter searchParameter = new SearchParameter("byName","Cont");
        List<ContactDto> contactDtos = new ArrayList<>();
        ContactDto contactDto1 = new ContactDto("Contact1","Contact1","Contact1",
                "+380(11)1234567","","","");
        ContactDto contactDto2 = new ContactDto("Contact2","Contact2","Contact2",
                "+380(22)9876543","","","");

        when(searchService.getContactsByParameter(searchParameter,1L)).thenReturn(contactDtos);

        List<ContactDto> resultList = searchService.getContactsByParameter(searchParameter, 1L);
        assertEquals(contactDtos.size(), resultList.size());
    }

}