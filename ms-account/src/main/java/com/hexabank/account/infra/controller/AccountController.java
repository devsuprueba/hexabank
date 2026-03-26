package com.hexabank.account.infra.controller;

import com.hexabank.account.application.usecase.CreateAccountUseCase;
import com.hexabank.account.domain.entity.Account;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;

    public AccountController(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody CreateAccountRequest req) {
        Account created = createAccountUseCase.create(req.clientId, req.accountNumber, req.initialBalance);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> get(@PathVariable Long id) {
        // minimal placeholder; in a full implementation we'd use a query use-case
        return ResponseEntity.notFound().build();
    }

    public static record CreateAccountRequest(Long clientId, String accountNumber, BigDecimal initialBalance) {
    }
}
