package az.writhline.product.repository;

import az.writhline.product.model.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CachingRepository extends JpaRepository<Caching, Long> {
}
