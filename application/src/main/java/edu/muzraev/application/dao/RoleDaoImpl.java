package edu.muzraev.application.dao;

import edu.muzraev.application.domains.Role;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class RoleDaoImpl implements RoleDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Set<Role> getRoles() {
        return entityManager
                .createQuery("from Role", Role.class)
                .getResultStream()
                .collect(Collectors.toSet());
    }

    @Override
    public String deleteRole(long id) {
        Query query = entityManager.createQuery("DELETE from Role where id = :id", Role.class);
        query.setParameter("id", id );
        query.executeUpdate();
        return "Role has been removed";
    }

    @Override
    public Role createRole(Role role) {
        String newRole =  role.getRole();
        TypedQuery<Role> typedQuery = entityManager.createQuery("select r from Role r where r.role =: newRole",Role.class);
        typedQuery.setParameter("role", newRole );
        List<Role> list = typedQuery.getResultList();
        if (list.isEmpty()){
            entityManager.persist(role);
        }
        return role;
    }
}
