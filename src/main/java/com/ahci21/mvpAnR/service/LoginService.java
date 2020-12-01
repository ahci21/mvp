package com.ahci21.mvpAnR.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahci21.mvpAnR.dto.TokenDto;
import com.ahci21.mvpAnR.dto.UserDto;
import com.ahci21.mvpAnR.entity.User;


@Service
public interface LoginService {

    public ResponseEntity<String> login(UserDto user);

    public ResponseEntity<String> register(User user);
    
    public ResponseEntity<String> refreshToken();

    ResponseEntity<String> validateToken(TokenDto token);
    
}
