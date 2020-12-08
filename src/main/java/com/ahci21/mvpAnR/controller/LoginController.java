package com.ahci21.mvpAnR.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ahci21.mvpAnR.dto.AccessTokenDto;
import com.ahci21.mvpAnR.dto.TokenDto;
import com.ahci21.mvpAnR.dto.UserDto;
import com.ahci21.mvpAnR.entity.Persona;
import com.ahci21.mvpAnR.entity.User;
import com.ahci21.mvpAnR.service.impl.LoginServiceImpl;
import com.ahci21.mvpAnR.service.impl.PersonaServiceImpl;

@RestController
public class LoginController {
    
    @Autowired
    LoginServiceImpl loginServiceImpl;
    
    @Autowired
    PersonaServiceImpl personaService;
    
    @CrossOrigin
    @PostMapping("/login")
    public ResponseEntity<AccessTokenDto> login(@RequestBody UserDto user) {
        return loginServiceImpl.login(user);
    }
    
    @CrossOrigin
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        return loginServiceImpl.register(user);
    }
    
    @CrossOrigin
    @PostMapping("/token/refresh")
    public ResponseEntity<AccessTokenDto> refresh(HttpServletRequest request) {
        return loginServiceImpl.refresh(request);
    }
    
    @CrossOrigin
    @PostMapping("/token/validate")
    public ResponseEntity<AccessTokenDto> validateToken(HttpServletRequest request) {
        return loginServiceImpl.validateToken(request);
    }
    
   
    
}
