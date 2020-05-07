package dev.williamchung.test;


//import com.sun.org.apache.xpath.internal.operations.Bool;
import dev.williamchung.models.User;
import dev.williamchung.repositories.UserRepository;
import dev.williamchung.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

/**
 * This is the JUnit test class for UserService.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    private UserService userService;
    private User user;
    List<User> users = new ArrayList<>();

    @Before
    public void init() {
        userService  = new UserService();
        userService.setUserRepository(userRepository);
        user = new User("testUserName","testPassword");
        user.setId(1);
    }

    /**
     * Test the authenticateUser method for success
     */
    @Test
    public void testAuthenticateUserSuccess(){

        when(userRepository.findByUsername("testUserName")).thenReturn(user);
        assertEquals(userService.authenticateUser("testUserName","testPassword"),Boolean.TRUE);
    }

    /**
     * Test the authenticateUser method for failure
     */
    @Test
    public void testAuthenticateUserFail(){
        when(userRepository.findByUsername("testUserName")).thenReturn(null);
        assertEquals(userService.authenticateUser("testUserName","testPassword"),Boolean.FALSE);
    }

    /**
     * Test the UsernameAvailable method for success
     */
    @Test
    public void testUsernameAvailableSuccess(){
        when(userRepository.findByUsername("testUserName")).thenReturn(user);
        assertEquals(userService.usernameAvailable("testUserName"), Boolean.FALSE);
    }

    /**
     * Test the usernameAvailable method for failure
     */
    @Test
    public void testUsernameAvailableFail(){
        when(userRepository.findByUsername("testUserName")).thenReturn(null);
        assertEquals(userService.usernameAvailable("testUserName"), Boolean.TRUE);
    }

    /**
     * Test the registerUser method for success
     */
    @Test
    public void testRegisterUserSuccess(){
        Answer<User> answer = new Answer<User>() {
            public User answer(InvocationOnMock invocation) throws Throwable {
                users.add(user);
                return user;
            }
        };
        when(userRepository.save(any(User.class))).then(answer);
        assertEquals(users.size(),0);
        userService.registerUser("user","password");
        assertEquals(users.size(),1);

    }

    /**
     * Test the getUserById method for success
     */
    @Test
    public void getUserByIdTest(){
        when(userRepository.findById(1)).thenReturn(user);
        User resultUser = userService.getUserById(1);
        assertEquals(resultUser, user);
        assertEquals(user.getUsername(),resultUser.getUsername());
        assertEquals(user.getPassword(),resultUser.getPassword());
    }

    /**
     * Test the getUserByUsername method for success
     */
    @Test
    public void getUserByUsernameTest(){
        when(userRepository.findByUsername("testUserName")).thenReturn(user);
        User resultUser = userService.getUserByUsername("testUserName");
        assertEquals(resultUser,user);
        assertEquals(user.getUsername(),resultUser.getUsername());
        assertEquals(user.getPassword(),resultUser.getPassword());
    }
}
