package az.writhline.product.repository;


import az.writhline.product.model.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductsEntity,Long> {
    List<ProductsEntity> getProductsEntityByPriceBetween(Integer from, Integer to);

    @Query("SELECT p.category AS category, COUNT(p) AS count FROM ProductsEntity p GROUP BY p.category")
    List<CategoryCount> countProductsByCategory();

//    @Override
//    Optional<ProductsEntity> findById(Long id);
//    @Query("SELECT (p.name, p.price, c.name)
//    List<ProductProjection> findAllProducts();
}
