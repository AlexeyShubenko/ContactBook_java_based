package mvc.service;

import com.test.javaproject.mvc.dto.ContactDto;
import com.test.javaproject.mvc.service.ContactService;
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
import static org.mockito.Mockito.*;

/**
 * Created by Alexey on 15.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class ContactServiceTest {

    @Mock
    private ContactService contactService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getContactList() throws Exception {
        List<ContactDto> contactDtos = new ArrayList<>();
        ContactDto contactDto1 = new ContactDto("Contact1","Contact1","Contact1",
                "+380(11)1234567","","","");
        ContactDto contactDto2 = new ContactDto("Contact2","Contact2","Contact2",
                "+380(22)9876543","","","");
        contactDtos.add(contactDto1);
        contactDtos.add(contactDto2);

        when(contactService.getContactList(1L)).thenReturn(contactDtos);

        List<ContactDto> result = contactService.getContactList(1L);
        assertEquals(2,result.size());


    }

    @Test
    public void saveContact() throws Exception {
        ContactDto contactDto = new ContactDto("Contact1","Contact1","Contact1",
                "+380(11)1234567","","","");
        contactService.saveContact(1L,contactDto);
        verify(contactService, times(1)).saveContact(1L, contactDto);
    }

    @Test
    public void editContact() throws Exception {
        ContactDto contactDto = new ContactDto("NewContactName","Contact1","Contact1",
                "+380(11)1234567","","","home@gmail.com");
        contactService.editContact(contactDto);
        verify(contactService,times(1)).editContact(contactDto);
    }

    @Test
    public void getContactById() throws Exception {
        ContactDto contactDto = new ContactDto("Contact1","Contact1","Contact1",
                "+380(11)1234567","","","");
        contactDto.setContactId(1L);

        when(contactService.getContactById(1L)).thenReturn(contactDto);

        ContactDto result = contactService.getContactById(1L);
        assertEquals("Contact1",result.getFirstName());
        assertEquals("Contact1",result.getLastName());
        assertEquals("Contact1",result.getMiddleName());
        assertEquals("+380(11)1234567",result.getMobPhoneNumber());
    }

    @Test
    public void deleteContact() throws Exception {
        contactService.deleteContact(1L);
        verify(contactService,times(1)).deleteContact(1L);
    }

    @Test
    public void checkExistingContact() throws Exception {
        when(contactService.checkExistingContact(1L,"+380(11)1234567")).thenReturn(true);

        boolean isContactExist = contactService.checkExistingContact(1L,"+380(11)1234567");
        assertEquals(true, isContactExist);

    }

}