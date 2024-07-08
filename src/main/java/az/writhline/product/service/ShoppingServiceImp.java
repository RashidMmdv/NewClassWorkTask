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
        Optional<ProductsEntity> products = productRepository.findById(productId);
        Optional<ShoppingCarts> carts = shoppingCartRepository.findById(cartId);

//        log.info("Product is {}",products);
//        log.info("Cart is {}",carts);

        ShoppingCarts shoppingCarts = carts.get();
        ProductsEntity productsEntity = products.get();
        shoppingCarts.getProducts().add(productsEntity);

        ShoppingCarts saveCart = shoppingCartRepository.save(shoppingCarts);
        return modelMapper.map(saveCart, ShoppingCartDto.class);
    }

    @Override
    public void removeProductFromCart(Long id, Long productId) {
        shoppingCartRepository.deleteById(id);
        productRepository.deleteById(productId);

    }

    @Override
    public ShoppingCartDto getShoppingCartById(Long id) {
        ProductsEntity products = shoppingCartRepository.findByProducts();
        log.info("Product is {}",products);
        return null;
    }

}
