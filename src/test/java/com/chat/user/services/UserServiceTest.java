package com.chat.user.services;

import com.chat.user.entity.User;
import com.chat.user.entity.UserNotFoundException;
import com.chat.user.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private static final String TEST_NAME = "test name";
    private static final Long EXISTING_USER_ID = 73L;

    private UserSerice userSerice;
    private UserRepository userRepositoryMock;


    @Before
    public void setUp() {
        userRepositoryMock = mock(UserRepository.class);
        userSerice = new UserServiceImpl(userRepositoryMock);
    }

    @Test
    public void testCreateUser() {
        User user = new User()
                .setName(TEST_NAME);
        when(userRepositoryMock.save(user)).thenReturn(user);
        assertEquals(user, userSerice.createOrUpdate(user));
        verify(userRepositoryMock).save(user);
    }

    @Test
    public void testReadUser() throws UserNotFoundException {
        User user = new User()
                .setName(TEST_NAME);
        when(userRepositoryMock.findById(EXISTING_USER_ID)).thenReturn(Optional.of(user));
        assertEquals(user, userSerice.read(EXISTING_USER_ID));
        verify(userRepositoryMock).findById(EXISTING_USER_ID);
    }

}
