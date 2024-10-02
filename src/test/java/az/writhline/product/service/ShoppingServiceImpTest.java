//package az.writhline.product.service;
//
//import az.writhline.product.model.ProductsEntity;
//import az.writhline.product.model.ShoppingCarts;
//import az.writhline.product.repository.ProductRepository;
//import az.writhline.product.repository.ShoppingCartsRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.ArrayList;
//import java.util.Objects;
//import java.util.Optional;
//import java.util.Set;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.*;
//
//
//@ExtendWith(MockitoExtension.class)
//class ShoppingServiceImpTest {
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
//}
//
//
