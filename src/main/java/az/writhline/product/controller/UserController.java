package az.writhline.product.controller;


import az.writhline.product.Dto.UserDto;
import az.writhline.product.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void createUser(@RequestBody UserDto userDto) {

        userService.createUser(userDto);

    }

}
