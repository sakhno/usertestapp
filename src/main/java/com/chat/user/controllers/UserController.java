package com.chat.user.controllers;

import com.chat.user.entity.User;
import com.chat.user.entity.UserNotFoundException;
import com.chat.user.services.UserSerice;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    private UserSerice userSerice;

    public UserController(UserSerice userSerice) {
        this.userSerice = userSerice;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user) {
        user.setId(null);
        return userSerice.createOrUpdate(user);
    }

    @GetMapping("/{id}")
    public User readUser(@PathVariable Long id) throws UserNotFoundException {
        return userSerice.read(id);
    }
}
