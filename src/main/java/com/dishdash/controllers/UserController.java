package com.dishdash.controllers;

import com.dishdash.models.User;
import com.dishdash.services.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class.getName());
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {
        logger.info("Creating new user");
        return ResponseEntity.status(201).body(userService.saveUser(user));
    }

    /**
     * A route for login
     * @param payload Contains all the needed info for logging in
     * @return The login info of the user if it was successful
     */
    @PostMapping("login")
    public ResponseEntity<User> login(@Valid @RequestBody LoginPayload payload) {
        logger.info("Logging in User: {}", payload.getIdentifier());
        logger.debug("Checking if user used username for login");
        //To-Do make the queries into 1 single query for both username and email
        Optional<User> userOpt = userService.loginByUsername(payload.getIdentifier(), payload.getPassword());
        ResponseEntity<User> response = userOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());

        if(!(response.getStatusCode() == HttpStatus.OK)) {
            logger.debug("Checking if user used email for login");
            userOpt = userService.loginByEmail(payload.getIdentifier(), payload.getPassword());
            response = userOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
        }

        return response;
    }

    @Getter
    @Setter
    public static class LoginPayload {
        @NotNull(message = "Email or Username Required")
        private String identifier;
        @NotNull(message = "Password Required")
        private String password;
    }
}
