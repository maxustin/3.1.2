package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional
    public List<User> getAllUsersFromDatabase() {
        return userDAO.getAllUsers();
    }

    @Override
    @Transactional
    public void addUserToDatabase(User user) {
        userDAO.addUser(user);
    }

    @Override
    @Transactional
    public void removeUserByIdFromDatabase(long id) {
        userDAO.removeUserById(id);
    }

    @Override
    @Transactional
    public User getUserByIdFromDatabase(long id) {
        return userDAO.getUserById(id);
    }

    @Override
    public User findUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }

    @Override
    @Transactional
    public void updateUserInDatabase(User user) {
        userDAO.updateUser(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("username haven't found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getAuthorities());
    }
}
