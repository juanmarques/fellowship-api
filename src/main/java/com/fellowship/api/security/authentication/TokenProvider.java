package com.fellowship.api.security.authentication;

import com.fellowship.api.security.authentication.model.UserPrincipal;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author Juan Marques
 */
@Log4j2
@Service
public class TokenProvider {

    //Token will be valid for 24h
    public static final long JWT_TOKEN_VALIDITY = 24 * 60 * 60;

    private final String secret = "U9/DYAYIfxEvcWuJD5+knPbBsOcX4hDCiYIuXDbmrsXJ4GR14r+AieYxhqm0M6X2}";

    public String createToken(Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        Date expiryDate = new Date(new Date().getTime() + JWT_TOKEN_VALIDITY * 1000);

        return Jwts.builder()
                .setSubject(userPrincipal.getId())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public String getUserIdFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(authToken);
            return true;
        }
        catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        }
        catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        }
        catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        }
        catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
}
