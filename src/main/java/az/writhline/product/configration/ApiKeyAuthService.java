package az.writhline.product.configration;

import az.writhline.product.model.Authority;
import az.writhline.product.model.User;
import az.writhline.product.repository.UserRepository;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiKeyAuthService implements AuthService {

    private final UserRepository userRepository;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("Authorization");
        if (header != null && header.startsWith("ApiKey ")) {
            String apiKey = header.substring(7);
            try {
                Optional<User> optionalUser = userRepository.findByApiKey(apiKey);

                return optionalUser.map(user -> getAuthentication(user.getAuthorities(), user));


            } catch (JwtException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }


    private Authentication getAuthentication(List<Authority> authorities, User  user) {

        List<String> list = authorities.stream().map(Authority::getAuthority).toList();

        JwtCredentials credentials = JwtCredentials.builder().authority(list).id(user.getId()).type("USERNAME_PASSWORD_AUTH").status("A").build();

      return    new UsernamePasswordAuthenticationToken(null, credentials, authorities);


    }
}
