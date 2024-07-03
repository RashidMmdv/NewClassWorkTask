package az.writhline.product.model;


import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
@Builder
@Data
@Getter
@Setter
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

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_details_id")
    ProductDetails productDetails;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    Category category;
}
