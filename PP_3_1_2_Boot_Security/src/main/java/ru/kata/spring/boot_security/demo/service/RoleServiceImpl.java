package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.kata.spring.boot_security.demo.dao.RoleDAO;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{
    private RoleDAO roleDAO;

    public RoleServiceImpl(RoleDAO roleDAO) {
        this.roleDAO=roleDAO;
    }

    @Override
    public List<Role> getAllRolesFromDatabase() {
        return roleDAO.getAllRoles();
    }

    @Override
    public Role getRoleByIdFromDatabase(long id) {
        return roleDAO.getRoleById(id);
    }

    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleDAO.findRoleByRoleName(roleName);
    }

    @Override
    public Set<Role> findByIdRoles(List<Long> roles) {
        return roleDAO.findByIdRoles(roles);
    }
}
