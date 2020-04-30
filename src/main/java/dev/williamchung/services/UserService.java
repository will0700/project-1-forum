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

    public Boolean authenticateUser(String username, String password) {
        User user = this.userRepository.findByUsername(username);
        if (user == null) {
            return false;
        } else {
            return user.getPassword().equals(password);
        }
    }
}
