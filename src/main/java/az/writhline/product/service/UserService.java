package az.writhline.product.service;


import az.writhline.product.Dto.TokenResponseDto;
import az.writhline.product.Dto.UserDto;
import az.writhline.product.configration.BaseJwtService;
import az.writhline.product.mapper.UserMapper;
import az.writhline.product.model.Authority;
import az.writhline.product.model.Role;
import az.writhline.product.model.User;
import az.writhline.product.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import static az.writhline.product.model.Role.USER;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final BaseJwtService baseJwtService;


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }


    public void createUser(UserDto userDto) {
        userRepository.findByUsername(userDto.getUserName()).ifPresentOrElse(
                user -> {
                    throw new IllegalArgumentException("Username already exists!");
                },
                ()->{
                    User user =User.builder()
                            .username(userDto.getUserName())
                            .password(passwordEncoder.encode(userDto.getPassword()))
                            .authorities(List.of(Authority.builder()
                                            .authority(USER)
                                    .build()))
                            .build();
                    userRepository.save(user);
                }
        );
    }

    public TokenResponseDto login(UserDto userDto) {
        User user = userRepository.findByUsername(userDto.getUserName()).orElseThrow(() -> new UsernameNotFoundException(userDto.getUserName()));

        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Wrong password!");
        }

        Map<String, Object> claims = buildClaims(user);

        String apiKey = getApiKey();
        user.setApiKey(apiKey);
        userRepository.save(user);

        String token = baseJwtService.createToken(claims, user.getUsername());
        return new TokenResponseDto(token,apiKey);

    }

    private Map<String,Object> buildClaims(User user){
        Map<String,Object> claims = new HashMap<>();
        List<String> authority = user.getAuthorities().stream().map(Authority::getAuthority).toList();
        claims.put("authority", authority);
        return claims;
    }
    private String getApiKey(){
        return UUID.randomUUID().toString();
    }
}
