package pe.ucv.ucvbackend.domain.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECRET_KEY = "2e48f8da20ccb954e501ae0c078a76e1b727cd7895f3f514d8da394105117578";

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> extraClaims = new HashMap<>();

        //  Usar reflection para obtener el EMAIL
        try {
            Class<?> userClass = userDetails.getClass();

            // Intentar obtener EMAIL (campo crítico)
            Object email = userClass.getMethod("getEmail").invoke(userDetails);
            if (email != null) {
                extraClaims.put("email", email);
            }

            // Intentar obtener userId
            Object userId = userClass.getMethod("getUserId").invoke(userDetails);
            if (userId != null) {
                extraClaims.put("userId", userId);
            }

            // Intentar obtener username (nickname)
            Object username = userClass.getMethod("getUsername").invoke(userDetails);
            if (username != null) {
                extraClaims.put("username", username);
            }

            // Intentar obtener role
            Object role = userClass.getMethod("getUserRole").invoke(userDetails);
            if (role != null) {
                extraClaims.put("role", role.toString());
            }

        } catch (Exception e) {
            System.err.println("Error adding extra claims to JWT: " + e.getMessage());
        }

        return generateToken(extraClaims, userDetails);
    }

    public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        // Usar EMAIL como subject
        String subject = userDetails.getUsername();

        try {
            // Intentar obtener el email específicamente
            Object email = userDetails.getClass().getMethod("getEmail").invoke(userDetails);
            if (email != null) {
                subject = (String) email;
            }
        } catch (Exception e) {
            // Si falla, usar el username por defecto
            System.err.println("Could not get email, using username as subject: " + e.getMessage());
        }

        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 24 * 60))
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
    }


    public String getUsername(String token) {
        return getClaim(token, Claims::getSubject);
    }

    public <T> T getClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims getAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean validateToken(String jwt, UserDetails userDetails) {
        final String username = getUsername(jwt);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(jwt));
    }

    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    private Date getExpiration(String token) {
        return getClaim(token, Claims::getExpiration);
    }
}