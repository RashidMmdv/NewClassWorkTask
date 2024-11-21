package az.writhline.product.configration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;
import java.util.Map;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Slf4j
@Component
@RequiredArgsConstructor
public final class BaseJwtService {

    private SecretKey key;

    @Value("${spring.security.jwt-secret-key}")
    private String jwtSecretKey;

    @PostConstruct
    public void init() {
        byte[] keyBytes;
        keyBytes = Decoders.BASE64.decode(jwtSecretKey);
        key = Keys.hmacShaKeyFor(keyBytes);
    }

    public Jws<Claims> parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);
    }

    public String createToken(Map<String, Object> claims,String username) {
        return Jwts.builder()
                .claims(claims)
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(key)
                .subject(username)
                .compact();
    }

}
