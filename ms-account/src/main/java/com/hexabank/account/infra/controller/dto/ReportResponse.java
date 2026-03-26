package com.hexabank.account.infra.controller.dto;

import java.math.BigDecimal;
import java.util.List;

public final class ReportResponse {

    private final Long clientId;
    private final List<AccountStatementResponse> accounts;

    public ReportResponse(final Long clientId, final List<AccountStatementResponse> accounts) {
        this.clientId = clientId;
        this.accounts = accounts;
    }

    public Long getClientId() {
        return clientId;
    }

    public List<AccountStatementResponse> getAccounts() {
        return accounts;
    }

    public static final class AccountStatementResponse {
        private final Long accountId;
        private final String accountNumber;
        private final BigDecimal currentBalance;
        private final List<MovementRowResponse> movements;

        public AccountStatementResponse(final Long accountId, final String accountNumber,
                                        final BigDecimal currentBalance,
                                        final List<MovementRowResponse> movements) {
            this.accountId = accountId;
            this.accountNumber = accountNumber;
            this.currentBalance = currentBalance;
            this.movements = movements;
        }

        public Long getAccountId() {
            return accountId;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public BigDecimal getCurrentBalance() {
            return currentBalance;
        }

        public List<MovementRowResponse> getMovements() {
            return movements;
        }
    }

    public static final class MovementRowResponse {
        private final String movementDate;
        private final String movementType;
        private final BigDecimal amount;
        private final BigDecimal balanceAfter;

        public MovementRowResponse(final String movementDate, final String movementType,
                                   final BigDecimal amount, final BigDecimal balanceAfter) {
            this.movementDate = movementDate;
            this.movementType = movementType;
            this.amount = amount;
            this.balanceAfter = balanceAfter;
        }

        public String getMovementDate() {
            return movementDate;
        }

        public String getMovementType() {
            return movementType;
        }

        public BigDecimal getAmount() {
            return amount;
        }

        public BigDecimal getBalanceAfter() {
            return balanceAfter;
        }
    }
}
