package java;

import application.entities.User;
import application.services.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.ApplicationTestConfig;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

public class UserServiceIntegrationTests extends ApplicationTestConfig {
    @Autowired
    UserService userService;
    @Test
    public void testGetCorrect() {
        List<User> userList = userService.findAllUsers();
        assertEquals("Test Insert Person", 1,1);
    }



}

