package az.writhline.product.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<ProductsEntity> products;
}
