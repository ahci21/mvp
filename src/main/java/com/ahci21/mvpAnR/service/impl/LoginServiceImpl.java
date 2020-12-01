package com.ahci21.mvpAnR.service.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ahci21.mvpAnR.dto.TokenDto;
import com.ahci21.mvpAnR.dto.UserDto;
import com.ahci21.mvpAnR.entity.User;
import com.ahci21.mvpAnR.repository.RoleRepository;
import com.ahci21.mvpAnR.repository.UserRepository;
import com.ahci21.mvpAnR.service.LoginService;
import com.ahci21.mvpAnR.util.TokenUtil;
import com.ahci21.mvpAnR.entity.Role;

@Service
public class LoginServiceImpl implements LoginService{    
    
    @Autowired
    TokenUtil jwtUtil;
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    RoleRepository roleRepository;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
    @Override
    public ResponseEntity<String> login(UserDto user) {
        
        User found =   userRepository.findByUsername(user.getUsername()).get();
        
        System.out.println(found.toString());
        
        String token = jwtUtil.generateToken(1, found.getIduser(), 50000);
        
        return new ResponseEntity<String>("Wrong Credentials: "+token, null, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<String> register(User user) {
        Set<com.ahci21.mvpAnR.entity.Role> roles = new HashSet<>();
        Role r = roleRepository.findAll().get(0);
        
        System.out.println(r.toString());
        
        roles.add(r);
        
        user.setRoles(roles);
        user.setPassword(passwordEncoder.encode(user.getPassword()));      
        
        try {
            userRepository.save(user);
            System.out.print(user.toString());            
            return new ResponseEntity<String>("Answers successfully saved to database.", null, HttpStatus.CREATED);
        } catch (Exception e) { 
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                SQLIntegrityConstraintViolationException sql_violation_exception = (SQLIntegrityConstraintViolationException) e.getCause().getCause() ;
                return new ResponseEntity<String>("Constraint Violation: " + sql_violation_exception.getMessage(), null, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<String>("e.getMessage()", null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
        
    }

    @Override
    public ResponseEntity<String> refreshToken() {
        return new ResponseEntity<String>("Wrong Credentials", null, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<String> validateToken(TokenDto token) {
        return new ResponseEntity<String>("Wrong Credentials"+jwtUtil.validateToken(token.getToken()), null, HttpStatus.UNAUTHORIZED);
        
    }

}
