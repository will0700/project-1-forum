package dev.williamchung.services;

import dev.williamchung.models.User;
import dev.williamchung.repositories.UserRepository;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public User getUserById(Integer id) {
        return this.userRepository.findById(id);
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Boolean authenticateUser(String username, String password) {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            return false;
        } else {
            return user.getPassword().equals(password);
        }
    }

    public Boolean usernameAvailable(String username) {
        User user = this.userRepository.findByUsername(username);
        return user == null;
    }

    public User registerUser(String username, String password) {
        User user = new User(username, password);
        return this.userRepository.save(user);
    }
}
