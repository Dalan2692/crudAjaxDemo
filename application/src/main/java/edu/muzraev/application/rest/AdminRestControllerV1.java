package edu.muzraev.application.rest;


import edu.muzraev.application.domains.Role;
import edu.muzraev.application.domains.User;
import edu.muzraev.application.service.RoleService;
import edu.muzraev.application.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/api/v1/admin")
public class AdminRestControllerV1 {

    private final UserService userService;
    private final RoleService roleService;
    public AdminRestControllerV1(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    private Role mapStringToRole(User user){
        String userRole = user.getRoles().stream().map(Role :: getRole).findAny().orElseThrow();
        String userRoleWithPrefix = "ROLE_"+userRole;
        Set<Role> rolesFromBd = roleService.getRoles();
        return rolesFromBd.stream().filter(r -> r.getRole().equals(userRoleWithPrefix)).findAny().orElseThrow();
    }

    //ResponseEntity
    @PostMapping()
    public ResponseEntity<User> saveUser(@RequestBody User user){
//        String userRole = user.getRoles().stream().map(Role::getRole).findAny().orElseThrow();
//        String userRoleWithPrefix = "ROLE_"+userRole;
//        Set<Role> rolesFromBd = roleService.getRoles();
//        Role role = rolesFromBd.stream().filter(r -> r.getRole().equals(userRoleWithPrefix)).findAny().orElseThrow();
        Role role = mapStringToRole(user);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @GetMapping("/roles")
    public ResponseEntity<Set<Role>> findAllRoles(){
        Set<Role> roles = roleService.getRoles();
        return roles != null && !roles.isEmpty() ?
               new ResponseEntity<>(roles, HttpStatus.OK):
               new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable(name = "id") long id,  @RequestBody User user ){
        Role role =  mapStringToRole(user);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        final boolean userUpdated = userService.updateUser(id ,user);
        return userUpdated
               ? new ResponseEntity<>(HttpStatus.OK)
               : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }


    @GetMapping("{id}")
    public ResponseEntity<User> findUserById(@PathVariable("id")  long id){
        Optional<User> optional = userService.getUserById(id);
        return optional.map(user -> new ResponseEntity<>(user, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @GetMapping()
    public ResponseEntity<List<User>> findAllUsers(){
        final List<User> users = userService.getUsers();
        return users != null && !users.isEmpty()
                ? new ResponseEntity<>(users, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> deleteUser(@PathVariable("id") long id){
        return userService.deleteUserById(id)
               ? new ResponseEntity<>(true,HttpStatus.OK)
               : new ResponseEntity<>(false,HttpStatus.NOT_MODIFIED);
    }


}
