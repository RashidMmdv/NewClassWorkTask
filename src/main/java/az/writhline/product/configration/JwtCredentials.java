package az.writhline.product.configration;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import java.util.List;

@Data
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtCredentials {

    Long id;
    List<String> authority;
    String status;
    String sub;
    String type;
}
