package edu.muzraev.application.dao;


import edu.muzraev.application.domains.User;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface UserDao {

    User createUser(User user);
    boolean updateUser(long id, User user);

    Optional<User> getUserById(long id);
    Optional<User> getUserByName(String username);

    List<User> getUsers();

    boolean deleteUserById(long id);


}
