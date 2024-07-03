package az.writhline.product.Dto;


import lombok.Data;
import lombok.Getter;

@Data

public class RequestDto {

    String name;
    Integer price;
    CategoryDto category;
    String description;
    ProductDetailDto productDetails;
}
