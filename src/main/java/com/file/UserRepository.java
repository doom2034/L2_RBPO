package com.file;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserRepository {
    private final List<User> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<User> findAll() { return new ArrayList<>(users); }
    public Optional<User> findById(Long id) { return users.stream().filter(u -> u.getId().equals(id)).findFirst(); }
    public User save(User user) {
        if (user.getId() == null) {
            user.setId(idGenerator.getAndIncrement());
            users.add(user);
        } else {
            findById(user.getId()).ifPresent(existing -> {
                existing.setName(user.getName());
                existing.setEmail(user.getEmail());
            });
        }
        return user;
    }
    public boolean deleteById(Long id) { return users.removeIf(u -> u.getId().equals(id)); }
}
