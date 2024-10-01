package az.writhline.product.service;

import az.writhline.product.Dto.ShoppingCartDto;
import az.writhline.product.Dto.ShoppingCartRequestDto;
import az.writhline.product.model.ShoppingCarts;

public interface ShoppingService {
    ShoppingCartDto createShoppingCart(ShoppingCartRequestDto name);
    public ShoppingCartDto addProductToCart(Long cartId, Long productId);

    void removeProductFromCart(Long id, Long productId);

    ShoppingCarts getShoppingCartById(Long id);
}
