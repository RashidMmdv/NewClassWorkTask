package az.writhline.product;

import az.writhline.product.model.ProductDetails;
import az.writhline.product.model.ProductsEntity;
import az.writhline.product.repository.ProductDetailRepository;
import az.writhline.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableMBeanExport;

@SpringBootApplication

public class ProductApplication {

    public static void main(String[] args) {SpringApplication.run(ProductApplication.class, args);}

}
