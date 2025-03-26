package org.example;

import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String, String> users = new HashMap<>();

    public boolean registerUser(String username, String password) {
        if (users.containsKey(username)) {
            return false; // User already exists
        }
        users.put(username, password);
        return true;
    }

    public boolean authenticateUser(String username, String password) {
        return users.containsKey(username) && users.get(username).equals(password);
    }
}