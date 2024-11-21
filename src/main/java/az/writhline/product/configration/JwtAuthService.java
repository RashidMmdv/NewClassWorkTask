package az.writhline.product.configration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtAuthService implements AuthService {

    private final BaseJwtService baseJwtService;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            try {
                Jws<Claims> claimsJws = baseJwtService.parseToken(token);
                return Optional.of(getAuthentication(claimsJws.getPayload()));
            } catch (JwtException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }


    private Authentication getAuthentication(Claims claims) {
        List<String> roles = (List) claims.get("authority");
        List<GrantedAuthority> authorityList;
        authorityList = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

        JwtCredentials credentials = new ModelMapper().map(claims, JwtCredentials.class);

        return new UsernamePasswordAuthenticationToken(null, credentials, authorityList);
    }
}
