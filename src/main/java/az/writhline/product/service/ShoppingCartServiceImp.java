package az.writhline.product.service;

import az.writhline.product.Dto.ShoppingCartDto;
import az.writhline.product.model.ProductsEntity;
import az.writhline.product.model.ShoppingCarts;
import az.writhline.product.repository.ProductRepository;
import az.writhline.product.repository.ShoppingCartsRepository;
import az.writhline.product.service.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
 class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartsRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;



    @Override
    public ShoppingCartDto createShoppingCart(String name) {
        ShoppingCarts cart = new ShoppingCarts();
        cart.setName(name);
        ShoppingCarts savedCart = shoppingCartRepository.save(cart);
        return modelMapper.map(savedCart, ShoppingCartDto.class);
    }

    @Override
    public ShoppingCartDto addProductToCart(Long cartId, Long productId) {
        Optional<ShoppingCarts> cartOpt = shoppingCartRepository.findById(cartId);
        Optional<ProductsEntity> productOpt = productRepository.findById(productId);

        if (cartOpt.isPresent() && productOpt.isPresent()) {
            ShoppingCarts cart = cartOpt.get();
            ProductsEntity product = productOpt.get();
            cart.getProducts().add(product);
            ShoppingCarts updatedCart = shoppingCartRepository.save(cart);
            return modelMapper.map(updatedCart, ShoppingCartDto.class);
        } else {
            throw new IllegalArgumentException("Id not fonud");
        }
    }

    @Override
    public ShoppingCartDto removeProductFromCart(Long cartId, Long productId) {
        Optional<ShoppingCarts> cartOpt = shoppingCartRepository.findById(cartId);
        Optional<ProductsEntity> productOpt = productRepository.findById(productId);

        if (cartOpt.isPresent() && productOpt.isPresent()) {
            ShoppingCarts cart = cartOpt.get();
            ProductsEntity product = productOpt.get();
            cart.getProducts().remove(product);
            ShoppingCarts updatedCart = shoppingCartRepository.save(cart);
            return modelMapper.map(updatedCart, ShoppingCartDto.class);
        } else {
            throw new IllegalArgumentException("Id not found");
        }
    }

    @Override
    public ShoppingCartDto getShoppingCartById(Long id) {
        ShoppingCarts cart = shoppingCartRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));
        return modelMapper.map(cart, ShoppingCartDto.class);
    }
}
