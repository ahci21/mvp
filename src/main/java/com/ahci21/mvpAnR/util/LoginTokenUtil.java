package com.ahci21.mvpAnR.util;

import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ahci21.mvpAnR.dto.MapTokenDto;
import com.ahci21.mvpAnR.entity.LoginToken;
import com.ahci21.mvpAnR.entity.Permission;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

@Component
public class LoginTokenUtil {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private long expiration;
    @Value("${jwt.refreshExpiration}")
    private long refreshExpiration;

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    public int getIdFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (Integer) claims.get("idToken");
    }

    public int getUserIdFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return (Integer) claims.get("idUser");
    }

    public int[] getPermissionFromToken(String token) {
        Claims claims = getAllClaimsFromToken(token);
        return  preparePermissionFromList((List<Integer>)claims.get("idPermissions"));
    }

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

    public String generateToken(int idToken, int idUser, int[] idPermissions) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("idToken", idToken);
        claims.put("idUser", idUser);
        claims.put("idPermissions", idPermissions);

        return doGenerateToken(claims);
    }
    
    public String generateRefreshToken(int idToken, int idUser, int[] idPermissions) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("idToken", idToken);
        claims.put("idUser", idUser);
        claims.put("idPermissions", idPermissions);

        return doGenerateRefreshToken(claims);
    }
    
    public String generateToken(int idToken, int idUser, int[] idPermissions, long expires) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("idToken", idToken);
        claims.put("idUser", idUser);
        claims.put("idPermissions", idPermissions);

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
    
    public String doGenerateRefreshToken(Map<String, Object> claims) {
        
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
                .setExpiration(new Date(System.currentTimeMillis() + refreshExpiration))
                .signWith(signatureAlgorithm, signingKey).compact();
    }


    // validate token
    public MapTokenDto validateToken(String token) {

        MapTokenDto tokenDto = new MapTokenDto();
        
        tokenDto.setLoginToken(new LoginToken());

        try {
            //if there is a problem with the signature while reading claims, 
            //a SignatureException arise
            tokenDto.getLoginToken().setId(getIdFromToken(token));
            tokenDto.getLoginToken().setIduser(getUserIdFromToken(token));
            tokenDto.getLoginToken().setIdpermission(getPermissionFromToken(token));
            tokenDto.setMessage("Valid Token, refresh is not necessary");
            return tokenDto;

        }catch (SignatureException e) {
            tokenDto.setMessage("Not Valid Token");
            return tokenDto;
        }catch (ExpiredJwtException ex) {
            tokenDto.setMessage("Expired Token");
            tokenDto.getLoginToken().setId((int)ex.getClaims().get("idToken"));
            tokenDto.getLoginToken().setIduser((int)ex.getClaims().get("idUser"));
            tokenDto.getLoginToken().setIdpermission((preparePermissionFromList((List<Integer>)ex.getClaims().get("idPermissions"))));
            return tokenDto;
        }catch(MalformedJwtException mex) {
            tokenDto.setMessage("Malformed JWT");
            return tokenDto;
        }
    }
    

    
    public int[] preparePermissions(Set<Permission> permSet){

        List<Integer> permList = new ArrayList<Integer>();
        for (Iterator<Permission> it = permSet.iterator(); it.hasNext();) {
            permList.add( it.next().getIdpermission());
        }
        
        int[] permArray = new int[permList.size()];
        for (int i = 0; i < permList.size(); i++) {
            permArray[i] = permList.get(i);
        }
        
        return permArray;
    }
    
    public int[] preparePermissionFromList(List<Integer> permList){

        int[] permArray = new int[permList.size()];
        for (int i = 0; i < permList.size(); i++) {
            permArray[i] = permList.get(i);
        }
        
        return permArray;
    }
    
}