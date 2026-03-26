package com.hexabank.account.infra.controller;

import com.hexabank.account.application.usecase.CreateAccountUseCase;
import com.hexabank.account.application.usecase.GetAccountUseCase;
import com.hexabank.account.application.usecase.GetAccountsUseCase;
import com.hexabank.account.domain.entity.Account;
import com.hexabank.account.infra.controller.dto.AccountResponse;
import com.hexabank.account.infra.controller.dto.CreateAccountRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;
    private final GetAccountUseCase getAccountUseCase;
    private final GetAccountsUseCase getAccountsUseCase;

    public AccountController(CreateAccountUseCase createAccountUseCase,
                             GetAccountUseCase getAccountUseCase,
                             GetAccountsUseCase getAccountsUseCase) {
        this.createAccountUseCase = createAccountUseCase;
        this.getAccountUseCase = getAccountUseCase;
        this.getAccountsUseCase = getAccountsUseCase;
    }

    @PostMapping
    public ResponseEntity<AccountResponse> create(@Valid @RequestBody CreateAccountRequest req) {
        Account created = createAccountUseCase.create(
            req.getClientId(),
            req.getAccountNumber(),
            req.getInitialBalance()
        );

        AccountResponse resp = map(created);

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> get(@PathVariable Long id) {
        return getAccountUseCase.getById(id)
            .map(this::map)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AccountResponse>> list() {
        List<AccountResponse> list = getAccountsUseCase.getAll()
            .stream()
            .map(this::map)
            .collect(Collectors.toList());

        return ResponseEntity.ok(list);
    }

    private AccountResponse map(Account a) {
        return new AccountResponse(a.getId(), a.getClientId(), a.getAccountNumber(), a.getCurrentBalance());
    }
}
