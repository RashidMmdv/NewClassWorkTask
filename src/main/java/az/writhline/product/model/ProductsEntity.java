package az.writhline.product.model;

import az.writhline.product.enums.Category;
import jakarta.persistence.*;
import jdk.jfr.DataAmount;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;

@Entity
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
    @Enumerated(EnumType.STRING)
    Category category;
    String description;
}
