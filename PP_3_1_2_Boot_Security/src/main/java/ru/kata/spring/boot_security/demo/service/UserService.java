package ru.kata.spring.boot_security.demo.service;


import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.UserAuth;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<UserAuth> getAllUsersFromDatabase();
    void addUserToDatabase(UserAuth user);
    void removeUserByIdFromDatabase(long id);
    UserAuth getUserByIdFromDatabase(long id);

    UserAuth findUserByUsername(String username);
    void updateUserInDatabase(UserAuth user);
}
