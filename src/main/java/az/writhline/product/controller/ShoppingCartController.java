package az.writhline.product.controller;

import az.writhline.product.Dto.ShoppingCartDto;
import az.writhline.product.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shopping-carts")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PostMapping
    public ResponseEntity<ShoppingCartDto> createShoppingCart(@RequestBody String name) {
        return ResponseEntity.ok(shoppingCartService.createShoppingCart(name));
    }

    @PostMapping("/{id}/product")
    public ResponseEntity<ShoppingCartDto> addProductToCart(@PathVariable Long id, @RequestBody Long productId) {
        return ResponseEntity.ok(shoppingCartService.addProductToCart(id, productId));
    }

    @DeleteMapping("/{id}/product/{productId}")
    public ResponseEntity<ShoppingCartDto> removeProductFromCart(@PathVariable Long id, @PathVariable Long productId) {
        return ResponseEntity.ok(shoppingCartService.removeProductFromCart(id, productId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCartDto> getShoppingCartById(@PathVariable Long id) {
        return ResponseEntity.ok(shoppingCartService.getShoppingCartById(id));
    }
}
