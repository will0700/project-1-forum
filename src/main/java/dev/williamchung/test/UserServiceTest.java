package dev.williamchung.test;


//import com.sun.org.apache.xpath.internal.operations.Bool;
import dev.williamchung.models.User;
import dev.williamchung.repositories.UserRepository;
import dev.williamchung.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;
    private User mockedUser;

    @Before
    public void init() {
        userService  = new UserService();
        userService.setUserRepository(userRepository);
        mockedUser = new User("testUserName","testPassword");
    }

    @Test
    public void testAuthenticateUserSuccess(){

        when(userRepository.findByUsername("testUserName")).thenReturn(mockedUser);
        assertEquals(userService.authenticateUser("testUserName","testPassword"),Boolean.TRUE);
    }

    @Test
    public void testAuthenticateUserFail(){
        when(userRepository.findByUsername("testUserName")).thenReturn(null);
        assertEquals(userService.authenticateUser("testUserName","testPassword"),Boolean.FALSE);
    }

    @Test
    public void testUsernameAvailableSuccess(){
        when(userRepository.findByUsername("testUserName")).thenReturn(mockedUser);
        assertEquals(userService.usernameAvailable("testUserName"), Boolean.FALSE);
    }

    @Test
    public void testUsernameAvailableFail(){
        when(userRepository.findByUsername("testUserName")).thenReturn(null);
        assertEquals(userService.usernameAvailable("testUserName"), Boolean.TRUE);
    }

}
