package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsersFromDatabase();
    void addUserToDatabase(User user);
    void removeUserByIdFromDatabase(long id);
    User getUserByIdFromDatabase(long id);

    User findUserByUsername(String username);
    void updateUserInDatabase(User user);
}
