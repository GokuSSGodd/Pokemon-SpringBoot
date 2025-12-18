package org.example.jpademo.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.jpademo.dto.UserSignupDto;
import org.example.jpademo.service.JDBCUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

    private final JDBCUserService jdbcUserService;

    @GetMapping("/")
    public String getHomePage(){
        return "home";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @GetMapping("/logout")
    public String getLogoutPage() {
        return "logout";
    }

    @GetMapping("/signup")
    public String getSignupPage() {
        return "signup";
    }

    @PostMapping("/signup")
    public String postSignupPage(@RequestBody UserSignupDto userSignupDto){
        var password = userSignupDto.password();
        var currentPassword = userSignupDto.confirmPassword();
        if(!password.equals(currentPassword)){
            return "unauthorized";
        }
        var username = userSignupDto.username();
        jdbcUserService.addUser(username, password);
        return "login";
    }

}
