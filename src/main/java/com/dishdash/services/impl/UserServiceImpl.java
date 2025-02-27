package com.dishdash.services.impl;

import com.dishdash.models.User;
import com.dishdash.repository.UserRepository;
import com.dishdash.services.UserService;

import java.util.Optional;

public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Creates/Updates the user
     * @param user current user
     * @return The updated user assuming the update went well
     */
    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * A login attempt for username
     * @param username The user's username
     * @param password The user's password
     * @return The logged-in user if the attempt was a success
     */
    @Override
    public Optional<User> loginByUsername(String username, String password) {
        return Optional.empty();
    }

    /**
     * A login attempt for email
     * @param email The user's email
     * @param password The user's password
     * @return The logged-in user if the attempt was a success
     */
    @Override
    public Optional<User> loginByEmail(String email, String password) {
        return Optional.empty();
    }
}
