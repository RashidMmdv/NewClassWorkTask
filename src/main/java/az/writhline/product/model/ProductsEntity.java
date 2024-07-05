package az.writhline.product.model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique=true)
    String name;
    Integer price;
    String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "product_details_id")
    ProductDetails productDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;

//    @ManyToMany
//    @JoinTable(name = "product_shoppingcart",
//            joinColumns = @JoinColumn(name = "product_id"),
//            inverseJoinColumns = @JoinColumn(name = "shoppingcart_id"))
//    Set<ShoppingCarts> shoppingCarts;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<ShoppingCarts> shoppingCarts = new HashSet<>();

}
