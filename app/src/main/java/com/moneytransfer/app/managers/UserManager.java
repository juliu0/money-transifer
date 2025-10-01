package com.moneytransfer.app.managers;

import com.moneytransfer.app.models.User;
import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private static UserManager instance;
    private Map<String, User> users;
    private User currentUser;

    private UserManager() {
        users = new HashMap<>();
        // Create a default user for demo purposes
        User defaultUser = new User("user1", "John Doe", "john@example.com", 1000.0);
        users.put(defaultUser.getId(), defaultUser);
        currentUser = defaultUser;
    }

    public static UserManager getInstance() {
        if (instance == null) {
            instance = new UserManager();
        }
        return instance;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    public User getUserById(String userId) {
        return users.get(userId);
    }

    public void addUser(User user) {
        users.put(user.getId(), user);
    }

    public Map<String, User> getAllUsers() {
        return new HashMap<>(users);
    }
}
