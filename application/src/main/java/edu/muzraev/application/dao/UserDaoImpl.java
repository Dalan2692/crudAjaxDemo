package edu.muzraev.application.dao;


import edu.muzraev.application.domains.User;

import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


@Repository
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;



    @Override
    public User createUser(User user) {
        String username = user.getUsername();
        TypedQuery<User> typedQuery = entityManager.createQuery("select u from User u where u.username =:username",User.class );
        typedQuery.setParameter("username", username);
        List<User> list = typedQuery.getResultList();
        if (list.isEmpty()){
            entityManager.persist(user);
        }
        return  user;
    }

    @Override
    public boolean updateUser(long id , User user) {
        User userFromBd = entityManager.find(User.class, id);
        if (userFromBd != null){
            userFromBd.setUsername(user.getUsername());
            userFromBd.setFirstName(user.getFirstName());
            userFromBd.setLastName(user.getLastName());
            userFromBd.setRoles(user.getRoles());
            userFromBd.setAge(user.getAge());
            userFromBd.setPassword(user.getPassword());

            entityManager.merge(userFromBd);
            return true;
        }
        return false;
    }

    @Override
    public Optional<User> getUserById(long id) {

        return Optional.ofNullable(entityManager.find(User.class, id));
    }

    @Override
    public Optional<User> getUserByName(String username) {
        TypedQuery<User> typedQuery = entityManager.createQuery("select u from User u where u.username = :username",User.class);

        typedQuery.setParameter("username", username );
        try {
            return Optional.of(typedQuery.getSingleResult());
        }catch (NoResultException e){
            return Optional.empty();
        }

    }

    @Override
    public List<User> getUsers() {
        TypedQuery<User> typedQuery = entityManager.createQuery("from User",User.class);
        return typedQuery.getResultList();
    }

    @Override
    public boolean deleteUserById(long id) {
        Query query = entityManager.createQuery("DELETE from User where  id =: id");
        query.setParameter("id",id);
        query.executeUpdate();
        return true;
    }
}
