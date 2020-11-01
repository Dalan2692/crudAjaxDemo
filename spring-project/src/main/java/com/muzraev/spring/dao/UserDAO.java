package com.muzraev.spring.dao;

import com.muzraev.spring.models.Role;
import com.muzraev.spring.models.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserDAO {
    List<User> allUsers();
    void add(User user);
    void delete(User user);
    void edit(User user);
    void deleteUserById(long id);
    Optional<User> getById(long id);
    Optional<User> getUserByName(String name);
    Set<Role> allRoles();

}
