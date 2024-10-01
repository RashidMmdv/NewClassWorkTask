package az.writhline.product.repository;
import az.writhline.product.model.Account;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface AccountRepository extends CrudRepository<Account, Long> {

    @Lock(LockModeType.OPTIMISTIC)
    Optional<Account> findById(Long Id);
}
