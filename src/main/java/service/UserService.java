package service;

import user.User;

import java.util.ArrayList;
import java.util.List;

public class UserService {
    private static List<User> users = new ArrayList<>();
    private static int idCounter = 1;

    static {
        users.add(new User(idCounter++, "admin", "admin123", "admin@gmail.com"));
    }
    public UserService() {
        if(users.isEmpty()) {
            users.add(new User(idCounter++, "admin", "admin123", "admin@example.com"));
        }
    }

    public List<User> getAllUsers() {
        return users;
    }

    public void addUser(String username, String password, String email) {
        users.add(new User(idCounter++, username, password, email));
    }

    public User getUserById(int id) {
        return users.stream()
                .filter(user -> user.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public void updateUser(int id, String username, String password, String email) {
        User user = getUserById(id);
        if (user != null) {
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
        }
    }

    public void deleteUser(int id) {
        users.removeIf(user ->user.getId() == id);
    }
}
