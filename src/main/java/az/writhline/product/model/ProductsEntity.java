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

public class ProductsEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    String name;
    Integer price;
    String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_details_id")
    ProductDetails productDetails;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Category category;


    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<ShoppingCarts> shoppingCarts = new HashSet<>();

}
