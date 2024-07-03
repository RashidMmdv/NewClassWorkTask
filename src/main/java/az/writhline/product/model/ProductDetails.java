package az.writhline.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String color;
    String image_url;

    @OneToOne(mappedBy = "productDetails",fetch = FetchType.LAZY)
     ProductsEntity product;
}
