package az.writhline.product.controller;

import az.writhline.product.Dto.ProductIdDto;
import az.writhline.product.Dto.ShoppingCartDto;
import az.writhline.product.Dto.ShoppingCartRequestDto;
import az.writhline.product.service.ShoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping")
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ShoppingService shoppingService;

    @PostMapping
    public ShoppingCartDto createShoppingCart(@RequestBody ShoppingCartRequestDto name) {

        return shoppingService.createShoppingCart(name);
    }

    @PostMapping("/{id}/product")
    public ShoppingCartDto addProductToCart(@PathVariable Long id, @RequestBody ProductIdDto productId) {
        return shoppingService.addProductToCart(id, productId.getProductId());
    }

    @DeleteMapping("/{id}/product/{productId}")
    public void removeProductFromCart(@PathVariable Long id, @PathVariable Long productId) {
         shoppingService.removeProductFromCart(id, productId);
    }

    @GetMapping("/{id}")
    public ShoppingCartDto getShoppingCartById(@PathVariable Long id) {
        return shoppingService.getShoppingCartById(id);
    }
}
