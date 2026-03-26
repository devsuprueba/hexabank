package com.hexabank.account.infra.controller.dto;

import java.math.BigDecimal;

public record AccountResponse(Long id, Long clientId, String accountNumber, BigDecimal currentBalance) {
}
