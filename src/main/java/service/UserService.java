package service;

import repository.UserRepository;
import user.User;
import java.util.List;
import java.util.Optional;


public class UserService {

    UserRepository userRepository = new UserRepository();

    public List<User> getAllUsers() {
        return userRepository.getAll();
    }

    public void addUser(String username, String password, String email) {
        userRepository.add(username,password,email);
    }

    public Optional<User> getUserById(int id) {
        return userRepository.getById(id);
    }

    public void updateUser(int id, String username, String password, String email) {
        userRepository.update(id,username,password,email);
    }

    public void deleteUser(int id) {
        userRepository.delete(id);
    }

    public Optional<User> authenticateUser(String username,String password) {
        return userRepository.find(username,password);
    }

}
