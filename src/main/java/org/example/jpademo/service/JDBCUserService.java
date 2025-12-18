package org.example.jpademo.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Service
@RequiredArgsConstructor
public class JDBCUserService {

    private final JdbcUserDetailsManager jdbcUserDetailManager;
    private final PasswordEncoder passwordEncoder;
    private final RestClient.Builder builder;

    public boolean userExists(String username){
        return jdbcUserDetailManager.userExists(username);
    }

    public void addUser(String username, String password){
        if (userExists(username)){
            return;
        }
        var encryptedPassword = passwordEncoder.encode(password);
        var user = User.builder()
                .username(username)
                .password(encryptedPassword)
                .roles("user")
                .build();
        jdbcUserDetailManager.createUser(user);
    }
}
