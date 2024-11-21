package az.writhline.product.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class Authority implements GrantedAuthority {

    @Enumerated(EnumType.STRING)
    Role authority;

    public String getAuthority() {
        return authority.name();
    }
}
