package com.file;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }
    public List<User> getAllUsers() { return userRepository.findAll(); }
    public Optional<User> getUserById(Long id) { return userRepository.findById(id); }
    public User createUser(User user) { return userRepository.save(user); }
    public Optional<User> updateUser(Long id, User updated) {
        return userRepository.findById(id).map(existing -> {
            if (updated.getName() != null) existing.setName(updated.getName());
            if (updated.getEmail() != null) existing.setEmail(updated.getEmail());
            return userRepository.save(existing);
        });
    }
    public boolean deleteUser(Long id) { return userRepository.deleteById(id); }
}
