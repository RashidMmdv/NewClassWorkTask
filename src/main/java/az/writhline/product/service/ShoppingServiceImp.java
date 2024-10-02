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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShoppingServiceImp implements ShoppingService {

    private final ShoppingCartsRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final RedisTemplate<Long, ShoppingCarts> redisTemplate;


    @Override
    public ShoppingCartDto createShoppingCart(ShoppingCartRequestDto name) {

        ShoppingCarts cart = new ShoppingCarts();

        cart.setName(name.getName());
        ShoppingCarts savedCart = shoppingCartRepository.save(cart);
         redisTemplate.opsForValue().set(cart.getId(),savedCart);
        return modelMapper.map(savedCart,ShoppingCartDto.class);
    }

    @Override
    public ShoppingCartDto addProductToCart(Long cartId, Long productId) {

        ProductsEntity product = productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("Product not found!"));
        ShoppingCarts carts = shoppingCartRepository.findById(cartId)
                .orElseThrow(()->new RuntimeException("Cart not found!"));

        log.info("Product is {}",product);
        carts.getProducts().add(product);
        product.getShoppingCarts().add(carts);
        log.info("Cart is {}",carts);
        ShoppingCarts saveCart = shoppingCartRepository.save(carts);
        redisTemplate.opsForValue().set(carts.getId(),carts);
        return modelMapper.map(saveCart, ShoppingCartDto.class);
    }

    @Override
    public void removeProductFromCart(Long id,Long productId) {
        ShoppingCarts carts = shoppingCartRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Cart not found!"));
        ProductsEntity product = productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("Product not found!"));
        ShoppingCarts shoppingCarts = redisTemplate.opsForValue().get(id);
                carts.getProducts().remove(product);
                redisTemplate.opsForValue().getAndDelete(id);
        log.info("Product is {}",carts);
        shoppingCartRepository.save(carts);


    }

    @Override
    public ShoppingCarts getShoppingCartById(Long id) {
        ShoppingCarts shoppingCarts = shoppingCartRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Shopping cart not found"));
        ShoppingCarts carts = redisTemplate.opsForValue().get(id);

        if (shoppingCarts == null) {
            log.info("Shopping cart DB");
            redisTemplate.opsForValue().set(id, modelMapper.map(shoppingCarts, ShoppingCarts.class));
            return shoppingCarts;
        }
        else {
            log.info("Shopping cart Cache");
            return shoppingCarts;
        }

    }

}
