package com.chat.user.controllers;

import com.chat.user.entity.User;
import com.chat.user.entity.UserNotFoundException;
import com.chat.user.services.UserSerice;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
    private static final Long EXISTING_USER_ID = 83L;
    private static final String USER_NAME = "user name";
    private static final Long NOT_EXISTING_USER_ID = 9732L;

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserSerice userSericeMock;
    private ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetUser() throws Exception {
        User user = new User()
                .setId(EXISTING_USER_ID)
                .setName(USER_NAME);
        when(userSericeMock.read(EXISTING_USER_ID)).thenReturn(user);
        mvc.perform(
                get("/user/"+ EXISTING_USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(content().json(new ObjectMapper().writeValueAsString(user)));
    }

    @Test
    public void testGetUser404() throws Exception {
        when(userSericeMock.read(any())).thenThrow(UserNotFoundException.class);
        mvc.perform(
                get("/user/"+ NOT_EXISTING_USER_ID))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User().setName(USER_NAME);
        User createdUser = new User().setName(USER_NAME).setId(EXISTING_USER_ID);
        when(userSericeMock.createOrUpdate(user)).thenReturn(createdUser);
        mvc.perform(
                post("/user")
                        .content(objectMapper.writeValueAsString(user))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(createdUser)));
    }

    @Test
    public void testCreateUserWithIdInBody() throws Exception {
        User user = new User().setName(USER_NAME);
        User userWithId = new User().setName(USER_NAME).setId(EXISTING_USER_ID);
        when(userSericeMock.createOrUpdate(user)).thenReturn(userWithId);
        mvc.perform(
                post("/user")
                        .content(objectMapper.writeValueAsString(userWithId))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(userWithId)));
    }

}
