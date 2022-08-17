package ru.kata.spring.boot_security.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.UserAuth;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserAuth> getAllUsers() {
        return entityManager.createQuery("select u from UserAuth u", UserAuth.class).getResultList();
    }

    @Override
    public void addUser(UserAuth user) {
        entityManager.persist(user);
    }

    @Override
    public void removeUserById(long id) {
        entityManager.remove(getUserById(id));
    }

    @Override
    public UserAuth getUserById(long id) {
        return entityManager.find(UserAuth.class, id);
    }

    @Override
    public UserAuth findByUsername(String username) {
        return entityManager .createQuery("select u from UserAuth u where u.email = :username", UserAuth.class)
                .setParameter("username", username)
                .getSingleResult();
    }

    @Override
    public void updateUser(UserAuth user) {
        entityManager.merge(user);
    }
}
