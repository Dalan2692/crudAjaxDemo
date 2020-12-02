package edu.muzraev.application.rest;

import edu.muzraev.application.domains.AuthenticationRequestDTO;

import edu.muzraev.application.domains.AuthenticationResponseDTO;
import edu.muzraev.application.domains.Role;
import edu.muzraev.application.domains.User;
import edu.muzraev.application.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/api/v1/auth")
public class AuthRestControllerV1  {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;


    @Autowired
    public AuthRestControllerV1(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }


    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request )   {
        try {

            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
            User user = userService.getUserByName(request.getUsername()).orElseThrow(
                    () -> new UsernameNotFoundException("user doesn't found"));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            AuthenticationResponseDTO response = new AuthenticationResponseDTO();
            response.setId(user.getId());
            response.setUsername(user.getUsername());
            response.setFirstName(user.getFirstName());
            response.setLastName(user.getLastName());
//            response.setRole(user.getRoles().stream().map(Role::getRole).findFirst().orElseThrow());
            response.setRoles(user.getRoles());
            response.setAge(user.getAge());

            return ResponseEntity.ok(response);

        } catch (AuthenticationException e){

            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);

        }

    }



    @PostMapping("/logout")
    public void logout(HttpServletRequest httpServletRequest , HttpServletResponse httpServletResponse) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(httpServletRequest, httpServletResponse, null);
    }

}
