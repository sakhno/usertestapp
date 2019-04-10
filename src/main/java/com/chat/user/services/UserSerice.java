package com.chat.user.services;

import com.chat.user.entity.User;
import com.chat.user.entity.UserNotFoundException;

public interface UserSerice {
    User createOrUpdate(User user);
    User read(Long id) throws UserNotFoundException;
}
