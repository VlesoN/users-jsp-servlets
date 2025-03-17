package service;

import repository.UserRepository;
import user.User;
import java.util.List;


public class UserService {

    UserRepository userRepository = new UserRepository();

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public void addUser(String username, String password, String email) {
        userRepository.add(username,password,email);
    }

    public User getUserById(int id) {
        return userRepository.getById(id);
    }

    public void updateUser(int id, String username, String password, String email) {
        userRepository.update(id,username,password,email);
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }

}
