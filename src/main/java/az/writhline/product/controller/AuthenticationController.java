package az.writhline.product.controller;

import az.writhline.product.Dto.TokenResponseDto;
import az.writhline.product.Dto.UserDto;
import az.writhline.product.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;


    @PostMapping("/login")
    public TokenResponseDto createUser(@RequestBody UserDto userDto) {

        return userService.login(userDto);

    }
}
