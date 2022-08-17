package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.UserAuth;

import java.util.List;

public interface UserDAO {
    List<UserAuth> getAllUsers();
    void addUser(UserAuth user);
    void removeUserById(long id);
    UserAuth getUserById(long id);

    UserAuth findByUsername(String username);
    void updateUser(UserAuth user);
}
