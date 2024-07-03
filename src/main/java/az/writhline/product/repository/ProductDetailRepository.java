package az.writhline.product.repository;

import az.writhline.product.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductDetailRepository extends JpaRepository<ProductDetails, Long> {
}
