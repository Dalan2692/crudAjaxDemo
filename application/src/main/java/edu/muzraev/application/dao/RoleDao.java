package edu.muzraev.application.dao;

import edu.muzraev.application.domains.Role;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface RoleDao {

    Set<Role> getRoles();
    String deleteRole(long id);
    Role createRole(Role role);
}
