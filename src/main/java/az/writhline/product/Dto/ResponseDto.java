package az.writhline.product.Dto;

import az.writhline.product.enums.Category;
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
    Category category;
    String description;
}
