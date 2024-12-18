package bank.controller;

import java.util.HashMap;
import java.util.Map;

import bank.model.User;

public class AuthController {
    private final Map<String, User> users = new HashMap<>();

    public AuthController() {
        // Populate users with some test accounts
        users.put("admin", new User("admin", "password123"));
        users.put("user1", new User("user1", "user1password"));
        // Add more users as needed
    }

    public boolean authenticate(String username, String password) {
        User user = users.get(username);
        // Check if user exists and password matches
        return user != null && user.checkPassword(password);
    }
}