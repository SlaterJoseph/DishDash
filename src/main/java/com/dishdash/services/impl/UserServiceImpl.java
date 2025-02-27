package com.dishdash.services.impl;

import com.dishdash.models.User;
import com.dishdash.repository.UserRepository;
import com.dishdash.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class.getName());


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates/Updates the user
     * @param user current user
     * @return The updated user assuming the update went well
     */
    @Override
    public User saveUser(User user) {
        logger.info("Saving user: " + user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
        logger.info("Login by username: " + username);
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }

    /**
     * A login attempt for email
     * @param email The user's email
     * @param password The user's password
     * @return The logged-in user if the attempt was a success
     */
    @Override
    public Optional<User> loginByEmail(String email, String password) {
        logger.info("Login by email: " + email);
        Optional<User> userOpt = userRepository.findByEmail(email);
        return userOpt.filter(user -> passwordEncoder.matches(password, user.getPassword()));
    }
}
