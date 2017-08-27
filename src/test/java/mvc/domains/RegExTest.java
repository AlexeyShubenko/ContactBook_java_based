package mvc.domains;

import com.test.javaproject.mvc.domains.RegEx;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Alexey on 15.08.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
public class RegExTest {

    @Test
    public void checkValidLogin() throws Exception {
        assertEquals(true, RegEx.checkValidLogin("user1"));
        assertEquals(false, RegEx.checkValidLogin("1MyLogin"));
        assertEquals(false, RegEx.checkValidLogin("login_12*ad"));

    }

    @Test
    public void checkValidTelNumber() throws Exception {
        assertEquals(true, RegEx.checkValidTelNumber("+380(96)1234567"));
        assertEquals(false, RegEx.checkValidTelNumber("0961234567"));
    }

    @Test
    public void checkValidHomeNumber() throws Exception {
        assertEquals(true, RegEx.checkValidHomeNumber("123456"));
        assertEquals(true, RegEx.checkValidHomeNumber("12-34-56"));
        assertEquals(false, RegEx.checkValidHomeNumber("1234-56"));

    }

}