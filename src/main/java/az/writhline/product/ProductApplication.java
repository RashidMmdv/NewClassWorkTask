package az.writhline.product;

import az.writhline.product.model.Category;
import az.writhline.product.model.ProductDetails;
import az.writhline.product.model.ProductsEntity;
import az.writhline.product.model.ShoppingCarts;
import az.writhline.product.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Component;

import java.util.Set;

@SpringBootApplication
@RequiredArgsConstructor
@EnableCaching
public class ProductApplication {
    private final ProductRepository productRepository;
    public static void main(String[] args) {SpringApplication.run(ProductApplication.class, args);}

//    @Component
//    class MyCommandLineRunner implements CommandLineRunner {
//
//        @Transactional
//        @Override
//        public void run(String... args) throws Exception {
//
//            Category category = Category.builder()
//                    .name("ELEKTRONIKA")
//                    .build();
//            ProductDetails productDetails = ProductDetails.builder()
//                    .color("Black")
//                    .image_url("https://xxxx.com")
//                    .build();
//            ShoppingCarts carts = ShoppingCarts.builder()
//                    .name("Asus A23334dd2222")
//
//                    .build();
//            ProductsEntity products = ProductsEntity.builder()
//                    .name("ASUS")
//                    .price(3500)
//                    .description("lhlhvlh;h;b;cbbhdbhbhhbhdsgd")
//                    .category(category)
//                    .productDetails(productDetails)
//
//                    .build();
//
//            productRepository.save(products);
//
//        }
//    }
}
