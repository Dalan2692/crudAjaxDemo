package com.muzraev.spring.services;

import com.muzraev.spring.dao.UserDAO;
import com.muzraev.spring.models.Role;
import com.muzraev.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Transactional
    @Override
    public List<User> allUsers() {
        return userDAO.allUsers();
    }

    @Transactional
    @Override
    public void add(User user) {
        userDAO.add(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDAO.delete(user);
    }

    @Transactional
    @Override
    public void edit(User user) {
        userDAO.edit(user);
    }

    @Transactional
    @Override
    public Optional<User> getById(long id) {
        return userDAO.getById(id);
    }

    @Transactional
    @Override
    public Optional<User> getUserByName(String name) {
        return userDAO.getUserByName(name);
    }

    @Transactional
    @Override
    public void deleteUserById(long id) {
        userDAO.deleteUserById(id);
    }

    @Transactional
    @Override
    public Set<Role> allRoles( ) {
        return userDAO.allRoles( );
    }
}
