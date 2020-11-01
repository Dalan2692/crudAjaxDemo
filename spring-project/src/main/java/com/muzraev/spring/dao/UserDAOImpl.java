package com.muzraev.spring.dao;

import com.muzraev.spring.models.Role;
import com.muzraev.spring.models.User;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserDAOImpl implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public List<User> allUsers() {
        return entityManager
                .createQuery("from User ", User.class)
                .getResultList();

    }

    @Override
    public void add(User user) {
        List<String> roles = user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
        Set<Role> rolesSet= new HashSet<>();
        for (String roleName: roles) {
            List<Role> foundInDB = entityManager.createQuery("select r from Role r where r.role = :roleName", Role.class)
                    .setParameter("roleName",roleName ).getResultList();
            if (foundInDB.isEmpty()){
                Role role1 = new Role();
                role1.setRole(roleName);
                entityManager.persist(role1);
                rolesSet.add(role1);
            } else {
                rolesSet.addAll(foundInDB);
            }
        }
        user.setRoles(rolesSet);
        entityManager.persist(user);
    }

    @Override
    public void delete(User user) {
        entityManager.remove(user);

    }

    @Override
    public void edit(User user) {
        entityManager.merge(user);

    }

    // передалать метод , чтобы Был optional ++
    @Override
    public Optional<User> getById(long id) {

        return Optional.ofNullable(entityManager.find(User.class, id));
    }



    // передалать метод , чтобы Был optional ++
    @Override
    public Optional<User> getUserByName(String username) {
        TypedQuery<User> typedQuery = entityManager.createQuery("select u from User u where u.username = :username",User.class);
        typedQuery.setParameter("username", username);

        try {
            return Optional.of(typedQuery.getSingleResult());
        } catch(NoResultException e){

            return Optional.empty();
        }
    }


    // добавил метод для удаления по id  ++
    @Override
    public void deleteUserById(long id) {
        Query query = entityManager.createQuery("DELETE from User where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public Set<Role> allRoles() {
        TypedQuery<Role> query = entityManager.createQuery("from Role",Role.class);
        return new HashSet<>(query.getResultList());
    }

}
