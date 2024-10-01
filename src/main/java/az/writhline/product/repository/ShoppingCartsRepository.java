package az.writhline.product.repository;

import az.writhline.product.model.ProductsEntity;
import az.writhline.product.model.ShoppingCarts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ShoppingCartsRepository extends JpaRepository<ShoppingCarts,Long> {

//    @Override
//    Optional<ShoppingCarts> findById(Long id);
//
//    @Query("SELECT p.name, p.price, c.name AS category_name FROM ProductsEntity p JOIN Category c ON p.category = c ")
//    ProductsEntity findByProducts();


}
