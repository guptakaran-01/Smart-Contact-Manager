package com.smart.services;

import java.util.List;
import java.util.Optional;

import com.smart.model.User;

public interface UserService {

    User saveUser(User user);
    Optional<User> getUserById(int userId);
    Optional<User> updateUser(User user);
    void deleteUser(int userId);
    boolean isUserExist(int userId);
    boolean isUserExistByEmail(String email);
    List<User> getAllUSers();
}
