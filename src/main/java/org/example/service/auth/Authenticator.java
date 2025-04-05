package org.example.service.auth;

import org.example.model.user.UserManager;

public class Authenticator {
    private UserManager userManager;

    public Authenticator(UserManager userManager) {
        this.userManager = userManager;
    }

    public boolean register(String username, String password) {
        return userManager.registerUser(username, password);
    }

    public boolean login(String username, String password) {
        return userManager.authenticateUser(username, password);
    }
}