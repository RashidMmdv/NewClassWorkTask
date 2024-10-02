package az.writhline.product.controller;

import az.writhline.product.model.Caching;
import az.writhline.product.service.CachingServices;
import az.writhline.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/caching")
@RequiredArgsConstructor
public class CachingController {
    private final CachingServices cachingServices;

    @GetMapping("/{id}")
    public Caching getCaching(@PathVariable Long id) {
        return cachingServices.getCaching(id);
    }

}
