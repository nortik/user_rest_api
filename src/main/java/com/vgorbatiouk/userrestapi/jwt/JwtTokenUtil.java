package com.vgorbatiouk.userrestapi.jwt;

import java.util.Date;

import com.vgorbatiouk.userrestapi.dto.UserDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil {
    private static final long EXPIRE_DURATION = 24 * 60 * 60 * 1000; // 24 hour

    //@Value("${app.jwt.secret}")
    @Value("abcdefghijklmnOPQRSTUVWXYZ")
    //app.jwt.secret=abcdefghijklmnOPQRSTUVWXYZ
    private String SECRET_KEY;

    public String generateAccessToken(UserDTO user) {
        return Jwts.builder()
            //.setSubject(String.format("%s,%s", user.getId(), user.getEmail()))
            .setSubject(String.format("%s,%s", user.getName(), user.getEmail()))
            .setIssuer("CodeJava")
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_DURATION))
            .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
            .compact();

    }
}