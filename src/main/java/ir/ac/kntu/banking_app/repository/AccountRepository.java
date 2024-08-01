package ir.ac.kntu.banking_app.repository;

import ir.ac.kntu.banking_app.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
