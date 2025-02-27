package com.dishdash.services;

import com.dishdash.models.User;

import java.util.Optional;

public interface UserService {
    User saveUser(User user);
    Optional<User> loginByUsername(String username, String password);
    Optional<User> loginByEmail(String email, String password);

}
