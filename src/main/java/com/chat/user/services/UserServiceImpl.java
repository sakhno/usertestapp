package com.chat.user.services;

import com.chat.user.entity.User;
import com.chat.user.entity.UserNotFoundException;
import com.chat.user.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserSerice {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User createOrUpdate(User user) {
        return userRepository.save(user);
    }

    @Override
    public User read(Long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }
}
