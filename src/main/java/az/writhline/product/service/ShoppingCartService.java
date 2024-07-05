package az.writhline.product.service;

import az.writhline.product.Dto.ShoppingCartDto;
import az.writhline.product.model.ShoppingCarts;
import org.springframework.http.ResponseEntity;

public interface ShoppingCartService {
    ShoppingCartDto createShoppingCart(String name);
    ShoppingCartDto addProductToCart(Long cartId, Long productId);
    ShoppingCartDto removeProductFromCart(Long cartId, Long productId);
    ShoppingCartDto getShoppingCartById(Long id);
}
