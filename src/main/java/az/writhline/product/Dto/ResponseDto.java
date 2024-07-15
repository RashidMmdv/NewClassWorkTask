package az.writhline.product.Dto;


import az.writhline.product.model.Category;
import az.writhline.product.model.ProductDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
    Long id;
    String name;
    Integer price;
    String  category;
    String description;
    ProductDetailDto productDetails;
}
