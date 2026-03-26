package com.hexabank.account.domain.entity;

import com.hexabank.account.domain.exception.DomainException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountDomainTest {

    @Test
    void creatingAccountWithNullClientIdShouldThrow() {
        assertThrows(DomainException.class, () -> new Account(null, "ACC-1", BigDecimal.ZERO));
    }

    @Test
    void creatingAccountWithNonPositiveClientIdShouldThrow() {
        assertThrows(DomainException.class, () -> new Account(0L, "ACC-2", BigDecimal.ZERO));
    }

}
