package pl.opalinski.sshclient.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.opalinski.sshclient.Entities.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
