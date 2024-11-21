package az.writhline.product.configration;


import az.writhline.product.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthRequestFilter authRequestFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {


        http.cors(cors-> cors.disable());
        http.csrf(csrf-> csrf.disable());

        http.authorizeHttpRequests(auth ->auth.requestMatchers("/test/hello").permitAll());
        http.authorizeHttpRequests(auth ->auth.requestMatchers("/user").permitAll());
        http.authorizeHttpRequests(auth ->auth.requestMatchers("/auth/login").permitAll());
        http.authorizeHttpRequests(auth ->auth.requestMatchers("/test/user").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name()));
        http.authorizeHttpRequests(auth ->auth.requestMatchers("/test/admin").hasAnyAuthority( Role.ADMIN.name()));
        http.addFilterBefore(authRequestFilter, UsernamePasswordAuthenticationFilter.class);
        http.httpBasic(Customizer.withDefaults());
        return http.build();

    }

}
