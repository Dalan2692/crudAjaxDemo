package edu.muzraev.application.service;

import edu.muzraev.application.domains.Role;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface RoleService {
    Set<Role> getRoles();
    String deleteRole(long id);
    Role createRole(Role role);
}
