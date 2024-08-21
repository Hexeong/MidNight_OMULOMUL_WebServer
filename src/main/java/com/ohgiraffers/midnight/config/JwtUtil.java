package com.ohgiraffers.midnight.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {
    private String SECRET_KEY = "MmI3ZTE1MTYyOGFkZTJhNmFiZjcxNTg4ODA5Y2Y0ZjNj"; // 비밀 키
    private long EXPIRATION_TIME = 1000 * 60 * 60; // 1시간

    // JWT 생성
    public String generateToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, username);
    }

    // JWT 생성 로직
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // JWT 검증
    public boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }

    // 사용자 이름 추출
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 만료 여부 확인
    private boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // Claims 추출
    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    // JWT 만료시키기
    public String invalidateToken(String token) {
        // Claims를 추출하여 만료 시간을 현재 시간으로 설정
        Claims claims = extractAllClaims(token);
        claims.setExpiration(new Date(System.currentTimeMillis())); // 현재 시간으로 만료 시간 설정

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(claims.getSubject())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis())) // 즉시 만료
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}