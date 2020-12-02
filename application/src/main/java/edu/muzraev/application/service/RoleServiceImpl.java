package edu.muzraev.application.service;

import edu.muzraev.application.dao.RoleDao;
import edu.muzraev.application.domains.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleDao roleDao;

    public RoleServiceImpl(RoleDao roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public Set<Role> getRoles() {
        return roleDao.getRoles();
    }

    @Override
    public String deleteRole(long id) {
        return roleDao.deleteRole(id);
    }

    @Override
    public Role createRole(Role role) {
        return roleDao.createRole(role);
    }
}
