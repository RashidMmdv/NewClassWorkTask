package az.writhline.product.configration;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Optional;
import org.springframework.security.core.Authentication;

public interface AuthService {

    Optional<Authentication> getAuthentication(HttpServletRequest httpServletRequest);

}
