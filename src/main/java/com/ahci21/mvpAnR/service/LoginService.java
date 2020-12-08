package com.ahci21.mvpAnR.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ahci21.mvpAnR.dto.AccessTokenDto;
import com.ahci21.mvpAnR.dto.TokenDto;
import com.ahci21.mvpAnR.dto.UserDto;
import com.ahci21.mvpAnR.entity.User;

@Service
public interface LoginService {

    public ResponseEntity<AccessTokenDto> login(UserDto user);

    public ResponseEntity<String> register(User user);
    
    public ResponseEntity<AccessTokenDto> refresh(HttpServletRequest request);    

    public ResponseEntity<AccessTokenDto> validateToken(HttpServletRequest request);
    
}
