package az.writhline.product.repository;

import az.writhline.product.model.ShoppingCarts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingCartsRepository extends JpaRepository<ShoppingCarts,Long> {

}
