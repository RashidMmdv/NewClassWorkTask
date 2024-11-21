package az.writhline.product.configration;

import az.writhline.product.model.Authority;
import az.writhline.product.model.User;
import az.writhline.product.repository.UserRepository;
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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsernamePasswordAuthService implements AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Optional<Authentication> getAuthentication(HttpServletRequest httpServletRequest) {
        String header = httpServletRequest.getHeader("Authorization");
        System.err.println(header);
        if (header != null && header.startsWith("Basic ")) {
            String basicToken = header.substring(6);
            try {
                String usernameAndPass = new String(Base64.getDecoder().decode(basicToken));
                String[] split = usernameAndPass.split(":");
                String username = split[0];
                String password = split[1];
                User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
                if (!bCryptPasswordEncoder.matches(password, user.getPassword())) {
                    return Optional.empty();
                }
               return   Optional.of(getAuthentication(user.getAuthorities(),user));


            } catch (JwtException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }


    private Authentication getAuthentication(List<Authority> authorities,User  user) {

        List<String> list = authorities.stream().map(Authority::getAuthority).toList();

        JwtCredentials credentials = JwtCredentials.builder().authority(list).id(user.getId()).type("USERNAME_PASSWORD_AUTH").status("A").build();

        return new UsernamePasswordAuthenticationToken(null, credentials, authorities);
    }
}
