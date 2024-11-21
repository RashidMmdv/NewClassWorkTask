package az.writhline.product.service;

import az.writhline.product.Dto.ShoppingCartDto;
import az.writhline.product.Dto.ShoppingCartRequestDto;
import az.writhline.product.model.ProductsEntity;
import az.writhline.product.model.ShoppingCarts;
import az.writhline.product.repository.ProductRepository;
import az.writhline.product.repository.ShoppingCartsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ShoppingServiceImpTest {
//    @InjectMocks
//    ShoppingServiceImp shoppingServiceImp;
//    @Mock
//    ShoppingCartsRepository shoppingCartsRepository;
//    @Mock
//    ProductRepository productRepository;
//
//    private ShoppingCarts shoppingCart;
//    private ProductsEntity products1;
//
//    @BeforeEach
//    public void setUp() {
//
//        products1 = ProductsEntity.builder()
//                .id(9L)
//                .name("XXX")
//                .price(300)
//                .description("Description")
//                .build();
//        shoppingCart = ShoppingCarts.builder()
//                .id(10L)
//                .name("ProductId2")
//                .products(Set.of(products1))
//                .build();
//    }
//
//
//    @Test
//    void givenValidIdWhenGetShoppingCardThenSuccess() {
//        //Arrange
//
//        when(shoppingCartsRepository.findById(anyLong())).thenReturn(Optional.of(shoppingCart));
//        //Act
//        ShoppingCarts result = shoppingServiceImp.getShoppingCartById(10L);
//
//        //Asset
//        assertThat(result.getId()).isEqualTo(shoppingCart.getId());
//        assertThat(result.getName()).isEqualTo(shoppingCart.getName());
//        assertThat(result.getProducts()).isEqualTo(shoppingCart.getProducts());
//
//        verify(shoppingCartsRepository, times(1)).findById(anyLong());
//
//
//    }
//    @Test
//    void givenInvalidIdWhenGetShoppingCartByIdThenException() {
//    when(shoppingCartsRepository.findById(anyLong())).thenReturn(Optional.empty());
//    assertThatThrownBy(()-> shoppingServiceImp.getShoppingCartById(10L))
//            .isInstanceOf(RuntimeException.class)
//            .hasMessageContaining("Shopping cart not found");
//    }
//
//
//    @ParameterizedTest
//    @CsvSource("10, 9")
//     void givenTwoValidIdWhenRemoveProductFromShoppingCartThenSuccess(Long id ,Long productId) {
//        System.out.print(shoppingCart);
//
//        when(shoppingCartsRepository.findById(id)).thenReturn(Optional.of(shoppingCart));
//        when(productRepository.findById(productId)).thenReturn(Optional.of(products1));
//
//         assertThatThrownBy(()-> shoppingServiceImp.removeProductFromCart(id, productId))
//                 .isInstanceOf(UnsupportedOperationException.class);
//
//////            assertThat(shoppingCart.getProducts()).isNull();
////            verify(shoppingCartsRepository).findById(id);
////            verify(productRepository).findById(productId);
////            verify(shoppingCartsRepository).save(shoppingCart);
//
//    }


    @Mock
    private ShoppingCartsRepository shoppingCartsRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private RedisTemplate<Long, ShoppingCarts> redisTemplate;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ShoppingServiceImp shoppingService;

    private ValueOperations<Long, ShoppingCarts> valueOperationsMock;
    private ShoppingCarts cart;
    private ProductsEntity products;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        valueOperationsMock = mock(ValueOperations.class);
        when(redisTemplate.opsForValue()).thenReturn(valueOperationsMock);
//
//        products = ProductsEntity.builder()
//                .id(2L)  // Make sure the ID matches the ID used in the service call
//                .name("Test Product")
//                .price(1200)
//                .description("Test Description")
//                .shoppingCarts(new HashSet<>())
//                .build();
//
//        cart = ShoppingCarts.builder()
//                .id(1L)
//                .name("Test Cart")
//                .products(new HashSet<>())
//                .build();

    }


    @Test
    public void testCreateShoppingCart() {
        // Arrange
        ShoppingCartRequestDto requestDto = new ShoppingCartRequestDto();
        requestDto.setName("Test Product");

        ShoppingCarts cart = new ShoppingCarts();
        cart.setName("Test Product");

        ShoppingCarts savedCart = new ShoppingCarts();
        savedCart.setId(1L);
        savedCart.setName("Test Product");

        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(1L);
        shoppingCartDto.setName("New c");


        when(shoppingCartsRepository.save(any(ShoppingCarts.class))).thenAnswer(set -> {
            ShoppingCarts cartArg = set.getArgument(0);
            cartArg.setId(1L);
            return cartArg;
        });

        when(modelMapper.map(savedCart, ShoppingCartDto.class)).thenReturn(shoppingCartDto);

        // Act
        ShoppingCartDto result = shoppingService.createShoppingCart(requestDto);

        // Assert
//        cart = ShoppingCarts.builder()
//                .id(1L)
//                .name("Test Cart")
//                .products(new HashSet<>())
//                .build();
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("New c", result.getName());

        // Verify repository save was called
        verify(shoppingCartsRepository, times(1)).save(any(ShoppingCarts.class));

        // Verify RedisTemplate's set method was called with the correct ID
        verify(valueOperationsMock, times(1)).set(1L, savedCart);

    }

//    @Test
//    public void testAddProductToShoppingCart() {
//        // Arrange
//
//
//        when(shoppingCartsRepository.findById(cart.getId())).thenReturn(Optional.of(cart));
//        when(productRepository.findById(products.getId())).thenReturn(Optional.of(products));
//
//        cart.getProducts().add(products);
//        products.getShoppingCarts().add(cart);
//        when(shoppingCartsRepository.save(cart)).thenReturn(cart);
//
//        ShoppingCartDto expectedDto = new ShoppingCartDto();
//        when(modelMapper.map(cart, ShoppingCartDto.class)).thenReturn(expectedDto);
//
//        // Act
//        ShoppingCartDto result = shoppingService.addProductToCart(cart.getId(), products.getId());
//
//        // Assert
//        assertNotNull(result);
//        verify(shoppingCartsRepository, times(1)).findById(cart.getId());
//        verify(productRepository, times(1)).findById(products.getId());
//        verify(shoppingCartsRepository, times(1)).save(cart);
//        verify(redisTemplate, times(1)).opsForValue().set(cart.getId(), cart);
//    }

//    @Test
//    public void testGetShoppingCartById() {
//        ShoppingCarts shoppingCarts = new ShoppingCarts();
//        shoppingCarts.setId(1L);
//        shoppingCarts.setName("Test Product");
//        // Arrange
//        when(shoppingCartsRepository.findById(anyLong())).thenReturn(Optional.of(shoppingCarts));
//
//        // Act
//        ShoppingCarts carts = shoppingService.getShoppingCartById(1L);
//
//        // Assert
//
//    }



}













