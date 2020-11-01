package com.muzraev.spring.controllers;


import com.muzraev.spring.models.Role;
import com.muzraev.spring.models.User;
import com.muzraev.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String allUsers(Model model , Principal principal){
        List<User> usersList = userService.allUsers();
        User user = userService.getUserByName(principal.getName()).get();
        Set<Role> roles = userService.allRoles();
        model.addAttribute("userList", usersList);
        model.addAttribute("user", user);
        model.addAttribute("newUser", new User());
        model.addAttribute("roles", roles);
        model.addAttribute("role", new Role());
        return "common_page";
    }

    @PostMapping()
    public String addUser(@ModelAttribute("newUser") User user, @ModelAttribute("role") Role role){
        Role role1 = new Role();
        Set<Role> set = new HashSet<>();
        if (userService.getUserByName(user.getUsername()).isEmpty()){

            role1.setRole(role.getRole());
            set.add(role1);
            set.add(role1);
            user.setRoles(set);
            userService.add(user);
        }
        return "redirect:/admin";
    }











    // проверить на null
    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable("id") long id, Model model){
        User user = userService.getById(id).get();
        Set<Role> set = userService.allRoles();
        model.addAttribute("user", user);
        model.addAttribute("roleSet",set);
        model.addAttribute("roleObj", new Role());

        return "adminPages/editPage";
    }
    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") User user , @ModelAttribute("roleObj") Role roleObj) {
        if (!user.getUsername().trim().isEmpty() && !user.getPassword().trim().isEmpty()
                && !user.getSurname().trim().isEmpty()) {
            Role role = new Role();
            role.setRole(roleObj.getRole());
            Set<Role> set = new HashSet<>();
            set.add(role);
            user.setRoles(set);
            userService.edit(user);
        }
        return "redirect:/admin";
    }
    //изменить название url
//    @GetMapping("/admin")
//    public String getUser(Model model){
//        model.addAttribute("newUser", new User());
//        model.addAttribute("roleObj", new Role());
//        Set<Role> set = userService.allRoles();
//        model.addAttribute("roleSet", set);
//        return "admin_page";
//    }
//
    //ПЕРЕДЕЛАТЬ ++













    // удалить по id через hql ++
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id")long id){
        userService.deleteUserById(id);

        return "redirect:/";
    }
}