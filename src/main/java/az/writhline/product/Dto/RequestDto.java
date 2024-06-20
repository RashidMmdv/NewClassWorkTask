package az.writhline.product.Dto;

import az.writhline.product.enums.Category;
import lombok.Data;

@Data
public class RequestDto {

    String name;
    Integer price;
    Category category;
    String description;
}
