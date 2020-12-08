package com.ahci21.mvpAnR.dto;

import com.ahci21.mvpAnR.entity.LoginToken;

public class MapTokenDto {
    private LoginToken loginToken;
    
    private String message;

    public LoginToken getLoginToken() {
        return loginToken;
    }

    public void setLoginToken(LoginToken loginToken) {
        this.loginToken = loginToken;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
