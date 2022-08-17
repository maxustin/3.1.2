package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.dao.UserDAO;

import ru.kata.spring.boot_security.demo.model.UserAuth;

import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private static final Logger logger = Logger.getLogger("Authentication Log");

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<UserAuth> getAllUsersFromDatabase() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void addUserToDatabase(UserAuth user) {
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void removeUserByIdFromDatabase(long id) {
        userDAO.removeUserById(id);
    }

    @Override
    @Transactional
    public UserAuth getUserByIdFromDatabase(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public UserAuth findUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public void updateUserInDatabase(UserAuth user) {
        userDAO.updateUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try{
            UserAuth user = findUserByUsername(username);
            return new User(user.getUsername(), user.getPassword(), user.getAuthorities());
        } catch(RuntimeException e) {
            logger.log(Level.SEVERE, "user hasn't been found due to incorrect entered data or \"users\" content", e);
        }
        return null;
    }
}
