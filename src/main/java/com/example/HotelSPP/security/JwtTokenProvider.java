package com.example.HotelSPP.security;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;


@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private long jwtExpirationInMs;

    private static final String ROLES_CLAIM_NAME = "roles";

    //TODO Expiration date
    public String generateToken(Authentication auth) {
        return generateTokenBody(auth, jwtExpirationInMs).compact();
    }

    public String generateToken(Authentication auth, long lifeInMs) {
        return generateTokenBody(auth, lifeInMs).compact();
    }

    public Long getUserIdFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        return Long.parseLong(claims.getSubject());
    }

    Collection<GrantedAuthority> getAuthoritiesFromJWT(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
        @SuppressWarnings("unchecked")
        List<String> grantedAuthorities = claims.get(ROLES_CLAIM_NAME, List.class);
        return grantedAuthorities == null ? Collections.emptyList() : grantedAuthorities.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException ex) {
            log.warn(String.format("Invalid JWT signature: %s", authToken));
        } catch (MalformedJwtException ex) {
            log.warn(String.format("Invalid JWT token: %s", authToken));
        } catch (ExpiredJwtException ex) {
            log.warn(String.format("Expired JWT token: %s", authToken));
        } catch (UnsupportedJwtException ex) {
            log.warn(String.format("Unsupported JWT token: %s", authToken));
        } catch (IllegalArgumentException ex) {
            log.warn(String.format("JWT claims string is empty: %s", authToken));
        }
        return false;
    }

    private JwtBuilder generateTokenBody(Authentication authentication, long lifeInMs) {
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + lifeInMs);
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return Jwts.builder()
                .setSubject(Long.toString(userPrincipal.getUser().getId()))
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .claim(ROLES_CLAIM_NAME, roles);
    }

    public Optional<String> getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }
}