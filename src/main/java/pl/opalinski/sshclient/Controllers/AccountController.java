package pl.opalinski.sshclient.Controllers;


import com.jcraft.jsch.JSchException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.opalinski.sshclient.Connection.ConnectionData;
import pl.opalinski.sshclient.Connection.SSHConnector;
import pl.opalinski.sshclient.Connection.SecureData;
import pl.opalinski.sshclient.Entities.Account;
import pl.opalinski.sshclient.Services.AccountService;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class AccountController {

    private final AccountService accountService;

    private final ConnectionData connectionData;

    @Autowired
    public AccountController(AccountService accountService, ConnectionData connectionData) {
        this.accountService = accountService;
        this.connectionData = connectionData;

    }

    @GetMapping("/accounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> response = accountService.getAllAccounts();
        if (response.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @GetMapping("/accounts/{id}")
    public ResponseEntity<Account> getStudentById(@PathVariable long id) {
        Account account;
        try {
            account = accountService.getAccountById(id);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

    @PostMapping("/accounts")
    public ResponseEntity<Account> create(@RequestBody Account account) {

        Account newAccount = accountService.saveAccount(account);

        return new ResponseEntity<>(newAccount, HttpStatus.CREATED);
    }

    @PostMapping("/getConnection")
    public ResponseEntity<SecureData> createConnection(@RequestBody SecureData secureData) {
        Account account = accountService.getAccountById(secureData.getId());
        connectionData.setName(account.getName());
        connectionData.setAddress(account.getAddress());
        connectionData.setPassword(secureData.getPassword());
        SSHConnector sshConnector = new SSHConnector(connectionData);
        try {
            sshConnector.run();
        } catch (JSchException e) {
            return new ResponseEntity<>(secureData, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(secureData, HttpStatus.OK);
    }
}
