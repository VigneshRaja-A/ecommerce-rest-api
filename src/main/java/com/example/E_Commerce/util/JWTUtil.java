package com.example.E_Commerce.util;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {
   @Value("${JWT_Secret}")
    private String secret;
    private SecretKey secretKey;

    @PostConstruct
    public void key()
    {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }
    public String generateJwtToken(String userName)
    {
        return Jwts.builder()
                .subject(userName)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*60))
                .signWith(this.secretKey)
                .compact();

    }

    public Claims extractClaims(String token)
    {
        return Jwts.parser()
                .verifyWith(this.secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String extractUserName(String token)
    {
       return extractClaims(token).getSubject();
    }

    public boolean validateToken(String userName,String token, UserDetails userDetails)
    {
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token)
    {
                return extractClaims(token).getExpiration().before(new Date());
    }
}
