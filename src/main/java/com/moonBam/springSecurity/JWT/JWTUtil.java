package com.moonBam.springSecurity.JWT;

import java.security.Key;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureAlgorithm;

@Component
public class JWTUtil {

    private SecretKey key;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {
		byte[] byteSecretKey = Decoders.BASE64.decode(secret);
        key = Keys.hmacShaKeyFor(byteSecretKey);
    }

    public String getUsername(String token) {
//12.3  return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().get("username", String.class);
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().get("username", String.class);
    }

    public String getRole(String token) {
//12.3  return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody().get("role", String.class);
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public Boolean isExpired(String token) {
//12.3  return Jwts.parser().setSigningKey(key).build().parseClaimsJws(token).getBody().getExpiration().before(new Date());
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(String username, String role, Long expiredMs) {
    	System.out.println(username + " : " + role + " : " + expiredMs);
    	return Jwts.builder()
                .claim("username", username)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(key)
                .compact();
    }
}