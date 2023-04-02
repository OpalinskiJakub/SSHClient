package pl.opalinski.sshclient.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.opalinski.sshclient.Entities.Account;
import pl.opalinski.sshclient.Repositories.AccountRepository;

import java.util.List;

@Service
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Account getAccountById(long id) {
        return accountRepository.findById(id).orElseThrow();
    }

    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }
}

