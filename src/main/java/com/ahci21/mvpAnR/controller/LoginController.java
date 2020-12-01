package com.ahci21.mvpAnR.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahci21.mvpAnR.dto.TokenDto;
import com.ahci21.mvpAnR.dto.UserDto;
import com.ahci21.mvpAnR.entity.User;
import com.ahci21.mvpAnR.service.impl.LoginServiceImpl;


@RestController
public class LoginController {
    
    @Autowired
    LoginServiceImpl loginServiceImpl;
    
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserDto user) {
        return loginServiceImpl.login(user);
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return loginServiceImpl.register(user);
    }
    
    @PostMapping("/refresh")
    public ResponseEntity<String> refreshToken(@RequestBody User user) {
        return loginServiceImpl.refreshToken();
    }
    
    @PreAuthorize("authenticaded")
    @PostMapping("/validattoken")
    public ResponseEntity<String> validateToken(@RequestBody TokenDto token) {
        return loginServiceImpl.validateToken(token);
    }
    
}
