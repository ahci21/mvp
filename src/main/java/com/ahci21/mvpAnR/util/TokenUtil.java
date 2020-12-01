package com.ahci21.mvpAnR.util;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ahci21.mvpAnR.entity.Token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public int getIdFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (Integer) claims.get("idToken");
    }

    public int getUserIdFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (Integer) claims.get("iduser");
    }

//    public int getRoleFromToken(String token) {
//        Claims claims = getAllClaimsFromToken(token);
//        return (Integer) claims.get("idrole");
//    }

    public Date getExpirationDateFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return claims.getExpiration();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(secret))
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    public String generateToken(int idToken, int idUser) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("idToken", idToken);
        claims.put("iduser", idUser);

        return doGenerateToken(claims);
    }
    
    public String generateToken(int idToken, int idUser, long expires) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("idToken", idToken);
        claims.put("iduser", idUser);

        return doGenerateToken(claims, expires);
    }

    private String doGenerateToken(Map<String, Object> claims) {
        
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(secret);
        
        Key signingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());

        // Set header for jwt
        Map<String, Object> header = new HashMap<>();
        header.put("alg", signatureAlgorithm.getJcaName());
        header.put("typ", "JWT");

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(signatureAlgorithm, signingKey).compact();
    }
    
    private String doGenerateToken(Map<String, Object> claims, long expires) {
        
        byte[] secretBytes = DatatypeConverter.parseBase64Binary(secret);
        Key signingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());

        // Set header for jwt
        Map<String, Object> header = new HashMap<>();
        header.put("alg", signatureAlgorithm.getJcaName());
        header.put("typ", "JWT");

        return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expires))
                .signWith(signatureAlgorithm, signingKey).compact();
    }


    // validate token
    public Token validateToken(String token) {

        Token tokenSurvey = new Token();

        try {
            //if there is a problem with the signature while reading claims, 
            //a SignatureException arise
            tokenSurvey.setId(getIdFromToken(token));
            tokenSurvey.setIduser(getUserIdFromToken(token));

            return tokenSurvey;

        } catch (JwtException e) {
            return null;
        }
    }
}