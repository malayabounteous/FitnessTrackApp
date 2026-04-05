package com.fitness.app.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${app.jwt.secret}")
    private String  secret;

    private Key key= Keys.hmacShaKeyFor(secret.getBytes());

    public String generateToken(String email,Long userId,String name)
    {
        return Jwts.builder().setSubject(email)
                .claim("userId",userId)
                .claim("name",name)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+1000*60*60*10))
                .signWith(key)
                .compact();
    }
    public String extractEmail(String token)
    {
        return Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token).getBody().getSubject();
    }
}
