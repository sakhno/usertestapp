package com.chat.user.repositories;

import com.chat.user.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class UserRepositoryTest {
    private static final String TEST_NAME = "test user name";
    private static final long EXISTING_USER_ID = 764;
    private static final String EXISTING_USER_NAME = "existing user name";
    private static final long NOT_EXISTING_USER_ID = 9754;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testCreateUser() {
        User userForCreate = new User()
                .setName(TEST_NAME);
        User createdEntry = userRepository.save(userForCreate);
        assertTrue(createdEntry.getId() > 0);
        assertEquals(TEST_NAME, createdEntry.getName());
    }

    @Test
    @Sql(statements = "INSERT INTO user (id, name) VALUES("+ EXISTING_USER_ID + ", '"+EXISTING_USER_NAME+"');")
    public void testGetUserById() {
        User user = userRepository.findById(EXISTING_USER_ID).get();
        assertEquals(EXISTING_USER_ID, user.getId().longValue());
        assertEquals(EXISTING_USER_NAME, user.getName());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetUserByIdWhenUserNotExists() {
        User user = userRepository.getOne(NOT_EXISTING_USER_ID);
        assertNull(user);
    }


}
