package az.writhline.product.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "users")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String username;
    private String password;
    private String apiKey;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_authority", joinColumns = @JoinColumn(name = "user_id"))
     public List<Authority> authorities;

}
