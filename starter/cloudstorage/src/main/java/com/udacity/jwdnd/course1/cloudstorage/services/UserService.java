package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final HashService hashService;

    public UserService(UserMapper userMapper, HashService hashService) {
        this.userMapper = userMapper;
        this.hashService = hashService;
    }

    public User getUser(String username) {
        return userMapper.getUser(username);
    }

    public int createNewUser(User user) {
        SecureRandom randomSecure = new SecureRandom();
        byte[] saltString = new byte[16];
        randomSecure.nextBytes(saltString);
        String encodeStringSalt = Base64.getEncoder().encodeToString(saltString);
        String hashedStringPassword = hashService.getHashedValue(user.getPassword(), encodeStringSalt);
        user.setSalt(encodeStringSalt);
        user.setPassword(hashedStringPassword);

        return userMapper.insertUser(user);
    }
}
