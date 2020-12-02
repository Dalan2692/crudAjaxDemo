package edu.muzraev.application.service;

import edu.muzraev.application.dao.UserDao;

import edu.muzraev.application.domains.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService{


    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }
    @Transactional
    @Override
    public User createUser(User user) {

        return userDao.createUser(user);
    }
    @Transactional
    @Override
    public boolean updateUser(long id , User user) {
        return userDao.updateUser(id , user);
    }
    @Transactional
    @Override
    public Optional<User> getUserById(long id) {
        return userDao.getUserById(id);
    }
    @Transactional
    @Override
    public Optional<User> getUserByName(String username) {
        return userDao.getUserByName(username);
    }
    @Transactional
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }
//    @Transactional
//    @Override
//    public Set<Role> getRoles() {
//        return userDao.getRoles();
//    }

    @Transactional
    @Override
    public boolean deleteUserById(long id) {
        return userDao.deleteUserById(id);
    }
}
