package az.writhline.product.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponseDto {
    private int status;
    private ErrorCode code;
    private String message;
    private String detail;
    private OffsetDateTime timestamp;
    private String path;
    @JsonProperty("requested_lang")
    private String requestedLang;
    @Builder.Default
    private Map<String, Object> data = new HashMap<>();
}