package az.writhline.product.controller;

import az.writhline.product.Dto.ResponseDto;
import az.writhline.product.model.ProductsEntity;
import az.writhline.product.repository.ProductRepository;
import az.writhline.product.service.ProductService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {

    private final ProductRepository productRepository;

    @GetMapping("/1")
    @SneakyThrows
    @Transactional
    public ResponseDto getProduct(){
        ProductsEntity product = productRepository.findById(1L).get();
        Thread.sleep(300);
        return ResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }
    @GetMapping("/2")
    public ResponseDto getProductFromDb(){
        ProductsEntity product = productRepository.findById(3L).get();
        return ResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .build();
    }

    @GetMapping("/hello")
    public String getHello(){

        return "Hello World";
    }
    @GetMapping("/user")
    public String getUser(){

        return "User";
    }
    @GetMapping("/admin")
    public String getAdmin(){

        return "Admin";
    }
}
