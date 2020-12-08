package com.ahci21.mvpAnR.dto;

public class AccessTokenDto {
    private String Message;
    private String Token;
    public String getMessage() {
        return Message;
    }
    public void setMessage(String message) {
        this.Message = message;
    }
    public String getToken() {
        return Token;
    }
    public void setToken(String refreshToken) {
        this.Token = refreshToken;
    }
    
    
}
