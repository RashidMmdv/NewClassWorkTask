package az.writhline.product.service;

import az.writhline.product.Dto.ShoppingCartDto;
import az.writhline.product.Dto.ShoppingCartRequestDto;

public interface ShoppingService {
    ShoppingCartDto createShoppingCart(ShoppingCartRequestDto name);
    public ShoppingCartDto addProductToCart(Long cartId, Long productId);

    void removeProductFromCart(Long id, Long productId);

    ShoppingCartDto getShoppingCartById(Long id);
}
