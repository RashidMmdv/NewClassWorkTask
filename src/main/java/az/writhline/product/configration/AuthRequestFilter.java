package az.writhline.product.configration;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@Slf4j
@RequiredArgsConstructor
@Component
public class AuthRequestFilter extends OncePerRequestFilter {

    private final List<AuthService> authServices;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws IOException, ServletException {
        Optional<Authentication> authOptional = Optional.empty();
        for (AuthService authService : authServices) {
            authOptional = authOptional.or(() -> authService.getAuthentication(httpServletRequest));
            authOptional.ifPresent(auth -> SecurityContextHolder.getContext().setAuthentication(auth));
            if (authOptional.isPresent()) {
                break;
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }


}
