package edu.muzraev.application.domains;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Set;

@Data
public class AuthenticationResponseDTO {
    private Long id;
    private String username;
    private Set<Role> roles;
    private int age;
    private String firstName;
    private String lastName;


}
