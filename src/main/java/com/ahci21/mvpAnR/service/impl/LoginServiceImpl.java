package com.ahci21.mvpAnR.service.impl;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ahci21.mvpAnR.dto.AccessTokenDto;
import com.ahci21.mvpAnR.dto.MapTokenDto;
import com.ahci21.mvpAnR.dto.TokenDto;
import com.ahci21.mvpAnR.dto.UserDto;
import com.ahci21.mvpAnR.entity.Permission;
import com.ahci21.mvpAnR.entity.Role;
import com.ahci21.mvpAnR.entity.User;
import com.ahci21.mvpAnR.repository.RoleRepository;
import com.ahci21.mvpAnR.repository.UserRepository;
import com.ahci21.mvpAnR.service.LoginService;
import com.ahci21.mvpAnR.util.LoginTokenUtil;

@Service
public class LoginServiceImpl implements LoginService{    
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private RoleRepository roleRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private LoginTokenUtil jwtUtil;
    
    @Override
    public  ResponseEntity<AccessTokenDto> login(UserDto user) {
        
        User found = new User();        
        Optional<User> userSearch = userRepository.findByUsername(user.getUsername());
        AccessTokenDto accessDto = new AccessTokenDto();
        accessDto.setMessage("Wrong Credentials");
        
        //Finding user name
        if (!userSearch.isPresent()) {
            //Finding email
            userSearch = userRepository.findByEmail(user.getUsername());
            if (!userSearch.isPresent()) {
                //Not found then doesn't exist
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(accessDto);
            }
        }
        
        //Saving search to actual user
        found = userSearch.get();        
        
        //Getting Permissions from Role
        Set<Permission> permSet = found.getRoles().iterator().next().getPermissions();
         
        
        //Validate password
        if (passwordEncoder.matches(user.getPassword(), found.getPassword())) {
            String token = jwtUtil.generateToken(1, found.getIduser(), jwtUtil.preparePermissions(permSet),60000);      
            accessDto.setMessage("Succesfull Login");
            accessDto.setToken(token);
            return ResponseEntity.ok(accessDto);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(accessDto);
        }     
        
    }

    @Override
    public ResponseEntity<String> register(User user) {
        
        Set<Role> roles = new HashSet<>();
        try {
            //Find basic Role
            Role r = roleRepository.findById(1).get();
            roles.add(r);
        } catch (Exception e) {
            return new ResponseEntity<String>("Base user role not found!", null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        
        

        try {       
            //Set user basic Role
            user.setRoles(roles);
            user.setPassword(passwordEncoder.encode(user.getPassword()));        
            userRepository.save(user);
        } catch (Exception e) {
            if(e.getCause() != null && e.getCause().getCause() instanceof SQLIntegrityConstraintViolationException) {
                SQLIntegrityConstraintViolationException sql_violation_exception = (SQLIntegrityConstraintViolationException) e.getCause().getCause() ;
                return new ResponseEntity<String>("Constraint Violation: " + sql_violation_exception.getMessage(), null, HttpStatus.CONFLICT);
            } else {
                return new ResponseEntity<String>("e.getMessage()", null, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        
        return new ResponseEntity<String>("User successfully saved to database.", null, HttpStatus.CREATED);
    }
    
    @Override 
    public ResponseEntity<AccessTokenDto> refresh(HttpServletRequest request){
        
        String authTokenHeader =  request.getHeader("Authorization");
        MapTokenDto loginDto = new MapTokenDto();
        AccessTokenDto accessDto = new AccessTokenDto();
        
        //Validate Token
        
        try {
            loginDto =  jwtUtil.validateToken(authTokenHeader.split(" ")[1]);
            accessDto.setMessage(loginDto.getMessage());
        } catch (NullPointerException e) {      
            accessDto.setMessage("No token was provided.");
            return ResponseEntity.badRequest().body(accessDto);
        }

        //Check Token state
        
        if (loginDto.getMessage().equals("Expired Token")) {
            String refreshToken = jwtUtil.generateRefreshToken(loginDto.getLoginToken().getId(), loginDto.getLoginToken().getIduser(), loginDto.getLoginToken().getIdpermission());
            accessDto.setToken(refreshToken);
            return ResponseEntity.created(null).body(accessDto);
        }else{
            return ResponseEntity.badRequest().body(accessDto);
        }
        

    }

    @Override
    public ResponseEntity<AccessTokenDto> validateToken(HttpServletRequest request) {

        AccessTokenDto accessDto = new AccessTokenDto();
        MapTokenDto mapDto = new MapTokenDto();
        
        try {
            mapDto = jwtUtil.validateToken(request.getHeader("Authorization").split(" ")[1]);
            accessDto.setMessage(mapDto.getMessage());
        } catch (NullPointerException e) {      
            accessDto.setMessage("No token was provided.");
            return ResponseEntity.badRequest().body(accessDto);
        }
        
        if (accessDto.getMessage().equals("Valid Token, refresh is not necessary")) {
            return ResponseEntity.ok(accessDto);
        }else{
            return ResponseEntity.badRequest().body(accessDto);
        }
        
      
        
    }
    
}
