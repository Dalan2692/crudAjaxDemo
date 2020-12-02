package edu.muzraev.application.service;


import edu.muzraev.application.domains.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public interface UserService {

    User createUser(User user);

    boolean updateUser(long id , User user);

    Optional<User> getUserById(long id);
    Optional<User> getUserByName(String username);

    List<User> getUsers();
//    Set<Role> getRoles();

    boolean deleteUserById(long id);

}
