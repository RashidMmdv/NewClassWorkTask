package az.writhline.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCarts implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "shopping_cart_products",
            joinColumns = @JoinColumn(name = "shopping_cart_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<ProductsEntity> products = new HashSet<>();

}
