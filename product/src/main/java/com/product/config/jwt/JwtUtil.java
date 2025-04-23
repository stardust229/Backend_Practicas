package com.product.config.jwt;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

import org.springframework.stereotype.Component;

import com.product.api.entity.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "ContrasenaSuperSeguraContrasenaSuperSeguraContrasenaSuperSeguraContrasenaSuperSeguraContrasenaSuperSegura";

    //@Value("${system.hostname}")
    private String hostname="localhost:8080";
    
    public String generateToken(User usuario) {
        return Jwts.builder()
        		.claim("id", usuario.getId())
        		.claim("email", usuario.getEmail())
        		.claim("roles", usuario.getRoles())
        		.setIssuer("http://" + hostname)
        		.setSubject(usuario.getUsername())
                .setAudience("http://" + hostname)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

	public List<HashMap<String, String>> extractPermisos(String token) {
         return extractClaims(token).get("roles", List.class);
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(extractClaims(token));
    }
}