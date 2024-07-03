//package az.writhline.product.configration;
//
//
//import az.writhline.product.model.ProductDetails;
//import az.writhline.product.model.ProductsEntity;
//import az.writhline.product.repository.ProductDetailRepository;
//import az.writhline.product.repository.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//public class ProductConfigration {
//
//    private final ProductRepository productRepository;
//    private final ProductDetailRepository detailRepository;
//    @Bean
//    public ProductsEntity Adding() {
//        ProductDetails details =
//                ProductDetails
//                        .builder()
//                        .color("Mat black")
//                        .image_url("https://XXXXXXXXXXXXXX")
//                        .build();
//        detailRepository.save(details);
//
//        ProductsEntity products =
//                ProductsEntity
//                        .builder()
//                        .name("HP P15 Pro2")
//                        .price(300)
//                        .category(Category.valueOf("ELEKTRONIKA"))
//                        .description("Perfect using!")
//                        .productDetails(details)
//                        .build();
//        productRepository.save(products);
//
//        log.info("Product is {}",products);
//
//        return products;
//    }
//}
