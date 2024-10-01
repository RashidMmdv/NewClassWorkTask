package az.writhline.product.service;

import az.writhline.product.Dto.ShoppingCartDto;
import az.writhline.product.Dto.ShoppingCartRequestDto;
import az.writhline.product.model.ProductsEntity;
import az.writhline.product.model.ShoppingCarts;
import az.writhline.product.repository.ProductRepository;
import az.writhline.product.repository.ShoppingCartsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingServiceImp implements ShoppingService {

    private final ShoppingCartsRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;



    @Override
    public ShoppingCartDto createShoppingCart(ShoppingCartRequestDto name) {
        ShoppingCarts cart = new ShoppingCarts();
        cart.setName(name.getName());
        ShoppingCarts savedCart = shoppingCartRepository.save(cart);
        return modelMapper.map(savedCart, ShoppingCartDto.class);
    }

    @Override
    public ShoppingCartDto addProductToCart(Long cartId, Long productId) {

        ProductsEntity product = productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("Product not found!"));
        ShoppingCarts carts = shoppingCartRepository.findById(cartId)
                .orElseThrow(()->new RuntimeException("Cart not found!"));

        log.info("Product is {}",product);
        log.info("Cart is {}",carts);
        carts.getProducts().add(product);

        ShoppingCarts saveCart = shoppingCartRepository.save(carts);
        return modelMapper.map(saveCart, ShoppingCartDto.class);
    }

    @Override
    public void removeProductFromCart(Long id,Long productId) {
        ShoppingCarts carts = shoppingCartRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Cart not found!"));
        ProductsEntity product = productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("Product not found!"));
        carts.getProducts().remove(product);
        shoppingCartRepository.save(carts);


    }

    @Override
    public ShoppingCarts getShoppingCartById(Long id) {
        ShoppingCarts shoppingCarts = shoppingCartRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Shopping cart not found"));
        log.info("Cart is {}",shoppingCarts);
        return shoppingCarts;
    }

}
